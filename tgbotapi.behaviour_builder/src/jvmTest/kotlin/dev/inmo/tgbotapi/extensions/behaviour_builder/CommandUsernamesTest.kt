package dev.inmo.tgbotapi.extensions.behaviour_builder

import dev.inmo.tgbotapi.bot.RequestsExecutor
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.containsCommand
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.requests.bot.GetMe
import dev.inmo.tgbotapi.types.message.textsources.botCommandTextSource
import kotlinx.coroutines.*
import kotlinx.serialization.json.Json
import kotlin.test.*

class CommandUsernamesTest {
    @Test
    fun matchesPrimaryAndActiveUsernamesFromOneGetMeCall() = runBlocking {
        var calls = 0
        val bot = object : RequestsExecutor {
            override suspend fun <T : Any> execute(request: Request<T>): T {
                assertSame<Request<*>>(GetMe, request)
                calls++
                return Json.decodeFromString(request.resultDeserializer,
                    """{"id":1,"is_bot":true,"first_name":"Example","username":"example_bot","active_usernames":["example_ai"]}""")
            }

            override fun close() = Unit
        }
        val scope = CoroutineScope(Job())
        try {
            val context = BehaviourContext(bot, scope)
            suspend fun matches(command: String) = context.containsCommand(Regex("help"), listOf(botCommandTextSource(command)))

            assertTrue(matches("help"))
            assertEquals(0, calls)
            assertTrue(matches("help@EXAMPLE_AI"))
            assertTrue(matches("help@example_bot"))
            assertFalse(matches("help@different_bot"))
            assertFalse(matches("start@example_ai"))
            assertEquals(1, calls)
        } finally {
            scope.cancel()
        }
    }
}
