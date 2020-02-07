package com.github.insanusmokrassar.TelegramBotAPI.requests.send

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.abstracts.SendChatMessageRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.actions.*
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import kotlinx.serialization.*
import kotlinx.serialization.internal.BooleanSerializer

/**
 * Send notification to user which will be shown for 5 seconds or while user have no messages from bot
 */
@Serializable
data class SendAction(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(actionField)
    val action: BotAction
): SendChatMessageRequest<Boolean> {
    override fun method(): String = "sendChatAction"
    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = BooleanSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}


suspend fun RequestsExecutor.sendBotAction(
    chatId: ChatIdentifier,
    action: BotAction
) = execute(
    SendAction(chatId, action)
)

suspend fun RequestsExecutor.sendBotAction(
    chat: Chat,
    action: BotAction
) = sendBotAction(chat.id, action)


suspend fun RequestsExecutor.sendActionTyping(
    chatId: ChatIdentifier
) = sendBotAction(chatId, TypingAction)

suspend fun RequestsExecutor.sendActionUploadPhoto(
    chatId: ChatIdentifier
) = sendBotAction(chatId, UploadPhotoAction)

suspend fun RequestsExecutor.sendActionRecordVideo(
    chatId: ChatIdentifier
) = sendBotAction(chatId, RecordVideoAction)

suspend fun RequestsExecutor.sendActionUploadVideo(
    chatId: ChatIdentifier
) = sendBotAction(chatId, UploadVideoAction)

suspend fun RequestsExecutor.sendActionRecordAudio(
    chatId: ChatIdentifier
) = sendBotAction(chatId, RecordAudioAction)

suspend fun RequestsExecutor.sendActionUploadAudio(
    chatId: ChatIdentifier
) = sendBotAction(chatId, UploadAudioAction)

suspend fun RequestsExecutor.sendActionUploadDocument(
    chatId: ChatIdentifier
) = sendBotAction(chatId, UploadDocumentAction)

suspend fun RequestsExecutor.sendActionFindLocation(
    chatId: ChatIdentifier
) = sendBotAction(chatId, FindLocationAction)

suspend fun RequestsExecutor.sendActionRecordVideoNote(
    chatId: ChatIdentifier
) = sendBotAction(chatId, RecordVideoNoteAction)

suspend fun RequestsExecutor.sendActionUploadVideoNote(
    chatId: ChatIdentifier
) = sendBotAction(chatId, UploadVideoNoteAction)


suspend fun RequestsExecutor.sendActionTyping(
    chat: Chat
) = sendBotAction(chat, TypingAction)

suspend fun RequestsExecutor.sendActionUploadPhoto(
    chat: Chat
) = sendBotAction(chat, UploadPhotoAction)

suspend fun RequestsExecutor.sendActionRecordVideo(
    chat: Chat
) = sendBotAction(chat, RecordVideoAction)

suspend fun RequestsExecutor.sendActionUploadVideo(
    chat: Chat
) = sendBotAction(chat, UploadVideoAction)

suspend fun RequestsExecutor.sendActionRecordAudio(
    chat: Chat
) = sendBotAction(chat, RecordAudioAction)

suspend fun RequestsExecutor.sendActionUploadAudio(
    chat: Chat
) = sendBotAction(chat, UploadAudioAction)

suspend fun RequestsExecutor.sendActionUploadDocument(
    chat: Chat
) = sendBotAction(chat, UploadDocumentAction)

suspend fun RequestsExecutor.sendActionFindLocation(
    chat: Chat
) = sendBotAction(chat, FindLocationAction)

suspend fun RequestsExecutor.sendActionRecordVideoNote(
    chat: Chat
) = sendBotAction(chat, RecordVideoNoteAction)

suspend fun RequestsExecutor.sendActionUploadVideoNote(
    chat: Chat
) = sendBotAction(chat, UploadVideoNoteAction)

