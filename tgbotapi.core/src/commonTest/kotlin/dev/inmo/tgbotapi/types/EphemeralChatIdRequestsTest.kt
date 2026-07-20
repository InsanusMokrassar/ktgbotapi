package dev.inmo.tgbotapi.types

import dev.inmo.tgbotapi.requests.DeleteEphemeralMessage
import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.requests.edit.caption.EditEphemeralMessageCaption
import dev.inmo.tgbotapi.requests.edit.media.EditEphemeralMessageMedia
import dev.inmo.tgbotapi.requests.edit.reply_markup.EditEphemeralMessageReplyMarkup
import dev.inmo.tgbotapi.requests.edit.text.EditEphemeralMessageText
import dev.inmo.tgbotapi.types.media.TelegramMediaPhoto
import kotlin.test.*

private val ephemeralChatIdRequestsChatId: RawChatId = RawChatId(111L)
private val ephemeralChatIdRequestsReceiverUser: UserId = ChatId(RawChatId(222L))
private val ephemeralChatIdRequestsMessageId = EphemeralMessageId(333L)
private val ephemeralChatIdWithMessageId = EphemeralChatId(
    ephemeralChatIdRequestsChatId,
    ephemeralChatIdRequestsReceiverUser,
    ephemeralChatIdRequestsMessageId
)
private val ephemeralChatIdWithoutMessageId = EphemeralChatId(
    ephemeralChatIdRequestsChatId,
    ephemeralChatIdRequestsReceiverUser
)
private val ephemeralChatIdRequestsMedia = TelegramMediaPhoto(FileId("photo_file_id"))

class EphemeralChatIdRequestsTest {
    @Test
    fun `DeleteEphemeralMessage_built_from_EphemeralChatId_delegates_correctly`() {
        val fromEphemeralChatId = DeleteEphemeralMessage(ephemeralChatIdWithMessageId)
        val fromExplicitArgs = DeleteEphemeralMessage(
            ephemeralChatIdWithMessageId,
            ephemeralChatIdRequestsReceiverUser,
            ephemeralChatIdRequestsMessageId
        )
        assertEquals(fromExplicitArgs, fromEphemeralChatId)

        assertFailsWith<IllegalArgumentException> {
            DeleteEphemeralMessage(ephemeralChatIdWithoutMessageId)
        }
    }

    @Test
    fun `EditEphemeralMessageMedia_built_from_EphemeralChatId_delegates_correctly`() {
        val fromEphemeralChatId = EditEphemeralMessageMedia(
            ephemeralChatIdWithMessageId,
            ephemeralChatIdRequestsMedia
        )
        val fromExplicitArgs = EditEphemeralMessageMedia(
            ephemeralChatIdWithMessageId,
            ephemeralChatIdRequestsReceiverUser,
            ephemeralChatIdRequestsMessageId,
            ephemeralChatIdRequestsMedia
        )
        assertEquals(fromExplicitArgs, fromEphemeralChatId)

        assertFailsWith<IllegalArgumentException> {
            EditEphemeralMessageMedia(ephemeralChatIdWithoutMessageId, ephemeralChatIdRequestsMedia)
        }
    }

    @Test
    fun `EditEphemeralMessageReplyMarkup_built_from_EphemeralChatId_delegates_correctly`() {
        val fromEphemeralChatId = EditEphemeralMessageReplyMarkup(ephemeralChatIdWithMessageId)
        val fromExplicitArgs = EditEphemeralMessageReplyMarkup(
            ephemeralChatIdWithMessageId,
            ephemeralChatIdRequestsReceiverUser,
            ephemeralChatIdRequestsMessageId
        )
        assertEquals(fromExplicitArgs, fromEphemeralChatId)

        assertFailsWith<IllegalArgumentException> {
            EditEphemeralMessageReplyMarkup(ephemeralChatIdWithoutMessageId)
        }
    }

    @Test
    fun `EditEphemeralMessageText_built_from_EphemeralChatId_delegates_correctly`() {
        val fromEphemeralChatId = EditEphemeralMessageText(ephemeralChatIdWithMessageId, "text")
        val fromExplicitArgs = EditEphemeralMessageText(
            ephemeralChatIdWithMessageId,
            ephemeralChatIdRequestsReceiverUser,
            ephemeralChatIdRequestsMessageId,
            "text"
        )
        assertEquals(fromExplicitArgs, fromEphemeralChatId)

        assertFailsWith<IllegalArgumentException> {
            EditEphemeralMessageText(ephemeralChatIdWithoutMessageId, "text")
        }
    }

    @Test
    fun `EditEphemeralMessageCaption_built_from_EphemeralChatId_delegates_correctly`() {
        val fromEphemeralChatId = EditEphemeralMessageCaption(ephemeralChatIdWithMessageId, "caption")
        val fromExplicitArgs = EditEphemeralMessageCaption(
            ephemeralChatIdWithMessageId,
            ephemeralChatIdRequestsReceiverUser,
            ephemeralChatIdRequestsMessageId,
            "caption"
        )
        assertEquals(fromExplicitArgs, fromEphemeralChatId)

        assertFailsWith<IllegalArgumentException> {
            EditEphemeralMessageCaption(ephemeralChatIdWithoutMessageId, "caption")
        }
    }
}
