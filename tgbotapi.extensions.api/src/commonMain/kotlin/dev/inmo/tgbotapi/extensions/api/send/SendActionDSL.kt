package dev.inmo.tgbotapi.extensions.api.send

import dev.inmo.micro_utils.coroutines.safely
import dev.inmo.micro_utils.coroutines.safelyWithoutExceptions
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.send.SendAction
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.actions.*
import dev.inmo.tgbotapi.types.chat.abstracts.Chat
import kotlinx.coroutines.*

private const val refreshTime: MilliSeconds = (botActionActualityTime - 1) * 1000L
typealias TelegramBotActionCallback<T> = suspend TelegramBot.() -> T

suspend fun <T> TelegramBot.withAction(
    actionRequest: SendAction,
    block: TelegramBotActionCallback<T>
): T {
    val botActionJob = supervisorScope {
        launch {
            while (isActive) {
                delay(refreshTime)
                safelyWithoutExceptions {
                    execute(actionRequest)
                }
            }
        }
    }
    return try {
        safely { block() }
    } finally {
        botActionJob.cancel()
    }
}

suspend fun <T> TelegramBot.withAction(
    chatId: ChatId,
    action: BotAction,
    block: TelegramBotActionCallback<T>
) = withAction(
    SendAction(chatId, action),
    block
)

suspend fun <T> TelegramBot.withAction(
    chat: Chat,
    action: BotAction,
    block: TelegramBotActionCallback<T>
) = withAction(
    chat.id,
    action,
    block
)

suspend fun <T> TelegramBot.withTypingAction(chatId: ChatId, block: TelegramBotActionCallback<T>) = withAction(chatId, TypingAction, block)
suspend fun <T> TelegramBot.withUploadPhotoAction(chatId: ChatId, block: TelegramBotActionCallback<T>) = withAction(chatId, UploadPhotoAction, block)
suspend fun <T> TelegramBot.withRecordVideoAction(chatId: ChatId, block: TelegramBotActionCallback<T>) = withAction(chatId, RecordVideoAction, block)
suspend fun <T> TelegramBot.withUploadVideoAction(chatId: ChatId, block: TelegramBotActionCallback<T>) = withAction(chatId, UploadVideoAction, block)
@Deprecated(
    "Deprecated according to https://core.telegram.org/bots/api-changelog#april-26-2021",
    ReplaceWith("withRecordVoiceAction", "dev.inmo.tgbotapi.extensions.api.send.withRecordVoiceAction")
)
suspend fun <T> TelegramBot.withRecordAudioAction(chatId: ChatId, block: TelegramBotActionCallback<T>) = withAction(chatId, RecordAudioAction, block)
@Deprecated(
    "Deprecated according to https://core.telegram.org/bots/api-changelog#april-26-2021",
    ReplaceWith("withUploadVoiceAction", "dev.inmo.tgbotapi.extensions.api.send.withUploadVoiceAction")
)
suspend fun <T> TelegramBot.withUploadAudioAction(chatId: ChatId, block: TelegramBotActionCallback<T>) = withAction(chatId, UploadAudioAction, block)
suspend fun <T> TelegramBot.withRecordVoiceAction(chatId: ChatId, block: TelegramBotActionCallback<T>) = withAction(chatId, RecordVoiceAction, block)
suspend fun <T> TelegramBot.withUploadVoiceAction(chatId: ChatId, block: TelegramBotActionCallback<T>) = withAction(chatId, UploadVoiceAction, block)
suspend fun <T> TelegramBot.withUploadDocumentAction(chatId: ChatId, block: TelegramBotActionCallback<T>) = withAction(chatId, UploadDocumentAction, block)
suspend fun <T> TelegramBot.withFindLocationAction(chatId: ChatId, block: TelegramBotActionCallback<T>) = withAction(chatId, FindLocationAction, block)
suspend fun <T> TelegramBot.withRecordVideoNoteAction(chatId: ChatId, block: TelegramBotActionCallback<T>) = withAction(chatId, RecordVideoNoteAction, block)
suspend fun <T> TelegramBot.withUploadVideoNoteAction(chatId: ChatId, block: TelegramBotActionCallback<T>) = withAction(chatId, UploadVideoNoteAction, block)


suspend fun <T> TelegramBot.withTypingAction(chat: Chat, block: TelegramBotActionCallback<T>) = withAction(chat, TypingAction, block)
suspend fun <T> TelegramBot.withUploadPhotoAction(chat: Chat, block: TelegramBotActionCallback<T>) = withAction(chat, UploadPhotoAction, block)
suspend fun <T> TelegramBot.withRecordVideoAction(chat: Chat, block: TelegramBotActionCallback<T>) = withAction(chat, RecordVideoAction, block)
suspend fun <T> TelegramBot.withUploadVideoAction(chat: Chat, block: TelegramBotActionCallback<T>) = withAction(chat, UploadVideoAction, block)
@Deprecated(
    "Deprecated according to https://core.telegram.org/bots/api-changelog#april-26-2021",
    ReplaceWith("withRecordVoiceAction", "dev.inmo.tgbotapi.extensions.api.send.withRecordVoiceAction")
)
suspend fun <T> TelegramBot.withRecordAudioAction(chat: Chat, block: TelegramBotActionCallback<T>) = withAction(chat, RecordAudioAction, block)
@Deprecated(
    "Deprecated according to https://core.telegram.org/bots/api-changelog#april-26-2021",
    ReplaceWith("withUploadVoiceAction", "dev.inmo.tgbotapi.extensions.api.send.withUploadVoiceAction")
)
suspend fun <T> TelegramBot.withUploadAudioAction(chat: Chat, block: TelegramBotActionCallback<T>) = withAction(chat, UploadAudioAction, block)
suspend fun <T> TelegramBot.withRecordVoiceAction(chat: Chat, block: TelegramBotActionCallback<T>) = withAction(chat, RecordVoiceAction, block)
suspend fun <T> TelegramBot.withUploadVoiceAction(chat: Chat, block: TelegramBotActionCallback<T>) = withAction(chat, UploadVoiceAction, block)
suspend fun <T> TelegramBot.withUploadDocumentAction(chat: Chat, block: TelegramBotActionCallback<T>) = withAction(chat, UploadDocumentAction, block)
suspend fun <T> TelegramBot.withFindLocationAction(chat: Chat, block: TelegramBotActionCallback<T>) = withAction(chat, FindLocationAction, block)
suspend fun <T> TelegramBot.withRecordVideoNoteAction(chat: Chat, block: TelegramBotActionCallback<T>) = withAction(chat, RecordVideoNoteAction, block)
suspend fun <T> TelegramBot.withUploadVideoNoteAction(chat: Chat, block: TelegramBotActionCallback<T>) = withAction(chat, UploadVideoNoteAction, block)
