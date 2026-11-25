package dev.inmo.tgbotapi.extensions.api

import com.sun.net.httpserver.HttpServer
import dev.inmo.tgbotapi.bot.ktor.telegramBot
import dev.inmo.tgbotapi.extensions.api.bot.getMe
import dev.inmo.tgbotapi.extensions.api.bot.setMySettings
import dev.inmo.tgbotapi.extensions.api.verifications.verifyUser
import dev.inmo.tgbotapi.types.CallbackQueryMode
import dev.inmo.tgbotapi.types.Username
import dev.inmo.tgbotapi.types.message.textsources.linkTextSource
import dev.inmo.tgbotapi.types.toChatId
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.*
import java.net.InetSocketAddress
import java.util.concurrent.ConcurrentHashMap
import kotlin.test.Test
import kotlin.test.assertEquals

class BotSettingsHttpTest {
    @Test
    fun publicExtensionsUseTestServerRoutesAndTelegramJson() = runBlocking {
        val requests = ConcurrentHashMap<String, JsonObject>()
        val server = HttpServer.create(InetSocketAddress("127.0.0.1", 0), 0)
        server.createContext("/") { exchange ->
            exchange.use {
                val path = exchange.requestURI.path
                val body = exchange.requestBody.bufferedReader().use { it.readText() }
                requests[path] = Json.parseToJsonElement(body).jsonObject
                val result = when (path) {
                    "/botfixture/test/getMe" ->
                        """{"id":4,"is_bot":true,"first_name":"Fixture","active_usernames":["fixture_ai"],"has_topic_sidebar":true}"""
                    else -> "true"
                }
                val response = """{"ok":true,"result":$result}""".encodeToByteArray()
                exchange.responseHeaders.add("Content-Type", "application/json")
                exchange.sendResponseHeaders(200, response.size.toLong())
                exchange.responseBody.use { it.write(response) }
            }
        }
        server.start()
        try {
            val bot = telegramBot("fixture", apiUrl = "http://127.0.0.1:${server.address.port}", testServer = true)
            try {
                assertEquals(listOf(Username("@fixture_ai")), bot.getMe().activeUsernames)
                bot.setMySettings(hasTopicSidebar = false, callbackQueryMode = CallbackQueryMode.Auto)
                bot.verifyUser(4L.toChatId(), listOf(linkTextSource("Details", "https://example.org")))
            } finally {
                bot.close()
            }
            assertEquals(Json.parseToJsonElement("""{"has_topic_sidebar":false,"callback_query_mode":"auto"}"""),
                requests.getValue("/botfixture/test/setMySettings"))
            val description = requests.getValue("/botfixture/test/verifyUser")
            assertEquals(JsonPrimitive("Details"), description["custom_description"])
            assertEquals(JsonPrimitive("text_link"), description.getValue("custom_description_entities").jsonArray.single().jsonObject["type"])
        } finally {
            server.stop(0)
        }
    }
}
