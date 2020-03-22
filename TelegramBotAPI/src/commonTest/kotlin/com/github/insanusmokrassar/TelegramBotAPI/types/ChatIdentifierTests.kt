package com.github.insanusmokrassar.TelegramBotAPI.types

import com.github.insanusmokrassar.TelegramBotAPI.utils.nonstrictJsonFormat
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.test.*

@ImplicitReflectionSerializer
private const val chatIdentifierChatId: Identifier = 123L
@ImplicitReflectionSerializer
private const val chatIdentifierLink = "tg://user?id=$chatIdentifierChatId"
@ImplicitReflectionSerializer
private const val testUsername = "@Example"

@ImplicitReflectionSerializer
class ChatIdentifierTests {
    @Test
    fun `Cast from Int to ChatId is working correctly`() {
        val chatId = chatIdentifierChatId.toInt().toChatId()
        assertEquals(chatIdentifierChatId, chatId.chatId)
    }
    @Test
    fun `Cast from Byte to ChatId is working correctly`() {
        val chatId = chatIdentifierChatId.toByte().toChatId()
        assertEquals(chatIdentifierChatId, chatId.chatId)
    }
    @Test
    fun `Cast from Identifier to ChatId is working correctly`() {
        val chatId = chatIdentifierChatId.toChatId()
        assertEquals(chatIdentifierChatId, chatId.chatId)
    }

    @Test
    fun `Creating link from ChatId is correct`() {
        val chatId = chatIdentifierChatId.toChatId()
        assertEquals(chatIdentifierLink, chatId.link)
    }

    @Test
    fun `Cast from String to Username is working correctly`() {
        assertEquals(testUsername, testUsername.toUsername().username)

        assertFails("Username creating must fail when trying to create from string which is not starting from @ symbol") {
            testUsername.replace("@", "").toUsername().username
        }
    }


    @Test
    fun `Deserializing from String must work correctly`() {
        @Serializable
        data class Example(
            val identifier: ChatIdentifier
        )

        Example(chatIdentifierChatId.toChatId()).let { withChatId ->
            val stringified = nonstrictJsonFormat.stringify(Example.serializer(), withChatId)
            assertEquals(stringified, "{\"identifier\":$chatIdentifierChatId}")
            val deserialized = nonstrictJsonFormat.parse(Example.serializer(), stringified)
            assertEquals(withChatId, deserialized)
        }

        Example(testUsername.toUsername()).let { withUsername ->
            val stringified = nonstrictJsonFormat.stringify(Example.serializer(), withUsername)
            assertEquals(stringified, "{\"identifier\":\"$testUsername\"}")
            val deserialized = nonstrictJsonFormat.parse(Example.serializer(), stringified)
            assertEquals(withUsername, deserialized)
        }

        // Replace @ by empty string, because from time to time we can retrieve from Telegram system
        // username without starting @ symbol
        Example(testUsername.toUsername()).let { withUsername ->
            val stringified = nonstrictJsonFormat.stringify(Example.serializer(), withUsername).replace("@", "")
            assertEquals("{\"identifier\":\"${testUsername.replace("@", "")}\"}", stringified)
            val deserialized = nonstrictJsonFormat.parse(Example.serializer(), stringified)
            assertEquals(withUsername, deserialized)
        }
    }
}
