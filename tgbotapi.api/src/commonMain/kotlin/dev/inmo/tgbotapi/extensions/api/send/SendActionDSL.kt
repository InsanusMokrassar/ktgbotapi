package dev.inmo.tgbotapi.extensions.api.send

import dev.inmo.micro_utils.coroutines.safelyWithResult
import dev.inmo.micro_utils.coroutines.safelyWithoutExceptions
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.send.SendAction
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.actions.*
import dev.inmo.tgbotapi.types.chat.abstracts.Chat
import kotlinx.coroutines.*
import kotlin.contracts.*
import kotlin.coroutines.coroutineContext

private const val refreshTime: MilliSeconds = (botActionActualityTime - 1) * 1000L
typealias TelegramBotActionCallback<T> = suspend TelegramBot.() -> T

@OptIn(ExperimentalContracts::class)
suspend fun <T> TelegramBot.withAction(
    actionRequest: SendAction,
    block: TelegramBotActionCallback<T>
): T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    val botActionJob = CoroutineScope(coroutineContext).launch {
        while (isActive) {
            delay(refreshTime)
            safelyWithoutExceptions {
                execute(actionRequest)
            }
        }
    }
    val result = safelyWithResult { block() }
    botActionJob.cancel()
    return result.getOrThrow()
}

@OptIn(ExperimentalContracts::class)
suspend fun <T> TelegramBot.withAction(
    chatId: ChatId,
    action: BotAction,
    block: TelegramBotActionCallback<T>
): T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withAction(
        SendAction(chatId, action),
        block
    )
}

@OptIn(ExperimentalContracts::class)
suspend fun <T> TelegramBot.withAction(
    chat: Chat,
    action: BotAction,
    block: TelegramBotActionCallback<T>
): T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withAction(
        chat.id,
        action,
        block
    )
}

@OptIn(ExperimentalContracts::class)
suspend fun <T> TelegramBot.withTypingAction(chatId: ChatId, block: TelegramBotActionCallback<T>) : T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withAction(chatId, TypingAction, block)
}
@OptIn(ExperimentalContracts::class)
suspend fun <T> TelegramBot.withUploadPhotoAction(chatId: ChatId, block: TelegramBotActionCallback<T>) : T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withAction(chatId, UploadPhotoAction, block)
}
@OptIn(ExperimentalContracts::class)
suspend fun <T> TelegramBot.withRecordVideoAction(chatId: ChatId, block: TelegramBotActionCallback<T>) : T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withAction(chatId, RecordVideoAction, block)
}
@OptIn(ExperimentalContracts::class)
suspend fun <T> TelegramBot.withUploadVideoAction(chatId: ChatId, block: TelegramBotActionCallback<T>) : T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withAction(chatId, UploadVideoAction, block)
}
@OptIn(ExperimentalContracts::class)
suspend fun <T> TelegramBot.withRecordVoiceAction(chatId: ChatId, block: TelegramBotActionCallback<T>) : T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withAction(chatId, RecordVoiceAction, block)
}
@OptIn(ExperimentalContracts::class)
suspend fun <T> TelegramBot.withUploadVoiceAction(chatId: ChatId, block: TelegramBotActionCallback<T>) : T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withAction(chatId, UploadVoiceAction, block)
}
@OptIn(ExperimentalContracts::class)
suspend fun <T> TelegramBot.withUploadDocumentAction(chatId: ChatId, block: TelegramBotActionCallback<T>) : T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withAction(chatId, UploadDocumentAction, block)
}
@OptIn(ExperimentalContracts::class)
suspend fun <T> TelegramBot.withFindLocationAction(chatId: ChatId, block: TelegramBotActionCallback<T>) : T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withAction(chatId, FindLocationAction, block)
}
@OptIn(ExperimentalContracts::class)
suspend fun <T> TelegramBot.withRecordVideoNoteAction(chatId: ChatId, block: TelegramBotActionCallback<T>) : T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withAction(chatId, RecordVideoNoteAction, block)
}
@OptIn(ExperimentalContracts::class)
suspend fun <T> TelegramBot.withUploadVideoNoteAction(chatId: ChatId, block: TelegramBotActionCallback<T>) : T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withAction(chatId, UploadVideoNoteAction, block)
}
@OptIn(ExperimentalContracts::class)
suspend fun <T> TelegramBot.withChooseStickerAction(chatId: ChatId, block: TelegramBotActionCallback<T>) : T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withAction(chatId, ChooseStickerAction, block)
}


@OptIn(ExperimentalContracts::class)
suspend fun <T> TelegramBot.withTypingAction(chat: Chat, block: TelegramBotActionCallback<T>) : T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withAction(chat, TypingAction, block)
}
@OptIn(ExperimentalContracts::class)
suspend fun <T> TelegramBot.withUploadPhotoAction(chat: Chat, block: TelegramBotActionCallback<T>) : T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withAction(chat, UploadPhotoAction, block)
}
@OptIn(ExperimentalContracts::class)
suspend fun <T> TelegramBot.withRecordVideoAction(chat: Chat, block: TelegramBotActionCallback<T>) : T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withAction(chat, RecordVideoAction, block)
}
@OptIn(ExperimentalContracts::class)
suspend fun <T> TelegramBot.withUploadVideoAction(chat: Chat, block: TelegramBotActionCallback<T>) : T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withAction(chat, UploadVideoAction, block)
}
@OptIn(ExperimentalContracts::class)
suspend fun <T> TelegramBot.withRecordVoiceAction(chat: Chat, block: TelegramBotActionCallback<T>) : T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withAction(chat, RecordVoiceAction, block)
}
@OptIn(ExperimentalContracts::class)
suspend fun <T> TelegramBot.withUploadVoiceAction(chat: Chat, block: TelegramBotActionCallback<T>) : T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withAction(chat, UploadVoiceAction, block)
}
@OptIn(ExperimentalContracts::class)
suspend fun <T> TelegramBot.withUploadDocumentAction(chat: Chat, block: TelegramBotActionCallback<T>) : T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withAction(chat, UploadDocumentAction, block)
}
@OptIn(ExperimentalContracts::class)
suspend fun <T> TelegramBot.withFindLocationAction(chat: Chat, block: TelegramBotActionCallback<T>) : T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withAction(chat, FindLocationAction, block)
}
@OptIn(ExperimentalContracts::class)
suspend fun <T> TelegramBot.withRecordVideoNoteAction(chat: Chat, block: TelegramBotActionCallback<T>) : T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withAction(chat, RecordVideoNoteAction, block)
}
@OptIn(ExperimentalContracts::class)
suspend fun <T> TelegramBot.withUploadVideoNoteAction(chat: Chat, block: TelegramBotActionCallback<T>) : T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withAction(chat, UploadVideoNoteAction, block)
}
@OptIn(ExperimentalContracts::class)
suspend fun <T> TelegramBot.withChooseStickerAction(chat: Chat, block: TelegramBotActionCallback<T>) : T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withAction(chat, ChooseStickerAction, block)
}
