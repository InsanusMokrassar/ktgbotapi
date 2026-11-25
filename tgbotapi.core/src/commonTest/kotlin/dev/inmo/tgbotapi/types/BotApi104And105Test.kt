package dev.inmo.tgbotapi.types

import dev.inmo.tgbotapi.requests.bot.GetMe
import dev.inmo.tgbotapi.requests.bot.SetMySettings
import dev.inmo.tgbotapi.requests.verifications.VerifyChat
import dev.inmo.tgbotapi.requests.verifications.VerifyUser
import dev.inmo.tgbotapi.types.chat.BotFullInfo
import dev.inmo.tgbotapi.types.chat.CommonBot
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.message.ChatEvents.forum.WriteAccessAllowed
import dev.inmo.tgbotapi.types.message.HTML
import dev.inmo.tgbotapi.types.message.RawMessageEntity
import dev.inmo.tgbotapi.types.message.asTextSources
import dev.inmo.tgbotapi.types.message.textsources.*
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.types.payments.ton.TonAddress
import dev.inmo.tgbotapi.types.queries.callback.InlineMessageIdDataCallbackQuery
import dev.inmo.tgbotapi.types.rich.*
import dev.inmo.tgbotapi.types.update.CallbackQueryUpdate
import dev.inmo.tgbotapi.types.update.abstracts.UpdateDeserializationStrategy
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.*
import kotlin.test.*

class BotApi104And105Test {
    private val json = Json { ignoreUnknownKeys = true }

    @Test
    fun getMeDecodesSelfInfoAndOrdinaryUsersRemainSeparate() {
        val wire = """{"id":123,"is_bot":true,"first_name":"Example","username":"example_bot","active_usernames":["example_bot","example_ai"],"supports_inline_queries":true,"inline_query_placeholder":"Search","needs_inline_query_location":true,"has_topic_sidebar":true,"allows_users_to_create_topics":true}"""
        val info = json.decodeFromString(GetMe.resultDeserializer, wire)
        assertEquals(123L.toChatId(), info.id)
        assertEquals(Username("@example_bot"), info.username)
        assertEquals(listOf(Username("@example_bot"), Username("@example_ai")), info.activeUsernames)
        assertEquals("Search", info.inlineQueryPlaceholder)
        assertTrue(info.needsInlineQueryLocation && info.hasTopicSidebar && info.allowsUsersToCreateTopics)
        val encoded = json.encodeToJsonElement(BotFullInfo.serializer(), info).jsonObject
        assertEquals(JsonPrimitive(true), encoded["is_bot"])
        assertEquals(JsonPrimitive("example_bot"), encoded["username"])
        assertEquals(JsonPrimitive("example_ai"), encoded.getValue("active_usernames").jsonArray.last())
        assertIs<CommonBot>(json.decodeFromString<User>("""{"id":123,"is_bot":true,"first_name":"Example"}"""))
    }

    @Test
    fun settingsDistinguishOmissionFromFalseZeroAndEmptyString() {
        val settings = SetMySettings(
            inlineQueryPlaceholder = "", inlineFeedbackShareDenominator = InlineFeedbackShareDenominator.Disabled,
            hasTopicSidebar = false, canCommunicateWithBots = true, callbackQueryMode = CallbackQueryMode.Inline
        )
        assertEquals("setMySettings", settings.method())
        assertEquals(json.parseToJsonElement("""{"inline_query_placeholder":"","inline_feedback_share_denominator":0,"has_topic_sidebar":false,"can_communicate_with_bots":true,"callback_query_mode":"inline"}"""), json.encodeToJsonElement(settings))
        assertEquals(json.parseToJsonElement("{}"), json.encodeToJsonElement(SetMySettings()))
        assertEquals(CallbackQueryMode.Auto, json.decodeFromString<CallbackQueryMode>("\"auto\""))
        assertFailsWith<SerializationException> { json.decodeFromString<CallbackQueryMode.Default>("\"inline\"") }
        assertFailsWith<SerializationException> { json.decodeFromString<InlineFeedbackShareDenominator.All>("10") }
        assertFailsWith<SerializationException> { json.decodeFromString<InlineFeedbackShareDenominator>("2") }
    }

    @Test
    fun callbackModeAndWriteAccessEventsDecodeThroughPublicContracts() {
        val update = json.decodeFromString(UpdateDeserializationStrategy, """{"update_id":1,"callback_query":{"id":"q","from":{"id":2,"is_bot":false,"first_name":"A"},"chat_instance":"instance","inline_message_id":"inline","data":"continue"}}""")
        val callback = assertIs<InlineMessageIdDataCallbackQuery>(assertIs<CallbackQueryUpdate>(update).data)
        assertEquals("continue", callback.data)
        val event = json.decodeFromString<WriteAccessAllowed>("""{"inviter":{"id":2,"is_bot":false,"first_name":"Owner"}}""")
        assertEquals(2L.toChatId(), assertIs<WriteAccessAllowed.FromInvitation>(event).inviter.id)
        assertEquals(event, json.decodeFromJsonElement<WriteAccessAllowed>(json.encodeToJsonElement(event)))
        assertEquals(WriteAccessAllowed.FromAttachmentMenu, json.decodeFromString<WriteAccessAllowed>("""{"from_attachment_menu":true}"""))
    }

    @Test
    fun verificationDescriptionSupportsBothTelegramFormattingForms() {
        val user = VerifyUser(1L.toChatId(), listOf(regularTextSource("ℹ️ "), linkTextSource("Help", "https://example.org/help")))
        val encoded = json.encodeToJsonElement(user).jsonObject
        val link = encoded.getValue("custom_description_entities").jsonArray.single().jsonObject
        assertEquals(JsonPrimitive("ℹ️ Help"), encoded["custom_description"])
        assertEquals(JsonPrimitive(3), link["offset"])
        assertEquals(JsonPrimitive(4), link["length"])
        assertEquals(JsonPrimitive("text_link"), link["type"])
        assertEquals(JsonPrimitive("https://example.org/help"), link["url"])
        val chat = VerifyChat(Username("@example_chat"), "<a href='https://example.org'>Help</a>", HTML)
        assertEquals(JsonPrimitive("HTML"), json.encodeToJsonElement(chat).jsonObject["custom_description_parse_mode"])
        assertFailsWith<IllegalArgumentException> { VerifyChat(chat.chatId, chat.description, HTML, user.customDescriptionEntities) }
        assertFailsWith<IllegalArgumentException> { VerifyUser(user.userId, listOf(boldTextSource("Help"))) }
        assertFailsWith<IllegalArgumentException> {
            VerifyChat(chat.chatId, "Help", customDescriptionEntities = listOf(RawMessageEntity("text_link", 0, 4)))
        }
    }

    @Test
    fun tonAndBankCardEntitiesRetainUtf16OffsetsAndTypes() {
        val text = "🚀 address 1234"
        val raw = listOf(RawMessageEntity("ton_address", 3, 7), RawMessageEntity("bank_card_number", 11, 4))
        val sources = raw.asTextSources(text)
        assertIs<TonAddressTextSource>(sources[1])
        assertIs<BankCardNumberTextSource>(sources.last())
        assertEquals(raw, sources.toRawMessageEntities())
        val rich = json.decodeFromString<RichText>("""{"type":"ton_address","text":{"type":"bold","text":"Wallet"},"address":"EQ_example"}""")
        assertEquals(TonAddress("EQ_example"), assertIs<RichTextTonAddress>(rich).address)
        assertEquals(rich, json.decodeFromJsonElement<RichText>(json.encodeToJsonElement(rich)))
    }

    @Test
    fun expandableQuotationsReadLegacyTextAndWriteNestedBlocks() {
        val oldWire = """{"type":"expandable_blockquote","text":"earlier"}"""
        val old = json.decodeFromString<RichBlockExpandableBlockQuotation>(oldWire)
        assertEquals(listOf(RichBlockParagraph(RichTextPlain("earlier"))), old.blocks)
        val oldStored = """{"type":"expandable_blockquote","text":"earlier","html":"cached","markdown":"cached"}"""
        assertEquals(old, Json.decodeFromString<RichBlockExpandableBlockQuotation>(oldStored))
        val wire = json.encodeToJsonElement(old).jsonObject
        assertTrue("blocks" in wire)
        assertFalse("text" in wire)
        val nested = buildInputRichBlocks {
            expandableBlockQuotation(blocks = {
                paragraph("first")
                divider()
            }, credit = RichTextPlain("owner"))
        }.single()
        assertEquals(2, nested.subBlocks.size)
        val stored = json.encodeToJsonElement(InputRichBlockSerializer, nested)
        assertEquals(nested, json.decodeFromJsonElement(InputRichBlockSerializer, stored))
        val received = json.decodeFromJsonElement(RichBlockSerializer, stored)
        assertEquals(2, received.subBlocks.size)
        assertNotNull(received.search { this is RichBlockDivider })
        assertTrue(received.html.contains("<cite>owner</cite>"))
    }
}
