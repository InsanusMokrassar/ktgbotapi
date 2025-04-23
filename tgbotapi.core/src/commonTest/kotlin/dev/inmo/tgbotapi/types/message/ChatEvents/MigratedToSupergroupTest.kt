package dev.inmo.tgbotapi.types.message.ChatEvents

import dev.inmo.tgbotapi.TestsJsonFormat
import dev.inmo.tgbotapi.extensions.utils.*
import dev.inmo.tgbotapi.types.IdChatIdentifier
import dev.inmo.tgbotapi.types.RawChatId
import dev.inmo.tgbotapi.types.update.abstracts.UpdateDeserializationStrategy
import kotlin.test.Test
import kotlin.test.assertEquals

class MigratedToSupergroupTest {
    @Test
    fun MigratedToSupergroupEventShouldBeParsed() {
        val payload = """
            {
              "update_id": 42,
              "message": {
                "message_id": 1,
                "from": {
                  "id": 1087968824,
                  "is_bot": true,
                  "first_name": "Group",
                  "username": "GroupAnonymousBot"
                },
                "sender_chat": {
                  "id": 42,
                  "title": "MigratedToSupergroupTest",
                  "type": "supergroup"
                },
                "chat": {
                  "id": 42,
                  "title": "MigratedToSupergroupTest",
                  "type": "supergroup"
                },
                "date": 1639955462,
                "migrate_from_chat_id": 57005
              }
            }
        """.trimIndent()
        val update = TestsJsonFormat.decodeFromString(UpdateDeserializationStrategy, payload)
        val message = update.messageUpdateOrThrow()
        val data = message.data.supergroupEventMessageOrThrow()
        val event = data.chatEvent.migratedToSupergroupOrThrow()

        assertEquals(IdChatIdentifier(RawChatId(57005)), event.migratedFrom)
    }
}
