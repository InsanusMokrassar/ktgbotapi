@file:Suppress("KDocUnresolvedReference")

package dev.inmo.tgbotapi.extensions.api.send.media

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.InputFile
import dev.inmo.tgbotapi.requests.send.media.SendSticker
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.files.Sticker
import dev.inmo.tgbotapi.types.message.abstracts.ChatContentMessage
import dev.inmo.tgbotapi.types.message.content.StickerContent
import dev.inmo.tgbotapi.types.message.SuggestedPostParameters

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend fun TelegramBot.sendSticker(
    chatId: ChatIdentifier,
    sticker: InputFile,
    threadId: MessageThreadId? = chatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = chatId.receiverUser ?.let(::EphemeralMessageParameters),
    emoji: String? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<StickerContent> = execute(
    SendSticker(
        chatId = chatId,
        sticker = sticker,
        threadId = threadId,
        directMessageThreadId = directMessageThreadId,
        businessConnectionId = businessConnectionId,
        ephemeralMessageParameters = ephemeralMessageParameters,
        emoji = emoji,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        suggestedPostParameters = suggestedPostParameters,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )
)

public suspend fun TelegramBot.sendSticker(
    chatId: ChatIdentifier,
    sticker: InputFile,
    threadId: MessageThreadId? = chatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    receiverUserId: UserId? = chatId.receiverUser,
    callbackQueryId: CallbackQueryId? = null,
    replaceCallbackQueryMessage: Boolean? = null,
    emoji: String? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<StickerContent> = sendSticker(
    chatId = chatId,
    sticker = sticker,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    ephemeralMessageParameters = receiverUserId ?.let { EphemeralMessageParameters(it, callbackQueryId, replaceCallbackQueryMessage) },
    emoji = emoji,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend fun TelegramBot.sendSticker(
    chat: Chat,
    sticker: InputFile,
    threadId: MessageThreadId? = chat.id.threadId,
    directMessageThreadId: DirectMessageThreadId? = chat.id.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = chat.id.receiverUser ?.let(::EphemeralMessageParameters),
    emoji: String? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<StickerContent> = sendSticker(
    chatId = chat.id,
    sticker = sticker,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    ephemeralMessageParameters = ephemeralMessageParameters,
    emoji = emoji,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)

public suspend fun TelegramBot.sendSticker(
    chat: Chat,
    sticker: InputFile,
    threadId: MessageThreadId? = chat.id.threadId,
    directMessageThreadId: DirectMessageThreadId? = chat.id.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    receiverUserId: UserId? = chat.id.receiverUser,
    callbackQueryId: CallbackQueryId? = null,
    replaceCallbackQueryMessage: Boolean? = null,
    emoji: String? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<StickerContent> = sendSticker(
    chat = chat,
    sticker = sticker,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    ephemeralMessageParameters = receiverUserId ?.let { EphemeralMessageParameters(it, callbackQueryId, replaceCallbackQueryMessage) },
    emoji = emoji,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend fun TelegramBot.sendSticker(
    chatId: ChatIdentifier,
    sticker: Sticker,
    threadId: MessageThreadId? = chatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = chatId.receiverUser ?.let(::EphemeralMessageParameters),
    emoji: String? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<StickerContent> = sendSticker(
    chatId = chatId,
    sticker = sticker.fileId,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    ephemeralMessageParameters = ephemeralMessageParameters,
    emoji = emoji,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)

public suspend fun TelegramBot.sendSticker(
    chatId: ChatIdentifier,
    sticker: Sticker,
    threadId: MessageThreadId? = chatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    receiverUserId: UserId? = chatId.receiverUser,
    callbackQueryId: CallbackQueryId? = null,
    replaceCallbackQueryMessage: Boolean? = null,
    emoji: String? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<StickerContent> = sendSticker(
    chatId = chatId,
    sticker = sticker,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    ephemeralMessageParameters = receiverUserId ?.let { EphemeralMessageParameters(it, callbackQueryId, replaceCallbackQueryMessage) },
    emoji = emoji,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
)

/**
 * @param replyMarkup Some of [KeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard] or
 * [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard] as a builders for that param
 */
public suspend fun TelegramBot.sendSticker(
    chat: Chat,
    sticker: Sticker,
    threadId: MessageThreadId? = chat.id.threadId,
    directMessageThreadId: DirectMessageThreadId? = chat.id.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    ephemeralMessageParameters: EphemeralMessageParameters? = chat.id.receiverUser ?.let(::EphemeralMessageParameters),
    emoji: String? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<StickerContent> = sendSticker(
    chat = chat,
    sticker = sticker.fileId,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    ephemeralMessageParameters = ephemeralMessageParameters,
    emoji = emoji,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)

public suspend fun TelegramBot.sendSticker(
    chat: Chat,
    sticker: Sticker,
    threadId: MessageThreadId? = chat.id.threadId,
    directMessageThreadId: DirectMessageThreadId? = chat.id.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    receiverUserId: UserId? = chat.id.receiverUser,
    callbackQueryId: CallbackQueryId? = null,
    replaceCallbackQueryMessage: Boolean? = null,
    emoji: String? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): ChatContentMessage<StickerContent> = sendSticker(
    chat = chat,
    sticker = sticker,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    ephemeralMessageParameters = receiverUserId ?.let { EphemeralMessageParameters(it, callbackQueryId, replaceCallbackQueryMessage) },
    emoji = emoji,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup,
)
