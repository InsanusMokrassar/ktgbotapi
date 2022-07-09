package dev.inmo.tgbotapi.types

import dev.inmo.tgbotapi.TestsJsonFormat
import kotlinx.serialization.Serializable
import kotlin.test.*

private const val chatIdentifierChatId: Identifier = 123L
private const val chatIdentifierLink = "tg://user?id=$chatIdentifierChatId"
private const val testUsername = "@Example"

class ChatIdentifierTests {
    @Test
    fun `Cast_from_Int_to_ChatId_is_working_correctly`() {
        val chatId = chatIdentifierChatId.toInt().toChatId()
        assertEquals(chatIdentifierChatId, chatId.chatId)
    }
    @Test
    fun `Cast_from_Byte_to_ChatId_is_working_correctly`() {
        val chatId = chatIdentifierChatId.toByte().toChatId()
        assertEquals(chatIdentifierChatId, chatId.chatId)
    }
    @Test
    fun `Cast_from_Identifier_to_ChatId_is_working_correctly`() {
        val chatId = chatIdentifierChatId.toChatId()
        assertEquals(chatIdentifierChatId, chatId.chatId)
    }

    @Test
    fun `Creating_link_from_ChatId_is_correct`() {
        val chatId = chatIdentifierChatId.toChatId()
        assertEquals(chatIdentifierLink, chatId.userLink)
    }

    @Test
    fun `Cast_from_String_to_Username_is_working_correctly`() {
        assertEquals(testUsername, testUsername.toUsername().username)

        assertFails("Username creating must fail when trying to create from string which is not starting from @ symbol") {
            testUsername.replace("@", "").toUsername().username
        }
    }

    @Serializable
    data class Example(
        val identifier: ChatIdentifier
    )

    @Test
    fun `Deserializing_from_String_must_work_correctly`() {

        Example(chatIdentifierChatId.toChatId()).let { withChatId ->
            val stringified = TestsJsonFormat.encodeToString(Example.serializer(), withChatId)
            assertEquals(stringified, "{\"identifier\":$chatIdentifierChatId}")
            val deserialized = TestsJsonFormat.decodeFromString(Example.serializer(), stringified)
            assertEquals(withChatId, deserialized)
        }

        Example(testUsername.toUsername()).let { withUsername ->
            val stringified = TestsJsonFormat.encodeToString(Example.serializer(), withUsername)
            assertEquals(stringified, "{\"identifier\":\"$testUsername\"}")
            val deserialized = TestsJsonFormat.decodeFromString(Example.serializer(), stringified)
            assertEquals(withUsername, deserialized)
        }

        // Replace @ by empty string, because from time to time we can retrieve from Telegram system
        // username without starting @ symbol
        Example(testUsername.toUsername()).let { withUsername ->
            val stringified = TestsJsonFormat.encodeToString(Example.serializer(), withUsername).replace("@", "")
            assertEquals("{\"identifier\":\"${testUsername.replace("@", "")}\"}", stringified)
            val deserialized = TestsJsonFormat.decodeFromString(Example.serializer(), stringified)
            assertEquals(withUsername, deserialized)
        }
    }
}
