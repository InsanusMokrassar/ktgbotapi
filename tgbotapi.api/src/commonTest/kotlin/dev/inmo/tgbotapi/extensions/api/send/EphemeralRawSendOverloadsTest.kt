package dev.inmo.tgbotapi.extensions.api.send

import dev.inmo.tgbotapi.bot.RequestsExecutor
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.requests.send.SendContact
import dev.inmo.tgbotapi.types.CallbackQueryId
import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.Contact
import dev.inmo.tgbotapi.types.EphemeralMessageParameters
import dev.inmo.tgbotapi.types.RawChatId
import kotlin.coroutines.Continuation
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.startCoroutine
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertSame
import kotlin.test.assertTrue

class EphemeralRawSendOverloadsTest {
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
        assertTrue(completed.isFailure)
        assertSame(RequestCaptured, completed.exceptionOrNull())
        return request
    }

    @Test
    fun rawDirectOverloadForwardsAllEphemeralParameters() {
        val chatId = ChatId(RawChatId(1L))
        val receiverUserId = ChatId(RawChatId(2L))
        val callbackQueryId = CallbackQueryId("direct-callback")
        val request = CapturingBot().capture {
            sendContact(
                chatId = chatId,
                phoneNumber = "+10000000000",
                firstName = "Direct",
                receiverUserId = receiverUserId,
                callbackQueryId = callbackQueryId,
                replaceCallbackQueryMessage = true
            )
        } as SendContact

        assertEquals(
            EphemeralMessageParameters(receiverUserId, callbackQueryId, true),
            request.ephemeralMessageParameters
        )
    }

    @Test
    fun rawGenericOverloadForwardsAllEphemeralParameters() {
        val chatId = ChatId(RawChatId(3L))
        val receiverUserId = ChatId(RawChatId(4L))
        val callbackQueryId = CallbackQueryId("generic-callback")
        val request = CapturingBot().capture {
            send(
                chatId = chatId,
                contact = Contact("+10000000001", "Generic"),
                receiverUserId = receiverUserId,
                callbackQueryId = callbackQueryId,
                replaceCallbackQueryMessage = false
            )
        } as SendContact

        assertEquals(
            EphemeralMessageParameters(receiverUserId, callbackQueryId, false),
            request.ephemeralMessageParameters
        )
    }

    @Test
    fun parameterObjectAndDefaultFormsRemainResolvable() {
        val chatId = ChatId(RawChatId(5L))
        val defaultRequest = CapturingBot().capture {
            sendContact(
                chatId = chatId,
                phoneNumber = "+10000000002",
                firstName = "Default"
            )
        } as SendContact
        assertNull(defaultRequest.ephemeralMessageParameters)

        val ephemeralMessageParameters = EphemeralMessageParameters(
            receiverUserId = ChatId(RawChatId(6L)),
            callbackQueryId = CallbackQueryId("object-callback"),
            replaceCallbackQueryMessage = true
        )
        val objectRequest = CapturingBot().capture {
            sendContact(
                chatId = chatId,
                phoneNumber = "+10000000003",
                firstName = "Object",
                ephemeralMessageParameters = ephemeralMessageParameters
            )
        } as SendContact

        assertEquals(ephemeralMessageParameters, objectRequest.ephemeralMessageParameters)
    }
}
