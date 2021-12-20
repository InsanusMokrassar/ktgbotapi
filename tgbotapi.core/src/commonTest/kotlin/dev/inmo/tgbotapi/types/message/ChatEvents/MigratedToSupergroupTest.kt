package dev.inmo.tgbotapi.types.message.ChatEvents

import dev.inmo.tgbotapi.TestsJsonFormat
import dev.inmo.tgbotapi.extensions.utils.asMessageUpdate
import dev.inmo.tgbotapi.extensions.utils.asMigratedToSupergroup
import dev.inmo.tgbotapi.extensions.utils.asSupergroupChatCreated
import dev.inmo.tgbotapi.extensions.utils.asSupergroupEventMessage
import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.update.abstracts.UpdateDeserializationStrategy
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.fail


class MigratedToSupergroupTest {
    @Test
    fun `MigratedToSupergroup event should be parsed`() {
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
        val message = update.asMessageUpdate() ?: fail("update should be of MessageUpdate subtype")
        val data = message.data.asSupergroupEventMessage() ?: fail("message should be of SupergroupEventMessage subtype")
        val event = data.chatEvent.asMigratedToSupergroup() ?: fail("event should be of SupergroupChatCreated subtype")

        assertEquals(ChatId(57005), event.migratedFrom)
    }
}