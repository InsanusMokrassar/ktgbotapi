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

/**
 * @param directMessageThreadId Direct messages topic identifier. Defaults from [chatId].
 */
public suspend fun TelegramBot.sendMessageDraft(
    chatId: IdChatIdentifier,
    draftId: DraftId,
    text: String,
    parseMode: ParseMode? = null,
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    canStop: Boolean? = null,
    keepOnStop: Boolean? = null
): Unit = execute(
    SendMessageDraft(
        chatId = chatId,
        draftId = draftId,
        text = text,
        parseMode = parseMode,
        directMessageThreadId = directMessageThreadId,
        canStop = canStop,
        keepOnStop = keepOnStop
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

/**
 * @param directMessageThreadId Direct messages topic identifier. Defaults from [chatId].
 */
public suspend fun TelegramBot.sendMessageDraftFlow(
    chatId: IdChatIdentifier,
    messagesFlow: Flow<TextSourcesList>,
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    draftId: DraftId? = null,
    canStop: Boolean? = null,
    keepOnStop: Boolean? = null
): Boolean {
    val draftId = draftId ?: GlobalDraftIdAllocator.allocate()
    return sendMessageDraftFlow(
        messagesFlow.map {
            SendMessageDraft(chatId = chatId, draftId = draftId, entities = it, directMessageThreadId = directMessageThreadId, canStop = canStop, keepOnStop = keepOnStop)
        }
    )
}

/**
 * @param directMessageThreadId Direct messages topic identifier. Defaults from [chatId].
 */
public suspend fun TelegramBot.sendMessageDraftFlowWithTextsAndParseMode(
    chatId: IdChatIdentifier,
    messagesFlow: Flow<Pair<String, ParseMode?>>,
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    draftId: DraftId? = null,
    canStop: Boolean? = null,
    keepOnStop: Boolean? = null
): Boolean {
    val draftId = draftId ?: GlobalDraftIdAllocator.allocate()
    return sendMessageDraftFlow(
        messagesFlow.map {
            SendMessageDraft(chatId = chatId, draftId = draftId, text = it.first, parseMode = it.second, directMessageThreadId = directMessageThreadId, canStop = canStop, keepOnStop = keepOnStop)
        }
    )
}

/**
 * @param directMessageThreadId Direct messages topic identifier. Defaults from [chatId].
 */
public suspend fun TelegramBot.sendMessageDraftFlowWithTexts(
    chatId: IdChatIdentifier,
    messagesFlow: Flow<String>,
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    draftId: DraftId? = null,
    canStop: Boolean? = null,
    keepOnStop: Boolean? = null
): Boolean {
    val draftId = draftId ?: GlobalDraftIdAllocator.allocate()
    return sendMessageDraftFlowWithTextsAndParseMode(
        chatId = chatId,
        messagesFlow = messagesFlow.map {
            it.escapeMarkdownV2Common() to MarkdownV2
        },
        directMessageThreadId = directMessageThreadId,
        draftId = draftId,
        canStop = canStop,
        keepOnStop = keepOnStop
    )
}

/**
 * @param directMessageThreadId Direct messages topic identifier. Defaults from [chat].
 */
public suspend fun TelegramBot.sendMessageDraft(
    chat: Chat,
    draftId: DraftId,
    text: String,
    parseMode: ParseMode? = null,
    directMessageThreadId: DirectMessageThreadId? = chat.id.directMessageThreadId,
    canStop: Boolean? = null,
    keepOnStop: Boolean? = null
): Unit = sendMessageDraft(
    chatId = chat.id,
    draftId = draftId,
    text = text,
    parseMode = parseMode,
    directMessageThreadId = directMessageThreadId,
    canStop = canStop,
    keepOnStop = keepOnStop
)

/**
 * @param directMessageThreadId Direct messages topic identifier. Defaults from [chatId].
 */
public suspend fun TelegramBot.sendMessageDraft(
    chatId: IdChatIdentifier,
    draftId: DraftId,
    entities: TextSourcesList,
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    canStop: Boolean? = null,
    keepOnStop: Boolean? = null
): Unit = execute(
    SendMessageDraft(
        chatId = chatId,
        draftId = draftId,
        entities = entities,
        directMessageThreadId = directMessageThreadId,
        canStop = canStop,
        keepOnStop = keepOnStop
    )
)

/**
 * @param directMessageThreadId Direct messages topic identifier. Defaults from [chat].
 */
public suspend fun TelegramBot.sendMessageDraft(
    chat: Chat,
    draftId: DraftId,
    entities: TextSourcesList,
    directMessageThreadId: DirectMessageThreadId? = chat.id.directMessageThreadId,
    canStop: Boolean? = null,
    keepOnStop: Boolean? = null
): Unit = sendMessageDraft(
    chatId = chat.id,
    draftId = draftId,
    entities = entities,
    directMessageThreadId = directMessageThreadId,
    canStop = canStop,
    keepOnStop = keepOnStop
)

/**
 * @param directMessageThreadId Direct messages topic identifier. Defaults from [chatId].
 */
public suspend fun TelegramBot.sendMessageDraft(
    chatId: IdChatIdentifier,
    draftId: DraftId,
    separator: TextSource? = null,
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    canStop: Boolean? = null,
    keepOnStop: Boolean? = null,
    builderBody: EntitiesBuilderBody
): Unit = sendMessageDraft(
    chatId = chatId,
    draftId = draftId,
    entities = buildEntities(separator, builderBody),
    directMessageThreadId = directMessageThreadId,
    canStop = canStop,
    keepOnStop = keepOnStop
)

/**
 * @param directMessageThreadId Direct messages topic identifier. Defaults from [chatId].
 */
public suspend fun TelegramBot.sendMessageDraft(
    chatId: IdChatIdentifier,
    draftId: DraftId,
    separator: String,
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    canStop: Boolean? = null,
    keepOnStop: Boolean? = null,
    builderBody: EntitiesBuilderBody
): Unit = sendMessageDraft(
    chatId = chatId,
    draftId = draftId,
    entities = buildEntities(separator, builderBody),
    directMessageThreadId = directMessageThreadId,
    canStop = canStop,
    keepOnStop = keepOnStop
)

/**
 * @param directMessageThreadId Direct messages topic identifier. Defaults from [chat].
 */
public suspend fun TelegramBot.sendMessageDraft(
    chat: Chat,
    draftId: DraftId,
    separator: TextSource? = null,
    directMessageThreadId: DirectMessageThreadId? = chat.id.directMessageThreadId,
    canStop: Boolean? = null,
    keepOnStop: Boolean? = null,
    builderBody: EntitiesBuilderBody
): Unit = sendMessageDraft(
    chatId = chat.id,
    draftId = draftId,
    separator = separator,
    directMessageThreadId = directMessageThreadId,
    canStop = canStop,
    keepOnStop = keepOnStop,
    builderBody = builderBody
)

/**
 * @param directMessageThreadId Direct messages topic identifier. Defaults from [chat].
 */
public suspend fun TelegramBot.sendMessageDraft(
    chat: Chat,
    draftId: DraftId,
    separator: String,
    directMessageThreadId: DirectMessageThreadId? = chat.id.directMessageThreadId,
    canStop: Boolean? = null,
    keepOnStop: Boolean? = null,
    builderBody: EntitiesBuilderBody
): Unit = sendMessageDraft(
    chatId = chat.id,
    draftId = draftId,
    separator = separator,
    directMessageThreadId = directMessageThreadId,
    canStop = canStop,
    keepOnStop = keepOnStop,
    builderBody = builderBody
)
