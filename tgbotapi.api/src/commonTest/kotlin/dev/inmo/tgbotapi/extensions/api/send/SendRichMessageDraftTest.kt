package dev.inmo.tgbotapi.extensions.api.send

import dev.inmo.tgbotapi.bot.RequestsExecutor
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.requests.send.SendRichMessageDraft
import dev.inmo.tgbotapi.types.ChatIdWithThreadId
import dev.inmo.tgbotapi.types.IdChatIdentifier
import dev.inmo.tgbotapi.types.MessageThreadId
import dev.inmo.tgbotapi.types.RawChatId
import dev.inmo.tgbotapi.types.rich.InputRichMessageHTML
import kotlin.coroutines.Continuation
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.startCoroutine
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertSame

class SendRichMessageDraftTest {
    private object RequestCaptured : Throwable()

    private class CapturingBot : RequestsExecutor {
        lateinit var request: Request<*>

        override suspend fun <T : Any> execute(request: Request<T>): T {
            this.request = request
            throw RequestCaptured
        }

        override fun close() = Unit
    }

    private fun CapturingBot.capture(block: suspend CapturingBot.() -> Unit): Request<*> {
        var completion: Result<Unit>? = null
        block.startCoroutine(
            this,
            object : Continuation<Unit> {
                override val context = EmptyCoroutineContext

                override fun resumeWith(result: Result<Unit>) {
                    completion = result
                }
            }
        )

        val completed = completion ?: error("Request coroutine did not complete synchronously")
        assertSame(RequestCaptured, completed.exceptionOrNull())
        return request
    }

    @Test
    fun directExtensionAcceptsIdChatIdentifier() {
        val chatId: IdChatIdentifier = ChatIdWithThreadId(RawChatId(1L), MessageThreadId(2L))
        val request = assertIs<SendRichMessageDraft>(
            CapturingBot().capture {
                sendRichMessageDraft(chatId, 3L, InputRichMessageHTML("direct"))
            }
        )

        assertEquals(chatId, request.chatId)
        assertEquals(MessageThreadId(2L), request.threadId)
    }

    @Test
    fun genericExtensionAcceptsIdChatIdentifier() {
        val chatId: IdChatIdentifier = ChatIdWithThreadId(RawChatId(4L), MessageThreadId(5L))
        val request = assertIs<SendRichMessageDraft>(
            CapturingBot().capture {
                send(chatId, 6L, InputRichMessageHTML("generic"))
            }
        )

        assertEquals(chatId, request.chatId)
        assertEquals(MessageThreadId(5L), request.threadId)
    }
}
