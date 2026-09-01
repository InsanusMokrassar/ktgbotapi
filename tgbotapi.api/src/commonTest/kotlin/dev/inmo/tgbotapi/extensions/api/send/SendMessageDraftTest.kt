package dev.inmo.tgbotapi.extensions.api.send

import dev.inmo.tgbotapi.bot.RequestsExecutor
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.requests.send.SendMessageDraft
import dev.inmo.tgbotapi.types.ChatIdWithChannelDirectMessageThreadId
import dev.inmo.tgbotapi.types.DirectMessageThreadId
import dev.inmo.tgbotapi.types.DraftId
import dev.inmo.tgbotapi.types.IdChatIdentifier
import dev.inmo.tgbotapi.types.RawChatId
import kotlin.coroutines.Continuation
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.startCoroutine
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertSame

class SendMessageDraftTest {
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
    fun directExtensionForwardsDirectMessageThreadId() {
        val chatId: IdChatIdentifier = ChatIdWithChannelDirectMessageThreadId(
            RawChatId(1L),
            DirectMessageThreadId(2L)
        )
        val request = assertIs<SendMessageDraft>(
            CapturingBot().capture {
                sendMessageDraft(
                    chatId,
                    DraftId(3L),
                    "direct",
                    directMessageThreadId = DirectMessageThreadId(7L)
                )
            }
        )

        assertEquals(chatId, request.chatId)
        assertEquals(DirectMessageThreadId(7L), request.directMessageThreadId)
    }

    @Test
    fun genericExtensionForwardsDirectMessageThreadId() {
        val chatId: IdChatIdentifier = ChatIdWithChannelDirectMessageThreadId(
            RawChatId(4L),
            DirectMessageThreadId(5L)
        )
        val request = assertIs<SendMessageDraft>(
            CapturingBot().capture {
                send(chatId, DraftId(6L), "generic")
            }
        )

        assertEquals(chatId, request.chatId)
        assertEquals(DirectMessageThreadId(5L), request.directMessageThreadId)
    }
}
