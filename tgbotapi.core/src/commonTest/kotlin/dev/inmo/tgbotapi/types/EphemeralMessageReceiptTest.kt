package dev.inmo.tgbotapi.types

import dev.inmo.tgbotapi.requests.send.SendTextMessage
import dev.inmo.tgbotapi.types.message.abstracts.ChannelContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.PossiblyEphemeralMessage
import dev.inmo.tgbotapi.types.message.abstracts.PrivateContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.PrivateForumContentMessage
import dev.inmo.tgbotapi.utils.nonstrictJsonFormat
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertNull

class EphemeralMessageReceiptTest {
    private val privateChat = """{"id":42,"type":"private","first_name":"Recipient"}"""
    private val channel = """{"id":-1000000000001,"type":"channel","title":"Test channel"}"""
    private val receiver = """{"id":42,"is_bot":false,"first_name":"Recipient"}"""
    private val ephemeralFields = ""","receiver_user":$receiver,"ephemeral_message_id":37"""
    private val topicFields = ""","is_topic_message":true,"message_thread_id":11"""
    private val send = SendTextMessage(
        text = "Test",
        ephemeralMessageParameters = EphemeralMessageParameters(callbackQueryId = CallbackQueryId("press"))
    )

    private fun receipt(chat: String, fields: String) = nonstrictJsonFormat.decodeFromString(
        send.resultDeserializer,
        """{"message_id":0,"date":1,"chat":$chat,"from":{"id":55,"is_bot":true,"first_name":"Bot"},"text":"Test"$fields}"""
    )

    private fun assertEphemeral(message: PossiblyEphemeralMessage) {
        assertEquals(MessageId(0L), message.messageId)
        assertEquals(42L.toChatId(), message.receiverUser?.id)
        assertEquals(EphemeralMessageId(37L), message.ephemeralMessageId)
        assertEquals(ReplyParameters(EphemeralMessageId(37L)), message.ephemeralReplyParametersOrNull())
    }

    @Test
    fun privateAndChannelCallbacksRetainTheEphemeralRecipientAndIdentifier() {
        assertEphemeral(assertIs<PrivateContentMessage<*>>(receipt(privateChat, ephemeralFields)))
        val topic = assertIs<PrivateForumContentMessage<*>>(receipt(privateChat, topicFields + ephemeralFields))
        assertEquals(MessageThreadId(11L), topic.threadId)
        assertEphemeral(topic)
        assertEphemeral(assertIs<ChannelContentMessage<*>>(receipt(channel, ephemeralFields)))
    }

    @Test
    fun aZeroMessageIdWithoutEphemeralFieldsDoesNotIdentifyAnEphemeralMessage() {
        val ordinary = listOf(receipt(privateChat, ""), receipt(privateChat, topicFields), receipt(channel, ""))
        for (message in ordinary) {
            val metadata = assertIs<PossiblyEphemeralMessage>(message)
            assertNull(metadata.receiverUser)
            assertNull(metadata.ephemeralMessageId)
            assertNull(message.ephemeralReplyParametersOrNull())
        }
    }
}
