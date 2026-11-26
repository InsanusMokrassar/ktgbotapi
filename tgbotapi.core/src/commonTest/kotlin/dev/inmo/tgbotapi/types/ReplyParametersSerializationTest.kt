package dev.inmo.tgbotapi.types

import dev.inmo.tgbotapi.requests.abstracts.json
import dev.inmo.tgbotapi.requests.send.SendRichMessage
import dev.inmo.tgbotapi.requests.send.SendTextMessage
import dev.inmo.tgbotapi.types.checklists.ChecklistTaskId
import dev.inmo.tgbotapi.types.message.textsources.boldTextSource
import dev.inmo.tgbotapi.types.polls.PollOptionPersistentId
import dev.inmo.tgbotapi.types.rich.*
import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class ReplyParametersSerializationTest {
    private val json = Json { encodeDefaults = false }

    @Test
    fun chatRoundTripUsesFlatShape() {
        val value: ReplyParameters = ReplyParameters(
            ChatId(RawChatId(123L)),
            MessageId(456L),
            allowSendingWithoutReply = true
        )

        val encoded = json.encodeToString(value)
        val encodedObject = json.parseToJsonElement(encoded).jsonObject

        assertTrue("chat_id" in encodedObject)
        assertTrue("message_id" in encodedObject)
        assertFalse("ephemeral_message_id" in encodedObject)
        assertEquals(value, json.decodeFromString<ReplyParameters>(encoded))
    }

    @Test
    fun ephemeralRoundTripUsesFlatShape() {
        val value: ReplyParameters = ReplyParameters(
            ephemeralMessageId = EphemeralMessageId(789L),
            allowSendingWithoutReply = true,
            checklistTaskId = ChecklistTaskId(12u),
            pollOptionId = PollOptionPersistentId("option-id")
        )

        val encoded = json.encodeToString(value)
        val encodedObject = json.parseToJsonElement(encoded).jsonObject

        assertTrue("ephemeral_message_id" in encodedObject)
        assertTrue("checklist_task_id" in encodedObject)
        assertTrue("poll_option_id" in encodedObject)
        assertFalse("chat_id" in encodedObject)
        assertFalse("message_id" in encodedObject)
        assertEquals(value, json.decodeFromString<ReplyParameters>(encoded))
    }

    @Test
    fun currentChatPayloadWithoutChatIdDecodes() {
        val decoded = json.decodeFromString<ReplyParameters>("""{"message_id":456}""")

        assertEquals(
            ReplyParameters.Chat(chatIdentifier = null, messageId = MessageId(456L)),
            decoded
        )
    }

    @Test
    fun richQuoteSurvivesAnIncomingMessageAndAnOutgoingReply() {
        val richTexts = listOf(
            RichTextGroup(listOf(RichTextPlain("part "), RichTextBold(RichTextPlain("one")))),
            RichTextPlain("two")
        )
        val wireTexts = """[["part ",{"type":"bold","text":"one"}],"two"]"""
        val chatId = ChatId(RawChatId(123L))
        val richMessage = SendRichMessage(chatId, InputRichMessageHTML("<p>Reply</p>"))
        val received = json.decodeFromString(
            richMessage.resultDeserializer,
            """{
                "message_id":42, "date":23,
                "chat":{"id":123,"type":"private","first_name":"User"},
                "from":{"id":123,"is_bot":false,"first_name":"User"},
                "rich_message":{"blocks":[{"type":"paragraph","text":"Reply"}]},
                "quote":{"text":"","position":7,"rich_texts":$wireTexts}
            }"""
        )
        val quote = assertNotNull(received.content.quote)
        assertEquals("", quote.text)
        assertEquals(richTexts, quote.richTexts)
        assertEquals(7, quote.position)
        assertEquals(quote, json.decodeFromString<TextQuote>(json.encodeToString(quote)))

        val parameters = ReplyParameters(received, quotePosition = quote.position, quoteRichTexts = quote.richTexts)
        val request = SendTextMessage(chatId, "Answer", replyParameters = parameters)
        val encoded = request.json().getValue("reply_parameters").jsonObject
        assertEquals(json.parseToJsonElement(wireTexts), encoded["quote_rich_texts"])
        assertEquals("7", encoded.getValue("quote_position").jsonPrimitive.content)
        assertEquals(parameters, json.decodeFromJsonElement(ReplyParameters.Serializer, encoded))
    }

    @Test
    fun textQuoteEntityOffsetsAreRelativeToTheQuotedText() {
        val sources = listOf(boldTextSource("😀x"))
        val quote = TextQuote("😀x", position = 12, textSources = sources, isManual = true)
        val encoded = json.encodeToJsonElement(TextQuote.serializer(), quote).jsonObject
        val entity = encoded.getValue("entities").jsonArray.single().jsonObject

        assertEquals("12", encoded.getValue("position").jsonPrimitive.content)
        assertEquals("0", entity.getValue("offset").jsonPrimitive.content)
        assertEquals("3", entity.getValue("length").jsonPrimitive.content)
        assertEquals(sources, quote.textSources)
        assertFalse("rich_texts" in encoded)
    }

    @Test
    fun payloadWithBothTargetKindsIsRejected() {
        assertFailsWith<SerializationException> {
            json.decodeFromString<ReplyParameters>("""{"chat_id":123,"message_id":456,"ephemeral_message_id":789}""")
        }
    }

    @Test
    fun payloadWithoutTargetIsRejected() {
        assertFailsWith<SerializationException> {
            json.decodeFromString<ReplyParameters>("""{"allow_sending_without_reply":true}""")
        }
    }
}
