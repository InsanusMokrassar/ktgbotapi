package dev.inmo.tgbotapi.extensions.api.send

import dev.inmo.micro_utils.coroutines.safelyWithResult
import dev.inmo.micro_utils.coroutines.safelyWithoutExceptions
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.send.SendAction
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.actions.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.chat.Chat
import kotlinx.coroutines.*
import kotlin.contracts.*

private const val refreshTime: MilliSeconds = (botActionActualityTime - 1) * 1000L
public typealias TelegramBotActionCallback<T> = suspend TelegramBot.() -> T

@OptIn(ExperimentalContracts::class)
public suspend fun <T> TelegramBot.withAction(
    actionRequest: SendAction,
    block: TelegramBotActionCallback<T>
): T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    val botActionJob = CoroutineScope(currentCoroutineContext()).launch {
        while (isActive) {
            safelyWithoutExceptions {
                execute(actionRequest)
            }
            delay(refreshTime)
        }
    }
    val result = safelyWithResult { block() }
    botActionJob.cancel()
    return result.getOrThrow()
}

@OptIn(ExperimentalContracts::class)
public suspend fun <T> TelegramBot.withAction(
    chatId: IdChatIdentifier,
    action: BotAction,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    block: TelegramBotActionCallback<T>
): T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withAction(
        SendAction(chatId, action, threadId, businessConnectionId),
        block
    )
}

@OptIn(ExperimentalContracts::class)
public suspend fun <T> TelegramBot.withAction(
    chat: Chat,
    action: BotAction,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    block: TelegramBotActionCallback<T>
): T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withAction(
        chat.id,
        action,
        threadId,
        businessConnectionId,
        block
    )
}

@OptIn(ExperimentalContracts::class)
public suspend fun <T> TelegramBot.withTypingAction(chatId: IdChatIdentifier, threadId: MessageThreadId? = chatId.threadId, businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,block: TelegramBotActionCallback<T>) : T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withAction(chatId, TypingAction, threadId, businessConnectionId, block)
}
@OptIn(ExperimentalContracts::class)
public suspend fun <T> TelegramBot.withUploadPhotoAction(chatId: IdChatIdentifier, threadId: MessageThreadId? = chatId.threadId, businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,block: TelegramBotActionCallback<T>) : T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withAction(chatId, UploadPhotoAction, threadId, businessConnectionId, block)
}
@OptIn(ExperimentalContracts::class)
public suspend fun <T> TelegramBot.withRecordVideoAction(chatId: IdChatIdentifier, threadId: MessageThreadId? = chatId.threadId, businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,block: TelegramBotActionCallback<T>) : T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withAction(chatId, RecordVideoAction, threadId, businessConnectionId, block)
}
@OptIn(ExperimentalContracts::class)
public suspend fun <T> TelegramBot.withUploadVideoAction(chatId: IdChatIdentifier, threadId: MessageThreadId? = chatId.threadId, businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,block: TelegramBotActionCallback<T>) : T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withAction(chatId, UploadVideoAction, threadId, businessConnectionId, block)
}
@OptIn(ExperimentalContracts::class)
public suspend fun <T> TelegramBot.withRecordVoiceAction(chatId: IdChatIdentifier, threadId: MessageThreadId? = chatId.threadId, businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,block: TelegramBotActionCallback<T>) : T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withAction(chatId, RecordVoiceAction, threadId, businessConnectionId, block)
}
@OptIn(ExperimentalContracts::class)
public suspend fun <T> TelegramBot.withUploadVoiceAction(chatId: IdChatIdentifier, threadId: MessageThreadId? = chatId.threadId, businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,block: TelegramBotActionCallback<T>) : T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withAction(chatId, UploadVoiceAction, threadId, businessConnectionId, block)
}
@OptIn(ExperimentalContracts::class)
public suspend fun <T> TelegramBot.withUploadDocumentAction(chatId: IdChatIdentifier, threadId: MessageThreadId? = chatId.threadId, businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId, block: TelegramBotActionCallback<T>) : T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withAction(chatId, UploadDocumentAction, threadId, businessConnectionId, block)
}
@OptIn(ExperimentalContracts::class)
public suspend fun <T> TelegramBot.withFindLocationAction(chatId: IdChatIdentifier, threadId: MessageThreadId? = chatId.threadId, businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId, block: TelegramBotActionCallback<T>) : T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withAction(chatId, FindLocationAction, threadId, businessConnectionId, block)
}
@OptIn(ExperimentalContracts::class)
public suspend fun <T> TelegramBot.withRecordVideoNoteAction(chatId: IdChatIdentifier, threadId: MessageThreadId? = chatId.threadId, businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId, block: TelegramBotActionCallback<T>) : T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withAction(chatId, RecordVideoNoteAction, threadId, businessConnectionId, block)
}
@OptIn(ExperimentalContracts::class)
public suspend fun <T> TelegramBot.withUploadVideoNoteAction(chatId: IdChatIdentifier, threadId: MessageThreadId? = chatId.threadId, businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId, block: TelegramBotActionCallback<T>) : T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withAction(chatId, UploadVideoNoteAction, threadId, businessConnectionId, block)
}
@OptIn(ExperimentalContracts::class)
public suspend fun <T> TelegramBot.withChooseStickerAction(chatId: IdChatIdentifier, threadId: MessageThreadId? = chatId.threadId, businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId, block: TelegramBotActionCallback<T>) : T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withAction(chatId, ChooseStickerAction, threadId, businessConnectionId, block)
}


@OptIn(ExperimentalContracts::class)
public suspend fun <T> TelegramBot.withTypingAction(chat: Chat, threadId: MessageThreadId? = chat.id.threadId, businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId, block: TelegramBotActionCallback<T>) : T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withAction(chat, TypingAction, threadId, businessConnectionId, block)
}
@OptIn(ExperimentalContracts::class)
public suspend fun <T> TelegramBot.withUploadPhotoAction(chat: Chat, threadId: MessageThreadId? = chat.id.threadId, businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId, block: TelegramBotActionCallback<T>) : T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withAction(chat, UploadPhotoAction, threadId, businessConnectionId, block)
}
@OptIn(ExperimentalContracts::class)
public suspend fun <T> TelegramBot.withRecordVideoAction(chat: Chat, threadId: MessageThreadId? = chat.id.threadId, businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId, block: TelegramBotActionCallback<T>) : T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withAction(chat, RecordVideoAction, threadId, businessConnectionId, block)
}
@OptIn(ExperimentalContracts::class)
public suspend fun <T> TelegramBot.withUploadVideoAction(chat: Chat, threadId: MessageThreadId? = chat.id.threadId, businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId, block: TelegramBotActionCallback<T>) : T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withAction(chat, UploadVideoAction, threadId, businessConnectionId, block)
}
@OptIn(ExperimentalContracts::class)
public suspend fun <T> TelegramBot.withRecordVoiceAction(chat: Chat, threadId: MessageThreadId? = chat.id.threadId, businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId, block: TelegramBotActionCallback<T>) : T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withAction(chat, RecordVoiceAction, threadId, businessConnectionId, block)
}
@OptIn(ExperimentalContracts::class)
public suspend fun <T> TelegramBot.withUploadVoiceAction(chat: Chat, threadId: MessageThreadId? = chat.id.threadId, businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId, block: TelegramBotActionCallback<T>) : T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withAction(chat, UploadVoiceAction, threadId, businessConnectionId, block)
}
@OptIn(ExperimentalContracts::class)
public suspend fun <T> TelegramBot.withUploadDocumentAction(chat: Chat, threadId: MessageThreadId? = chat.id.threadId, businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId, block: TelegramBotActionCallback<T>) : T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withAction(chat, UploadDocumentAction, threadId, businessConnectionId, block)
}
@OptIn(ExperimentalContracts::class)
public suspend fun <T> TelegramBot.withFindLocationAction(chat: Chat, threadId: MessageThreadId? = chat.id.threadId, businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId, block: TelegramBotActionCallback<T>) : T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withAction(chat, FindLocationAction, threadId, businessConnectionId, block)
}
@OptIn(ExperimentalContracts::class)
public suspend fun <T> TelegramBot.withRecordVideoNoteAction(chat: Chat, threadId: MessageThreadId? = chat.id.threadId, businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId, block: TelegramBotActionCallback<T>) : T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withAction(chat, RecordVideoNoteAction, threadId, businessConnectionId, block)
}
@OptIn(ExperimentalContracts::class)
public suspend fun <T> TelegramBot.withUploadVideoNoteAction(chat: Chat, threadId: MessageThreadId? = chat.id.threadId, businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId, block: TelegramBotActionCallback<T>) : T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withAction(chat, UploadVideoNoteAction, threadId, businessConnectionId, block)
}
@OptIn(ExperimentalContracts::class)
public suspend fun <T> TelegramBot.withChooseStickerAction(chat: Chat, threadId: MessageThreadId? = chat.id.threadId, businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId, block: TelegramBotActionCallback<T>) : T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return withAction(chat, ChooseStickerAction, threadId, businessConnectionId, block)
}
