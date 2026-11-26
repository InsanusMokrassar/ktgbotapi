package dev.inmo.tgbotapi.types

import dev.inmo.tgbotapi.requests.business_connection.DeleteBusinessAccountGift
import dev.inmo.tgbotapi.requests.chat.get.GetChat
import dev.inmo.tgbotapi.requests.chat.get.GetChatShareThemeBackdrops
import dev.inmo.tgbotapi.requests.gifts.GetUniqueGift
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.chat.*
import dev.inmo.tgbotapi.types.gifts.UniqueGiftName
import dev.inmo.tgbotapi.types.message.abstracts.ChatEventMessage
import dev.inmo.tgbotapi.types.payments.ton.TonAddress
import dev.inmo.tgbotapi.types.payments.ton.TonWalletCard
import dev.inmo.tgbotapi.types.payments.ton.TonWalletTransfer
import dev.inmo.tgbotapi.types.update.MessageUpdate
import dev.inmo.tgbotapi.types.update.abstracts.UpdateSerializerWithoutSerialization
import dev.inmo.tgbotapi.utils.nonstrictJsonFormat
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.*
import kotlin.test.*

class WalletAndShareThemeTest {
    private val json = Json
    private val emojiTheme = """{"type":"emoji","emojis":[{"type":"emoji","emoji":"⭐"},{"type":"custom_emoji","custom_emoji_id":"123"}],"background_backdrop_id":7}"""
    private val card = """
        {
          "address":"wallet", "amount":"100000000000000000001", "background_nft_address":"nft",
          "stickers":[{
            "position":{"x_percentage":12.5,"y_percentage":37.5,"width_percentage":62.5,"height_percentage":87.5,"rotation_angle":90.5,"corner_radius_percentage":6.25},
            "nft":{"address":"nft","collection_address":"collection","index":"18446744073709551616","name":"Example","description":"Sample NFT","attributes":[{"trait_type":"color","value":"blue"}]}
          }]
        }
    """.trimIndent()

    @Test
    fun getChatDecodesWalletAndShareTheme() {
        val getChat = GetChat(ChatId(RawChatId(1)))
        val chat = assertIs<ExtendedPrivateChat>(
            json.decodeFromString(getChat.resultDeserializer, """{"id":1,"type":"private","ton_wallet_card":$card,"share_theme":$emojiTheme}""")
        )
        val wallet = assertNotNull(chat.tonWalletCard)
        assertEquals(TonAddress("wallet"), wallet.address)
        assertEquals("100000000000000000001", wallet.amount)
        assertEquals("18446744073709551616", wallet.stickers.single().nft.index)
        assertEquals(json.parseToJsonElement(card), json.encodeToJsonElement(TonWalletCard.serializer(), wallet))
        val theme = assertIs<ChatShareTheme.Emoji>(chat.shareTheme)
        assertEquals(listOf(EmojiType.Emoji("⭐"), EmojiType.CustomEmoji(CustomEmojiId("123"))), theme.emojis)
        assertEquals(ChatShareThemeBackdropId(7), theme.backgroundBackdropId)
        assertEquals(json.parseToJsonElement(emojiTheme), nonstrictJsonFormat.encodeToJsonElement(ChatShareTheme.serializer(), theme))

        val bare = assertIs<ExtendedPrivateChat>(json.decodeFromString(getChat.resultDeserializer, """{"id":1,"type":"private"}"""))
        assertNull(bare.tonWalletCard)
        assertNull(bare.shareTheme)
        for (type in listOf("supergroup", "channel")) {
            val publicChat = json.decodeFromString(getChat.resultDeserializer, """{"id":-1,"type":"$type","title":"Chat","permissions":{},"accepted_gift_types":{},"share_theme":$emojiTheme}""")
            assertEquals(theme, publicChat.shareTheme)
        }
    }

    @Test
    fun uniqueGiftThemeUsesTheSameGiftAsGetUniqueGift() {
        val sticker = """{"file_id":"file","file_unique_id":"unique","type":"regular","width":1,"height":1}"""
        val colors = """{"center_color":1,"edge_color":2,"symbol_color":3,"text_color":4}"""
        val giftJson = """
            {"base_name":"Base","name":"Base-1","number":1,
             "model":{"name":"Model","sticker":$sticker,"rarity_per_mille":1},
             "symbol":{"name":"Symbol","sticker":$sticker,"rarity_per_mille":2},
             "backdrop":{"name":"Backdrop","colors":$colors,"rarity_per_mille":3}}
        """.trimIndent()
        val request = GetUniqueGift(UniqueGiftName("Base-1"))
        assertEquals("getUniqueGift", request.method())
        assertEquals(json.parseToJsonElement("""{"name":"Base-1"}"""), json.encodeToJsonElement(GetUniqueGift.serializer(), request))
        val gift = nonstrictJsonFormat.decodeFromString(request.resultDeserializer, giftJson)
        val themeJson = """{"type":"unique_gift","gift":$giftJson}"""
        val theme = assertIs<ChatShareTheme.UniqueGift>(nonstrictJsonFormat.decodeFromString(ChatShareTheme.serializer(), themeJson))
        assertEquals(gift, theme.gift)
        assertEquals(theme, nonstrictJsonFormat.decodeFromJsonElement(ChatShareTheme.serializer(), nonstrictJsonFormat.encodeToJsonElement(ChatShareTheme.serializer(), theme)))
        assertFailsWith<SerializationException> { json.decodeFromString(ChatShareTheme.UniqueGift.serializer(), emojiTheme) }
        assertFailsWith<SerializationException> { json.decodeFromString(EmojiType.Emoji.serializer(), """{"type":"custom_emoji","custom_emoji_id":"123"}""") }

        val backdrops = json.decodeFromString(GetChatShareThemeBackdrops.resultDeserializer, """{"backdrops":[{"id":7,"name":"Example","colors":$colors}]}""")
        assertEquals("getChatShareThemeBackdrops", GetChatShareThemeBackdrops.method())
        assertEquals(ChatShareThemeBackdropId(7), backdrops.backdrops.single().id)
        assertEquals(4, backdrops.backdrops.single().colors.textColor.int)
    }

    @Test
    fun deleteBusinessGiftSerializesTheOwnedGiftIdentifier() {
        val request = DeleteBusinessAccountGift(BusinessConnectionId("business"), GiftId("owned"))
        assertEquals("deleteBusinessAccountGift", request.method())
        assertEquals(
            json.parseToJsonElement("""{"business_connection_id":"business","owned_gift_id":"owned"}"""),
            json.encodeToJsonElement(DeleteBusinessAccountGift.serializer(), request),
        )
        assertEquals(Unit, json.decodeFromString(request.resultDeserializer, "true"))
    }

    @Test
    fun serviceMessagesDecodeFromUpdates() {
        fun event(fields: String) = assertIs<ChatEventMessage<*>>(
            assertIs<MessageUpdate>(nonstrictJsonFormat.decodeFromString(
                UpdateSerializerWithoutSerialization,
                """{"update_id":1,"message":{"message_id":2,"date":3,"chat":{"id":4,"type":"private","first_name":"User"},$fields}}"""
            )).data
        ).chatEvent

        assertEquals(PaidMessagesWaived, event(""""paid_messages_waived":{}"""))
        val transfer = assertIs<TonWalletTransfer>(event(""""ton_wallet_transfer":{"transaction_id":"","peer_address":"peer","amount":"-900000000000000000001","comment":"Note"}"""))
        assertEquals("", transfer.transactionId)
        assertEquals(TonAddress("peer"), transfer.peerAddress)
        assertEquals("-900000000000000000001", transfer.amount)
        assertEquals("Note", transfer.comment)
        assertNull(json.decodeFromString(TonWalletTransfer.serializer(), """{"transaction_id":"sent","peer_address":"peer","amount":"1"}""").comment)
    }
}
