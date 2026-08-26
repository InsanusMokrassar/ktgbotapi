package dev.inmo.tgbotapi.extensions.api.edit.text

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.edit.text.EditEphemeralMessageText
import dev.inmo.tgbotapi.requests.edit.text.EditEphemeralMessageRichText
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.types.rich.InputRichMessage
import dev.inmo.tgbotapi.utils.EntitiesBuilderBody
import dev.inmo.tgbotapi.utils.buildEntities

/** Edit an ephemeral message with a rich message. */
public suspend fun TelegramBot.editEphemeralMessageRichText(
    chatId: ChatIdentifier,
    receiverUserId: UserId,
    ephemeralMessageId: EphemeralMessageId,
    richMessage: InputRichMessage,
    replyMarkup: InlineKeyboardMarkup? = null
): Unit = execute(
    request = EditEphemeralMessageRichText(
        chatId = chatId,
        receiverUserId = receiverUserId,
        ephemeralMessageId = ephemeralMessageId,
        richMessage = richMessage,
        replyMarkup = replyMarkup
    )
)

/** Edit an ephemeral message with a rich message, obtaining recipient and message identifiers from [chatId]. */
public suspend fun TelegramBot.editEphemeralMessageRichText(
    chatId: EphemeralChatId,
    richMessage: InputRichMessage,
    replyMarkup: InlineKeyboardMarkup? = null
): Unit = execute(
    request = EditEphemeralMessageRichText(
        chatId = chatId,
        richMessage = richMessage,
        replyMarkup = replyMarkup
    )
)

/** Edit an ephemeral message with a rich message. */
public suspend fun TelegramBot.editEphemeralMessageRichText(
    chat: Chat,
    receiverUserId: UserId,
    ephemeralMessageId: EphemeralMessageId,
    richMessage: InputRichMessage,
    replyMarkup: InlineKeyboardMarkup? = null
): Unit = editEphemeralMessageRichText(
    chatId = chat.id,
    receiverUserId = receiverUserId,
    ephemeralMessageId = ephemeralMessageId,
    richMessage = richMessage,
    replyMarkup = replyMarkup
)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editEphemeralMessageText(
    chatId: ChatIdentifier,
    receiverUserId: UserId,
    ephemeralMessageId: EphemeralMessageId,
    text: String,
    parseMode: ParseMode? = null,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null
): Unit = execute(
    EditEphemeralMessageText(
        chatId = chatId,
        receiverUserId = receiverUserId,
        ephemeralMessageId = ephemeralMessageId,
        text = text,
        parseMode = parseMode,
        linkPreviewOptions = linkPreviewOptions,
        replyMarkup = replyMarkup
    )
)

/**
 * Convenience overload sourcing `receiverUserId`/`ephemeralMessageId` from [chatId]. Throws
 * [IllegalArgumentException] if [chatId] does not carry an ephemeralMessageId
 *
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editEphemeralMessageText(
    chatId: EphemeralChatId,
    text: String,
    parseMode: ParseMode? = null,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null
): Unit = editEphemeralMessageText(
    chatId = chatId,
    receiverUserId = chatId.receiverUser,
    ephemeralMessageId = requireNotNull(chatId.ephemeralMessageId) { "chatId ($chatId) does not carry an ephemeralMessageId" },
    text = text,
    parseMode = parseMode,
    linkPreviewOptions = linkPreviewOptions,
    replyMarkup = replyMarkup
)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editEphemeralMessageText(
    chat: Chat,
    receiverUserId: UserId,
    ephemeralMessageId: EphemeralMessageId,
    text: String,
    parseMode: ParseMode? = null,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null
): Unit = editEphemeralMessageText(
    chatId = chat.id,
    receiverUserId = receiverUserId,
    ephemeralMessageId = ephemeralMessageId,
    text = text,
    parseMode = parseMode,
    linkPreviewOptions = linkPreviewOptions,
    replyMarkup = replyMarkup
)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editEphemeralMessageText(
    chatId: ChatIdentifier,
    receiverUserId: UserId,
    ephemeralMessageId: EphemeralMessageId,
    entities: TextSourcesList,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null
): Unit = execute(
    EditEphemeralMessageText(
        chatId = chatId,
        receiverUserId = receiverUserId,
        ephemeralMessageId = ephemeralMessageId,
        entities = entities,
        linkPreviewOptions = linkPreviewOptions,
        replyMarkup = replyMarkup
    )
)

/**
 * Convenience overload sourcing `receiverUserId`/`ephemeralMessageId` from [chatId]. Throws
 * [IllegalArgumentException] if [chatId] does not carry an ephemeralMessageId
 *
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editEphemeralMessageText(
    chatId: EphemeralChatId,
    entities: TextSourcesList,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null
): Unit = editEphemeralMessageText(
    chatId = chatId,
    receiverUserId = chatId.receiverUser,
    ephemeralMessageId = requireNotNull(chatId.ephemeralMessageId) { "chatId ($chatId) does not carry an ephemeralMessageId" },
    entities = entities,
    linkPreviewOptions = linkPreviewOptions,
    replyMarkup = replyMarkup
)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editEphemeralMessageText(
    chat: Chat,
    receiverUserId: UserId,
    ephemeralMessageId: EphemeralMessageId,
    entities: TextSourcesList,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null
): Unit = editEphemeralMessageText(
    chatId = chat.id,
    receiverUserId = receiverUserId,
    ephemeralMessageId = ephemeralMessageId,
    entities = entities,
    linkPreviewOptions = linkPreviewOptions,
    replyMarkup = replyMarkup
)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editEphemeralMessageText(
    chatId: ChatIdentifier,
    receiverUserId: UserId,
    ephemeralMessageId: EphemeralMessageId,
    separator: TextSource? = null,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
): Unit = editEphemeralMessageText(
    chatId = chatId,
    receiverUserId = receiverUserId,
    ephemeralMessageId = ephemeralMessageId,
    entities = buildEntities(separator, builderBody),
    linkPreviewOptions = linkPreviewOptions,
    replyMarkup = replyMarkup
)

/**
 * Convenience overload sourcing `receiverUserId`/`ephemeralMessageId` from [chatId]. Throws
 * [IllegalArgumentException] if [chatId] does not carry an ephemeralMessageId
 *
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editEphemeralMessageText(
    chatId: EphemeralChatId,
    separator: TextSource? = null,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
): Unit = editEphemeralMessageText(
    chatId = chatId,
    entities = buildEntities(separator, builderBody),
    linkPreviewOptions = linkPreviewOptions,
    replyMarkup = replyMarkup
)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editEphemeralMessageText(
    chatId: ChatIdentifier,
    receiverUserId: UserId,
    ephemeralMessageId: EphemeralMessageId,
    separator: String,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
): Unit = editEphemeralMessageText(
    chatId = chatId,
    receiverUserId = receiverUserId,
    ephemeralMessageId = ephemeralMessageId,
    entities = buildEntities(separator, builderBody),
    linkPreviewOptions = linkPreviewOptions,
    replyMarkup = replyMarkup
)

/**
 * Convenience overload sourcing `receiverUserId`/`ephemeralMessageId` from [chatId]. Throws
 * [IllegalArgumentException] if [chatId] does not carry an ephemeralMessageId
 *
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editEphemeralMessageText(
    chatId: EphemeralChatId,
    separator: String,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
): Unit = editEphemeralMessageText(
    chatId = chatId,
    entities = buildEntities(separator, builderBody),
    linkPreviewOptions = linkPreviewOptions,
    replyMarkup = replyMarkup
)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editEphemeralMessageText(
    chat: Chat,
    receiverUserId: UserId,
    ephemeralMessageId: EphemeralMessageId,
    separator: TextSource? = null,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
): Unit = editEphemeralMessageText(
    chatId = chat.id,
    receiverUserId = receiverUserId,
    ephemeralMessageId = ephemeralMessageId,
    entities = buildEntities(separator, builderBody),
    linkPreviewOptions = linkPreviewOptions,
    replyMarkup = replyMarkup
)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editEphemeralMessageText(
    chat: Chat,
    receiverUserId: UserId,
    ephemeralMessageId: EphemeralMessageId,
    separator: String,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
): Unit = editEphemeralMessageText(
    chatId = chat.id,
    receiverUserId = receiverUserId,
    ephemeralMessageId = ephemeralMessageId,
    entities = buildEntities(separator, builderBody),
    linkPreviewOptions = linkPreviewOptions,
    replyMarkup = replyMarkup
)
