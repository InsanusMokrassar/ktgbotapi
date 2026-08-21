package dev.inmo.tgbotapi.types

import dev.inmo.tgbotapi.TestsJsonFormat
import kotlinx.serialization.Serializable
import kotlin.test.*

private val chatIdentifierChatId: RawChatId = RawChatId(123L)
private val chatIdentifierLink = "tg://user?id=$chatIdentifierChatId"
private const val testUsername = "@Example"

class ChatIdentifierTests {
    @Test
    fun `Cast_from_Int_to_ChatId_is_working_correctly`() {
        val chatId = chatIdentifierChatId.long.toInt().toChatId()
        assertEquals(chatIdentifierChatId, chatId.chatId)
    }
    @Test
    fun `Cast_from_Byte_to_ChatId_is_working_correctly`() {
        val chatId = chatIdentifierChatId.long.toByte().toChatId()
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
        assertEquals(testUsername, testUsername.toUsername().full)

        assertEquals(testUsername, testUsername.replace("@", "").toUsername().full)
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

    @Test
    fun `EphemeralChatId_is_serialized_as_bare_long_by_ChatIdentifierSerializer`() {
        val ephemeralChatId = EphemeralChatId(chatIdentifierChatId, ChatId(RawChatId(456L)))
        val stringified = TestsJsonFormat.encodeToString(ChatIdentifierSerializer, ephemeralChatId as ChatIdentifier)
        assertEquals("$chatIdentifierChatId", stringified)
        val deserialized = TestsJsonFormat.decodeFromString(ChatIdentifierSerializer, stringified)
        assertEquals(ChatId(chatIdentifierChatId), deserialized)
    }

    @Test
    fun `EphemeralChatId_round_trips_through_FullChatIdentifierSerializer_with_ephemeralMessageId`() {
        val ephemeralChatId = EphemeralChatId(chatIdentifierChatId, ChatId(RawChatId(456L)), EphemeralMessageId(789L))
        val stringified = TestsJsonFormat.encodeToString(FullChatIdentifierSerializer, ephemeralChatId as ChatIdentifier)
        assertEquals("\"$chatIdentifierChatId/eph/456/789\"", stringified)
        val deserialized = TestsJsonFormat.decodeFromString(FullChatIdentifierSerializer, stringified)
        assertEquals(ephemeralChatId, deserialized)
    }

    @Test
    fun `EphemeralChatId_round_trips_through_FullChatIdentifierSerializer_without_ephemeralMessageId`() {
        val ephemeralChatId = EphemeralChatId(chatIdentifierChatId, ChatId(RawChatId(456L)))
        val stringified = TestsJsonFormat.encodeToString(FullChatIdentifierSerializer, ephemeralChatId as ChatIdentifier)
        assertEquals("\"$chatIdentifierChatId/eph/456/\"", stringified)
        val deserialized = TestsJsonFormat.decodeFromString(FullChatIdentifierSerializer, stringified)
        assertEquals(ephemeralChatId, deserialized)
    }
}
