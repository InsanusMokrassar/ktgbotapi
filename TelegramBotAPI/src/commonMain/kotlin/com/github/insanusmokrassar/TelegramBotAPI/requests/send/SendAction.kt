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


@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.sendBotAction(
    chatId: ChatIdentifier,
    action: BotAction
) = execute(
    SendAction(chatId, action)
)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.sendBotAction(
    chat: Chat,
    action: BotAction
) = sendBotAction(chat.id, action)


@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.sendActionTyping(
    chatId: ChatIdentifier
) = sendBotAction(chatId, TypingAction)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.sendActionUploadPhoto(
    chatId: ChatIdentifier
) = sendBotAction(chatId, UploadPhotoAction)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.sendActionRecordVideo(
    chatId: ChatIdentifier
) = sendBotAction(chatId, RecordVideoAction)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.sendActionUploadVideo(
    chatId: ChatIdentifier
) = sendBotAction(chatId, UploadVideoAction)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.sendActionRecordAudio(
    chatId: ChatIdentifier
) = sendBotAction(chatId, RecordAudioAction)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.sendActionUploadAudio(
    chatId: ChatIdentifier
) = sendBotAction(chatId, UploadAudioAction)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.sendActionUploadDocument(
    chatId: ChatIdentifier
) = sendBotAction(chatId, UploadDocumentAction)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.sendActionFindLocation(
    chatId: ChatIdentifier
) = sendBotAction(chatId, FindLocationAction)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.sendActionRecordVideoNote(
    chatId: ChatIdentifier
) = sendBotAction(chatId, RecordVideoNoteAction)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.sendActionUploadVideoNote(
    chatId: ChatIdentifier
) = sendBotAction(chatId, UploadVideoNoteAction)


@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.sendActionTyping(
    chat: Chat
) = sendBotAction(chat, TypingAction)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.sendActionUploadPhoto(
    chat: Chat
) = sendBotAction(chat, UploadPhotoAction)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.sendActionRecordVideo(
    chat: Chat
) = sendBotAction(chat, RecordVideoAction)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.sendActionUploadVideo(
    chat: Chat
) = sendBotAction(chat, UploadVideoAction)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.sendActionRecordAudio(
    chat: Chat
) = sendBotAction(chat, RecordAudioAction)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.sendActionUploadAudio(
    chat: Chat
) = sendBotAction(chat, UploadAudioAction)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.sendActionUploadDocument(
    chat: Chat
) = sendBotAction(chat, UploadDocumentAction)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.sendActionFindLocation(
    chat: Chat
) = sendBotAction(chat, FindLocationAction)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.sendActionRecordVideoNote(
    chat: Chat
) = sendBotAction(chat, RecordVideoNoteAction)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.sendActionUploadVideoNote(
    chat: Chat
) = sendBotAction(chat, UploadVideoNoteAction)

