package dev.inmo.tgbotapi.extensions.api.send

import dev.inmo.kslog.common.logger
import dev.inmo.micro_utils.coroutines.runCatchingLogging
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.send.SendMessageDraft
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.MarkdownV2
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.utils.DraftIdAllocator
import dev.inmo.tgbotapi.utils.EntitiesBuilderBody
import dev.inmo.tgbotapi.utils.buildEntities
import dev.inmo.tgbotapi.utils.extensions.escapeMarkdownV2Common
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlin.js.JsName
import kotlin.jvm.JvmName

public suspend fun TelegramBot.sendMessageDraft(
    chatId: IdChatIdentifier,
    draftId: DraftId,
    text: String,
    parseMode: ParseMode? = null,
    threadId: MessageThreadId? = chatId.threadId
): Boolean = execute(
    SendMessageDraft(
        chatId = chatId,
        draftId = draftId,
        text = text,
        parseMode = parseMode,
        threadId = threadId
    )
)

private suspend fun TelegramBot.sendMessageDraftFlow(
    messagesFlow: Flow<SendMessageDraft>,
): Boolean {
    val done = messagesFlow
        .filter { draft ->
            val sent = runCatchingLogging(logger = logger) {
                execute(draft)
            }.getOrElse {
                false
            }

            sent == false
        }
        .firstOrNull()

    return done == null
}

public val GlobalDraftIdAllocator: DraftIdAllocator by lazy { DraftIdAllocator() }

public suspend fun TelegramBot.sendMessageDraftFlow(
    chatId: IdChatIdentifier,
    messagesFlow: Flow<TextSourcesList>,
    threadId: MessageThreadId? = chatId.threadId,
    draftId: DraftId? = null,
): Boolean {
    val draftId = draftId ?: GlobalDraftIdAllocator.allocate()
    return sendMessageDraftFlow(
        messagesFlow.map {
            SendMessageDraft(chatId = chatId, draftId = draftId, entities = it, threadId = threadId)
        }
    )
}

public suspend fun TelegramBot.sendMessageDraftFlowWithTextsAndParseMode(
    chatId: IdChatIdentifier,
    messagesFlow: Flow<Pair<String, ParseMode?>>,
    threadId: MessageThreadId? = chatId.threadId,
    draftId: DraftId? = null,
): Boolean {
    val draftId = draftId ?: GlobalDraftIdAllocator.allocate()
    return sendMessageDraftFlow(
        messagesFlow.map {
            SendMessageDraft(chatId = chatId, draftId = draftId, text = it.first, parseMode = it.second, threadId = threadId)
        }
    )
}

public suspend fun TelegramBot.sendMessageDraftFlowWithTexts(
    chatId: IdChatIdentifier,
    messagesFlow: Flow<String>,
    threadId: MessageThreadId? = chatId.threadId,
    draftId: DraftId? = null,
): Boolean {
    val draftId = draftId ?: GlobalDraftIdAllocator.allocate()
    return sendMessageDraftFlowWithTextsAndParseMode(
        chatId = chatId,
        messagesFlow = messagesFlow.map {
            it.escapeMarkdownV2Common() to MarkdownV2
        },
        threadId = threadId,
        draftId = draftId
    )
}

public suspend fun TelegramBot.sendMessageDraft(
    chat: Chat,
    draftId: DraftId,
    text: String,
    parseMode: ParseMode? = null,
    threadId: MessageThreadId? = chat.id.threadId
): Boolean = sendMessageDraft(
    chatId = chat.id as IdChatIdentifier,
    draftId = draftId,
    text = text,
    parseMode = parseMode,
    threadId = threadId
)

public suspend fun TelegramBot.sendMessageDraft(
    chatId: IdChatIdentifier,
    draftId: DraftId,
    entities: TextSourcesList,
    threadId: MessageThreadId? = chatId.threadId
): Boolean = execute(
    SendMessageDraft(
        chatId = chatId,
        draftId = draftId,
        entities = entities,
        threadId = threadId
    )
)

public suspend fun TelegramBot.sendMessageDraft(
    chat: Chat,
    draftId: DraftId,
    entities: TextSourcesList,
    threadId: MessageThreadId? = chat.id.threadId
): Boolean = sendMessageDraft(
    chatId = chat.id as IdChatIdentifier,
    draftId = draftId,
    entities = entities,
    threadId = threadId
)

public suspend fun TelegramBot.sendMessageDraft(
    chatId: IdChatIdentifier,
    draftId: DraftId,
    separator: TextSource? = null,
    threadId: MessageThreadId? = chatId.threadId,
    builderBody: EntitiesBuilderBody
): Boolean = sendMessageDraft(
    chatId = chatId,
    draftId = draftId,
    entities = buildEntities(separator, builderBody),
    threadId = threadId
)

public suspend fun TelegramBot.sendMessageDraft(
    chatId: IdChatIdentifier,
    draftId: DraftId,
    separator: String,
    threadId: MessageThreadId? = chatId.threadId,
    builderBody: EntitiesBuilderBody
): Boolean = sendMessageDraft(
    chatId = chatId,
    draftId = draftId,
    entities = buildEntities(separator, builderBody),
    threadId = threadId
)

public suspend fun TelegramBot.sendMessageDraft(
    chat: Chat,
    draftId: DraftId,
    separator: TextSource? = null,
    threadId: MessageThreadId? = chat.id.threadId,
    builderBody: EntitiesBuilderBody
): Boolean = sendMessageDraft(
    chatId = chat.id as IdChatIdentifier,
    draftId = draftId,
    separator = separator,
    threadId = threadId,
    builderBody = builderBody
)

public suspend fun TelegramBot.sendMessageDraft(
    chat: Chat,
    draftId: DraftId,
    separator: String,
    threadId: MessageThreadId? = chat.id.threadId,
    builderBody: EntitiesBuilderBody
): Boolean = sendMessageDraft(
    chatId = chat.id as IdChatIdentifier,
    draftId = draftId,
    separator = separator,
    threadId = threadId,
    builderBody = builderBody
)
