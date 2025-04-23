package dev.inmo.tgbotapi.extensions.api.send

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.send.SendTextMessage
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.TextContent
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.utils.EntitiesBuilderBody
import dev.inmo.tgbotapi.utils.buildEntities

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend fun TelegramBot.sendMessage(
    chatId: ChatIdentifier,
    text: String,
    parseMode: ParseMode? = null,
    linkPreviewOptions: LinkPreviewOptions? = null,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
): ContentMessage<TextContent> = execute(
    SendTextMessage(
        chatId = chatId,
        text = text,
        parseMode = parseMode,
        linkPreviewOptions = linkPreviewOptions,
        threadId = threadId,
        businessConnectionId = businessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup,
    ),
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend fun TelegramBot.sendTextMessage(
    chatId: ChatIdentifier,
    text: String,
    parseMode: ParseMode? = null,
    linkPreviewOptions: LinkPreviewOptions? = null,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
): ContentMessage<TextContent> = sendMessage(
    chatId = chatId,
    text = text,
    parseMode = parseMode,
    linkPreviewOptions = linkPreviewOptions,
    threadId = threadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend fun TelegramBot.sendTextMessage(
    chat: Chat,
    text: String,
    parseMode: ParseMode? = null,
    linkPreviewOptions: LinkPreviewOptions? = null,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
): ContentMessage<TextContent> = sendTextMessage(
    chatId = chat.id,
    text = text,
    parseMode = parseMode,
    linkPreviewOptions = linkPreviewOptions,
    threadId = threadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend fun TelegramBot.sendMessage(
    chat: Chat,
    text: String,
    parseMode: ParseMode? = null,
    linkPreviewOptions: LinkPreviewOptions? = null,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
): ContentMessage<TextContent> = sendMessage(
    chatId = chat.id,
    text = text,
    parseMode = parseMode,
    linkPreviewOptions = linkPreviewOptions,
    threadId = threadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend fun TelegramBot.sendMessage(
    chatId: ChatIdentifier,
    entities: TextSourcesList,
    linkPreviewOptions: LinkPreviewOptions? = null,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
): ContentMessage<TextContent> = execute(
    SendTextMessage(
        chatId = chatId,
        entities = entities,
        linkPreviewOptions = linkPreviewOptions,
        threadId = threadId,
        businessConnectionId = businessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup,
    ),
)

/**
 * @param replyMarkup Some [dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.sendMessage(
    chatId: ChatIdentifier,
    separator: TextSource? = null,
    linkPreviewOptions: LinkPreviewOptions? = null,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody,
): ContentMessage<TextContent> = sendMessage(
    chatId = chatId,
    entities = buildEntities(separator, builderBody),
    linkPreviewOptions = linkPreviewOptions,
    threadId = threadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
)

/**
 * @param replyMarkup Some [dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.sendMessage(
    chatId: ChatIdentifier,
    separator: String,
    linkPreviewOptions: LinkPreviewOptions? = null,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody,
): ContentMessage<TextContent> = sendMessage(
    chatId = chatId,
    entities = buildEntities(separator, builderBody),
    linkPreviewOptions = linkPreviewOptions,
    threadId = threadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend fun TelegramBot.sendTextMessage(
    chatId: ChatIdentifier,
    entities: TextSourcesList,
    linkPreviewOptions: LinkPreviewOptions? = null,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
): ContentMessage<TextContent> = sendMessage(
    chatId = chatId,
    entities = entities,
    linkPreviewOptions = linkPreviewOptions,
    threadId = threadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
)

/**
 * @param replyMarkup Some [dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.sendTextMessage(
    chatId: ChatIdentifier,
    separator: TextSource? = null,
    linkPreviewOptions: LinkPreviewOptions? = null,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody,
): ContentMessage<TextContent> = sendTextMessage(
    chatId = chatId,
    entities = buildEntities(separator, builderBody),
    linkPreviewOptions = linkPreviewOptions,
    threadId = threadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
)

/**
 * @param replyMarkup Some [dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.sendTextMessage(
    chatId: ChatIdentifier,
    separator: String,
    linkPreviewOptions: LinkPreviewOptions? = null,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody,
): ContentMessage<TextContent> = sendTextMessage(
    chatId = chatId,
    entities = buildEntities(separator, builderBody),
    linkPreviewOptions = linkPreviewOptions,
    threadId = threadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend fun TelegramBot.sendMessage(
    chat: Chat,
    entities: TextSourcesList,
    linkPreviewOptions: LinkPreviewOptions? = null,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
): ContentMessage<TextContent> = sendMessage(
    chatId = chat.id,
    entities = entities,
    linkPreviewOptions = linkPreviewOptions,
    threadId = threadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
)

/**
 * @param replyMarkup Some [dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.sendMessage(
    chat: Chat,
    separator: TextSource? = null,
    linkPreviewOptions: LinkPreviewOptions? = null,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody,
): ContentMessage<TextContent> = sendMessage(
    chat = chat,
    entities = buildEntities(separator, builderBody),
    linkPreviewOptions = linkPreviewOptions,
    threadId = threadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
)

/**
 * @param replyMarkup Some [dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.sendMessage(
    chat: Chat,
    separator: String,
    linkPreviewOptions: LinkPreviewOptions? = null,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody,
): ContentMessage<TextContent> = sendMessage(
    chat = chat,
    entities = buildEntities(separator, builderBody),
    linkPreviewOptions = linkPreviewOptions,
    threadId = threadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend fun TelegramBot.sendTextMessage(
    chat: Chat,
    entities: TextSourcesList,
    linkPreviewOptions: LinkPreviewOptions? = null,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
): ContentMessage<TextContent> = sendTextMessage(
    chatId = chat.id,
    entities = entities,
    linkPreviewOptions = linkPreviewOptions,
    threadId = threadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
)

/**
 * @param replyMarkup Some [dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.sendTextMessage(
    chat: Chat,
    separator: TextSource? = null,
    linkPreviewOptions: LinkPreviewOptions? = null,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody,
): ContentMessage<TextContent> = sendTextMessage(
    chat = chat,
    entities = buildEntities(separator, builderBody),
    linkPreviewOptions = linkPreviewOptions,
    threadId = threadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
)

/**
 * @param replyMarkup Some [dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.sendTextMessage(
    chat: Chat,
    separator: String,
    linkPreviewOptions: LinkPreviewOptions? = null,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody,
): ContentMessage<TextContent> = sendTextMessage(
    chat = chat,
    entities = buildEntities(separator, builderBody),
    linkPreviewOptions = linkPreviewOptions,
    threadId = threadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
)
