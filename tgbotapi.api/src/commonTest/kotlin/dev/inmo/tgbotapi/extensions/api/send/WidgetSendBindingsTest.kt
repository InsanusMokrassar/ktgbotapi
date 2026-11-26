package dev.inmo.tgbotapi.extensions.api.send

import dev.inmo.tgbotapi.bot.RequestsExecutor
import dev.inmo.tgbotapi.extensions.api.edit.caption.editMessageCaption
import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.requests.edit.caption.EditChatMessageCaption
import dev.inmo.tgbotapi.requests.send.SendSharedWidget
import dev.inmo.tgbotapi.requests.send.SendWidget
import dev.inmo.tgbotapi.requests.send.media.SendPhoto
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent.InputSharedWidgetMessageContent
import dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent.InputWidgetMessageContent
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.PhotoContent
import dev.inmo.tgbotapi.types.message.content.WidgetContent
import dev.inmo.tgbotapi.types.message.textsources.boldTextSource
import dev.inmo.tgbotapi.types.widgets.WidgetId
import kotlinx.serialization.json.Json
import kotlin.coroutines.Continuation
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.startCoroutine
import kotlin.test.*

class WidgetSendBindingsTest {
    private class Captured(val request: Request<*>) : RuntimeException()
    private val bot = object : RequestsExecutor {
        override suspend fun <T : Any> execute(request: Request<T>): T = throw Captured(request)
        override fun close() = Unit
    }

    private fun <T> runImmediately(block: suspend () -> T): T {
        var result: Result<T>? = null
        block.startCoroutine(Continuation(EmptyCoroutineContext) { result = it })
        return requireNotNull(result).getOrThrow()
    }

    private fun capture(block: suspend RequestsExecutor.() -> Unit): Request<*> =
        assertFailsWith<Captured> { runImmediately { bot.block() } }.request

    @Test
    fun directGenericAndReplyOverloadsForwardWidgetArguments() {
        val chat = ChatId(RawChatId(50))
        val direct = assertIs<SendWidget>(capture {
            sendWidget(chat, "<b>A</b>", chatData = "A", text = "caption", allowQueries = true, threadId = MessageThreadId(4))
        })
        assertEquals(MessageThreadId(4), direct.threadId)
        assertEquals("A", direct.chatData)
        assertEquals("caption", direct.text)
        assertTrue(direct.allowQueries)
        val id = WidgetId("shared")
        val generic = assertIs<SendSharedWidget>(capture {
            send(chat, InputSharedWidgetMessageContent(id, chatData = "B"), allowPaidBroadcast = true)
        })
        assertEquals(id, generic.widgetId)
        assertEquals("B", generic.chatData)
        assertTrue(generic.allowPaidBroadcast)

        val reply = assertIs<SendWidget>(capture {
            reply(chat, MessageId(5), InputWidgetMessageContent("C", isShared = true),
                replyInThreadId = MessageThreadId(7), allowSendingWithoutReply = true)
        })
        assertEquals(MessageThreadId(7), reply.threadId)
        assertTrue(reply.isShared)
        val target = assertIs<ReplyParameters.Chat>(reply.replyParameters)
        assertEquals(MessageId(5), target.messageId)
        assertEquals(true, target.allowSendingWithoutReply)
    }

    @Test
    fun captionEditsReturnWidgetAndPhotoMessagesThroughTheirNormalBindings() = runImmediately {
        val json = Json { ignoreUnknownKeys = true }
        val chatId = ChatId(RawChatId(50))
        val widgetField = """"widget":{"html":"<b>Widget</b>"}"""
        val photoField = """"photo":[{"file_id":"photo","file_unique_id":"unique","width":10,"height":10}]"""
        fun message(id: Int, field: String, caption: String) =
            """{"message_id":$id,"date":100,"chat":{"id":50,"type":"private","first_name":"Alice"},"from":{"id":7,"is_bot":false,"first_name":"Alice"},$field,"caption":"$caption"}"""

        val widget = json.decodeFromString(SendWidget(chatId, "<b>Widget</b>").resultDeserializer, message(1, widgetField, "Before"))
        val photo = json.decodeFromString(SendPhoto(chatId, FileId("photo")).resultDeserializer, message(2, photoField, "Before"))
        val editingBot = object : RequestsExecutor {
            override suspend fun <T : Any> execute(request: Request<T>): T {
                val resultDeserializer = request.resultDeserializer
                val edit = assertIs<EditChatMessageCaption>(request)
                assertEquals(chatId, edit.chatId)
                val response = if (edit.messageId == widget.messageId) message(1, widgetField, edit.text) else message(2, photoField, edit.text)
                return json.decodeFromString(resultDeserializer, response)
            }
            override fun close() = Unit
        }

        val byId = editingBot.editMessageCaption(chatId, widget.messageId, "By ID")
        assertEquals("By ID", assertIs<WidgetContent>(byId.content).text)
        val byText: ContentMessage<WidgetContent> = editingBot.editMessageCaption(widget, "New caption")
        assertEquals("<b>Widget</b>", byText.content.widget.html)
        val byEntities: ContentMessage<WidgetContent> = editingBot.editMessageCaption(widget, listOf(boldTextSource("Formatted")))
        assertEquals("Formatted", byEntities.content.text)
        val editedPhoto: ContentMessage<PhotoContent> = editingBot.editMessageCaption(photo, "Photo caption")
        assertEquals(FileId("photo"), editedPhoto.content.media.fileId)
    }
}
