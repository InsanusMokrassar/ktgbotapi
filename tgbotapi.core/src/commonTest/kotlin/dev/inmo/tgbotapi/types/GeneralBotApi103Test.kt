package dev.inmo.tgbotapi.types

import dev.inmo.tgbotapi.requests.send.SendMessageDraft
import dev.inmo.tgbotapi.requests.send.SendRichMessageDraft
import dev.inmo.tgbotapi.types.gifts.GiftSentOrReceivedEvent
import dev.inmo.tgbotapi.types.communities.CommunityId
import dev.inmo.tgbotapi.types.message.abstracts.ChatEventMessage
import dev.inmo.tgbotapi.types.message.textsources.boldTextSource
import dev.inmo.tgbotapi.types.rich.InputRichMessageHTML
import dev.inmo.tgbotapi.types.update.MessageGenerationStoppedUpdate
import dev.inmo.tgbotapi.types.update.MessageUpdate
import dev.inmo.tgbotapi.types.update.abstracts.UpdateSerializerWithoutSerialization
import dev.inmo.tgbotapi.utils.nonstrictJsonFormat
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

class GeneralBotApi103Test {
    private val json = Json { encodeDefaults = true }

    @Test
    fun `draft requests serialize generation stop controls`() {
        val chatId = ChatId(RawChatId(1L))
        val messageDraft = SendMessageDraft(chatId, DraftId(2L), "draft", canStop = true, keepOnStop = true)
        val richDraft = SendRichMessageDraft(chatId, 2L, InputRichMessageHTML("draft"), canStop = true, keepOnStop = true)

        val messageDraftJson = json.encodeToJsonElement(SendMessageDraft.serializer(), messageDraft).jsonObject
        val richDraftJson = json.encodeToJsonElement(SendRichMessageDraft.serializer(), richDraft).jsonObject

        assertEquals("true", messageDraftJson[canStopField].toString())
        assertEquals("true", messageDraftJson[keepOnStopField].toString())
        assertEquals("true", richDraftJson[canStopField].toString())
        assertEquals("true", richDraftJson[keepOnStopField].toString())
    }

    @Test
    fun `rich draft accepts thread-bearing chat identifiers`() {
        val chatId = ChatIdWithThreadId(RawChatId(3L), MessageThreadId(4L))
        val richDraft = SendRichMessageDraft(chatId, 5L, InputRichMessageHTML("draft"))
        val richDraftJson = json.encodeToJsonElement(SendRichMessageDraft.serializer(), richDraft).jsonObject

        assertEquals(chatId, richDraft.chatId)
        assertEquals(MessageThreadId(4L), richDraft.threadId)
        assertEquals("3", richDraftJson[chatIdField].toString())
        assertEquals("4", richDraftJson[messageThreadIdField].toString())
    }

    @Test
    fun `stopped message generation update deserializes`() {
        val update = nonstrictJsonFormat.decodeFromString(
            UpdateSerializerWithoutSerialization,
            """{"update_id":1,"stopped_message_generation":{"chat":{"id":2,"type":"private","first_name":"User"},"message_thread_id":3,"draft_id":4}}"""
        )

        val stoppedUpdate = assertIs<MessageGenerationStoppedUpdate>(update)
        assertEquals(ChatId(RawChatId(2L)), stoppedUpdate.data.chat.id)
        assertEquals(MessageThreadId(3L), stoppedUpdate.data.messageThreadId)
        assertEquals(DraftId(4L), stoppedUpdate.data.draftId)
    }

    @Test
    fun `community chat joined message deserializes`() {
        val update = nonstrictJsonFormat.decodeFromString(
            UpdateSerializerWithoutSerialization,
            """{"update_id":1,"message":{"message_id":2,"date":3,"chat":{"id":4,"type":"private","first_name":"User"},"community_chat_joined":{"community":{"id":5,"name":"Community"}}}}"""
        )

        val eventMessage = assertIs<ChatEventMessage<*>>(assertIs<MessageUpdate>(update).data)
        val joined = assertIs<dev.inmo.tgbotapi.types.communities.CommunityChatJoined>(eventMessage.chatEvent)
        assertEquals(CommunityId(5L), joined.community.id)
    }

    @Test
    fun `unique gift info text fields deserialize into text sources`() {
        val uniqueGift = nonstrictJsonFormat.decodeFromString(
            GiftSentOrReceivedEvent.UniqueGift,
            """{"gift":{"base_name":"Base","name":"unique","number":1,"model":{"name":"Model","sticker":{"file_id":"model-file","file_unique_id":"model-unique","type":"regular","width":1,"height":1},"rarity_per_mille":1},"symbol":{"name":"Symbol","sticker":{"file_id":"symbol-file","file_unique_id":"symbol-unique","type":"regular","width":1,"height":1},"rarity_per_mille":1},"backdrop":{"name":"Backdrop","colors":{"center_color":1,"edge_color":2,"symbol_color":3,"text_color":4},"rarity_per_mille":1}},"origin":"upgrade","text":"bold","entities":[{"type":"bold","offset":0,"length":4}],"is_private":true}"""
        )

        assertEquals("bold", uniqueGift.text)
        assertEquals(true, uniqueGift.isPrivate)
        assertEquals(1, uniqueGift.textSources.size)
    }

    @Test
    fun `unique gift info preserves positional prefixes and round trips appended text fields`() {
        val uniqueGift = nonstrictJsonFormat.decodeFromString(
            GiftSentOrReceivedEvent.UniqueGift,
            """{"gift":{"base_name":"Base","name":"unique","number":1,"model":{"name":"Model","sticker":{"file_id":"model-file","file_unique_id":"model-unique","type":"regular","width":1,"height":1},"rarity_per_mille":1},"symbol":{"name":"Symbol","sticker":{"file_id":"symbol-file","file_unique_id":"symbol-unique","type":"regular","width":1,"height":1},"rarity_per_mille":1},"backdrop":{"name":"Backdrop","colors":{"center_color":1,"edge_color":2,"symbol_color":3,"text_color":4},"rarity_per_mille":1}}}"""
        )

        val commonPrimary = GiftSentOrReceivedEvent.UniqueGift.Common(
            uniqueGift.gift,
            GiftSentOrReceivedEvent.UniqueGift.Origin.Upgrade,
            1,
            "TON",
            2L,
            3,
            TelegramDate.Start
        )
        val commonSecondary = GiftSentOrReceivedEvent.UniqueGift.Common(
            uniqueGift.gift,
            "transfer",
            4,
            "USD",
            5L,
            6,
            TelegramDate.Start
        )
        val businessPrimary = GiftSentOrReceivedEvent.UniqueGift.ReceivedInBusinessAccount(
            uniqueGift.gift,
            GiftId("owned-primary"),
            GiftSentOrReceivedEvent.UniqueGift.Origin.Resale,
            7,
            "EUR",
            8L,
            9,
            TelegramDate.Start
        )
        val businessSecondary = GiftSentOrReceivedEvent.UniqueGift.ReceivedInBusinessAccount(
            uniqueGift.gift,
            GiftId("owned-secondary"),
            "offer",
            10,
            "XTR",
            11L,
            12,
            TelegramDate.Start
        )

        assertEquals(3, commonPrimary.transferStarCount)
        assertEquals(6, commonSecondary.transferStarCount)
        assertEquals(9, businessPrimary.transferStarCount)
        assertEquals(12, businessSecondary.transferStarCount)

        val publicFactoryCommon = GiftSentOrReceivedEvent.UniqueGift(
            uniqueGift.gift,
            "upgrade",
            null,
            13
        )
        val publicFactoryBusiness = GiftSentOrReceivedEvent.UniqueGift(
            uniqueGift.gift,
            "resale",
            GiftId("owned-factory"),
            14
        )

        assertEquals(
            13,
            assertIs<GiftSentOrReceivedEvent.UniqueGift.Common>(publicFactoryCommon).transferStarCount
        )
        assertEquals(
            14,
            assertIs<GiftSentOrReceivedEvent.UniqueGift.ReceivedInBusinessAccount>(publicFactoryBusiness).transferStarCount
        )

        val withAppendedFields = GiftSentOrReceivedEvent.UniqueGift(
            gift = uniqueGift.gift,
            text = "bold",
            textSources = listOf(boldTextSource("bold")),
            isPrivate = true
        )
        val roundTripped = nonstrictJsonFormat.decodeFromString(
            GiftSentOrReceivedEvent.UniqueGift,
            nonstrictJsonFormat.encodeToString(GiftSentOrReceivedEvent.UniqueGift, withAppendedFields)
        )

        assertEquals("bold", roundTripped.text)
        assertEquals(true, roundTripped.isPrivate)
        assertEquals(listOf(boldTextSource("bold")), roundTripped.textSources)
    }
}
