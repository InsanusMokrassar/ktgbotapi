package dev.inmo.tgbotapi.extensions.api.send

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.send.SendMessageDraft
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.TextContent
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.utils.EntitiesBuilderBody
import dev.inmo.tgbotapi.utils.buildEntities

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
