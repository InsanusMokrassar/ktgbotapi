package dev.inmo.tgbotapi.types

import dev.inmo.tgbotapi.requests.send.SendTextMessage
import dev.inmo.tgbotapi.requests.abstracts.asMultipartFile
import dev.inmo.tgbotapi.requests.abstracts.json
import dev.inmo.tgbotapi.requests.abstracts.MultipartRequest
import dev.inmo.tgbotapi.requests.chat.members.PromoteChatMember
import dev.inmo.tgbotapi.requests.edit.caption.EditEphemeralMessageCaption
import dev.inmo.tgbotapi.requests.edit.media.EditChatMessageMedia
import dev.inmo.tgbotapi.requests.edit.media.EditEphemeralMessageMedia
import dev.inmo.tgbotapi.requests.edit.text.EditEphemeralMessageRichText
import dev.inmo.tgbotapi.requests.edit.text.EditEphemeralMessageText
import dev.inmo.tgbotapi.requests.send.media.SendPaidMedia
import dev.inmo.tgbotapi.requests.send.media.SendVisualMediaGroup
import dev.inmo.tgbotapi.types.chat.member.ChatCommonAdministratorRights
import dev.inmo.tgbotapi.types.media.TelegramMediaPhoto
import dev.inmo.tgbotapi.types.media.TelegramMediaLivePhoto
import dev.inmo.tgbotapi.types.media.TelegramPaidMediaLivePhoto
import dev.inmo.tgbotapi.types.media.TelegramMediaVideo
import dev.inmo.tgbotapi.types.rich.InputRichMessageHTML
import dev.inmo.tgbotapi.types.rich.InputRichMessageMedia
import dev.inmo.tgbotapi.utils.RiskFeature
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertIs

class EphemeralMessageParametersTest {
    private val json = Json { encodeDefaults = true }

    @Test
    fun `SendTextMessage serializes nested ephemeral parameters without legacy fields`() {
        val request = SendTextMessage(
            chatId = ChatId(RawChatId(1L)),
            text = "text",
            ephemeralMessageParameters = EphemeralMessageParameters(
                receiverUserId = ChatId(RawChatId(2L)),
                callbackQueryId = CallbackQueryId("callback"),
                replaceCallbackQueryMessage = true
            )
        )
        val element = json.encodeToJsonElement(SendTextMessage.serializer(), request).jsonObject

        assertEquals("2", element[ephemeralMessageParametersField]!!.jsonObject[receiverUserIdField]!!.toString())
        assertFalse(receiverUserIdField in element)
        assertFalse(callbackQueryIdField in element)
    }

    @Test
    fun `SendTextMessage defaults ephemeral parameters from EphemeralChatId`() {
        val chatId = EphemeralChatId(
            chatId = RawChatId(1L),
            receiverUser = ChatId(RawChatId(2L))
        )

        assertEquals(
            EphemeralMessageParameters(ChatId(RawChatId(2L))),
            SendTextMessage(chatId, "text").ephemeralMessageParameters
        )
    }

    @Test
    fun `EditEphemeralMessageMedia accepts multipart files and exposes media map`() {
        val media = TelegramMediaPhoto("content".encodeToByteArray().asMultipartFile("photo.jpg"))
        val request = EditEphemeralMessageMedia(ChatId(RawChatId(1L)), ChatId(RawChatId(2L)), EphemeralMessageId(3L), media)

        assertEquals(setOf(media.file.fileId), request.mediaMap.keys)
    }

    @Test
    fun `EditEphemeralMessageMedia includes live photo and static photo multipart files`() {
        val media = TelegramMediaLivePhoto(
            file = "live photo".encodeToByteArray().asMultipartFile("live-photo.mov"),
            photo = "static photo".encodeToByteArray().asMultipartFile("photo.jpg")
        )
        val request = EditEphemeralMessageMedia(ChatId(RawChatId(1L)), ChatId(RawChatId(2L)), EphemeralMessageId(3L), media)

        assertEquals(setOf(media.file.fileId, media.photo.fileId), request.mediaMap.keys)
    }

    @Test
    fun `EditChatMessageMedia includes live photo and static photo multipart files`() {
        val media = TelegramMediaLivePhoto(
            file = "live photo".encodeToByteArray().asMultipartFile("live-photo.mov"),
            photo = "static photo".encodeToByteArray().asMultipartFile("photo.jpg")
        )
        val request = EditChatMessageMedia(ChatId(RawChatId(1L)), MessageId(3L), media)

        assertEquals(setOf(media.file.fileId, media.photo.fileId), request.mediaMap.keys)
    }

    @OptIn(RiskFeature::class)
    @Test
    fun `SendVisualMediaGroup includes live photo and static photo multipart files`() {
        val media = TelegramMediaLivePhoto(
            file = "live photo".encodeToByteArray().asMultipartFile("live-photo.mov"),
            photo = "static photo".encodeToByteArray().asMultipartFile("photo.jpg")
        )
        val request = SendVisualMediaGroup(ChatId(RawChatId(1L)), listOf(media, media))

        assertEquals(setOf(media.file.fileId, media.photo.fileId), assertIs<MultipartRequest<*>>(request).mediaMap.keys)
    }

    @Test
    fun `SendPaidMedia includes live photo and static photo multipart files`() {
        val media = TelegramPaidMediaLivePhoto(
            file = "live photo".encodeToByteArray().asMultipartFile("live-photo.mov"),
            photo = "static photo".encodeToByteArray().asMultipartFile("photo.jpg")
        )
        val request = SendPaidMedia(ChatId(RawChatId(1L)), 1, listOf(media))

        assertEquals(setOf(media.file.fileId, media.photo.fileId), assertIs<MultipartRequest<*>>(request).mediaMap.keys)
    }

    @Test
    fun `EditEphemeralMessageMedia includes multipart thumbnail and cover`() {
        val video = TelegramMediaVideo(
            file = "video".encodeToByteArray().asMultipartFile("video.mp4"),
            thumb = "thumbnail".encodeToByteArray().asMultipartFile("thumbnail.jpg"),
            cover = "cover".encodeToByteArray().asMultipartFile("cover.jpg")
        )
        val request = EditEphemeralMessageMedia(ChatId(RawChatId(1L)), ChatId(RawChatId(2L)), EphemeralMessageId(3L), video)

        assertEquals(
            setOf(video.file.fileId, requireNotNull(video.thumb).fileId, requireNotNull(video.cover).fileId),
            request.mediaMap.keys
        )
    }

    @Test
    fun `EditEphemeralMessageCaption serializes show caption above media`() {
        val request = EditEphemeralMessageCaption(
            ChatId(RawChatId(1L)), ChatId(RawChatId(2L)), EphemeralMessageId(3L), "caption", showCaptionAboveMedia = true
        )

        assertEquals("true", json.encodeToJsonElement(EditEphemeralMessageCaption.serializer(), request).jsonObject[showCaptionAboveMediaField].toString())
    }

    @Test
    fun `EditEphemeralMessageRichText serializes rich message without text`() {
        val request = EditEphemeralMessageRichText(
            ChatId(RawChatId(1L)), ChatId(RawChatId(2L)), EphemeralMessageId(3L), InputRichMessageHTML("<b>text</b>")
        )
        val element = request.json()

        assertFalse(textField in element)
        assertEquals("<b>text</b>", element[richMessageField]!!.jsonObject["html"].toString().removeSurrounding("\""))
    }

    @Test
    fun `EditEphemeralMessageRichText includes rich message multipart media`() {
        val media = TelegramMediaPhoto("photo".encodeToByteArray().asMultipartFile("photo.jpg"))
        val richMessage = InputRichMessageHTML(
            html = "<a href=\"tg://photo?id=photo\">photo</a>",
            media = listOf(InputRichMessageMedia("photo", media))
        )
        val request = EditEphemeralMessageRichText(
            ChatId(RawChatId(1L)), ChatId(RawChatId(2L)), EphemeralMessageId(3L), richMessage
        )

        assertEquals(setOf(media.file.fileId), request.mediaMap.keys)
    }

    @Test
    fun `EditEphemeralMessageText rejects absent text and rich message`() {
        assertFailsWith<IllegalArgumentException> {
            EditEphemeralMessageText(
                ChatId(RawChatId(1L)), ChatId(RawChatId(2L)), EphemeralMessageId(3L),
                null, null, null, null, null, null
            )
        }
    }

    @Test
    fun `ChatCommonAdministratorRights serializes welcome message right`() {
        val element = json.encodeToJsonElement(
            ChatCommonAdministratorRights.serializer(),
            ChatCommonAdministratorRights(canSendWelcomeMessages = true)
        ).jsonObject

        assertEquals("true", element[canSendWelcomeMessagesField].toString())
    }

    @Test
    fun `PromoteChatMember serializes welcome message right`() {
        val request = PromoteChatMember(
            chatId = ChatId(RawChatId(1L)),
            userId = ChatId(RawChatId(2L)),
            canSendWelcomeMessages = true
        )
        val element = json.encodeToJsonElement(PromoteChatMember.serializer(), request).jsonObject

        assertEquals("true", element[canSendWelcomeMessagesField].toString())
    }
}
