package dev.inmo.tgbotapi.types

import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
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
        val value: ReplyParameters = ReplyParameters.Ephemeral(EphemeralMessageId(789L), true)

        val encoded = json.encodeToString(value)
        val encodedObject = json.parseToJsonElement(encoded).jsonObject

        assertTrue("ephemeral_message_id" in encodedObject)
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
