package dev.inmo.tgbotapi.extensions.api.get

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.get.GetCustomEmojiStickers
import dev.inmo.tgbotapi.types.CustomEmojiId
import dev.inmo.tgbotapi.types.files.CustomEmojiSticker
import kotlin.js.JsName
import kotlin.jvm.JvmName

public suspend fun TelegramBot.getCustomEmojiStickers(customEmojiIds: List<CustomEmojiId>): List<CustomEmojiSticker> = execute(
    GetCustomEmojiStickers(customEmojiIds),
)

@JvmName("getCustomEmojiStickersWithStringsList")
@JsName("getCustomEmojiStickersWithStringsList")
public suspend fun TelegramBot.getCustomEmojiStickers(customEmojiIds: List<String>): List<CustomEmojiSticker> = getCustomEmojiStickers(customEmojiIds.map(::CustomEmojiId))

public suspend fun TelegramBot.getCustomEmojiStickerOrNull(customEmojiId: CustomEmojiId): CustomEmojiSticker? = getCustomEmojiStickers(listOf(customEmojiId)).firstOrNull()

public suspend fun TelegramBot.getCustomEmojiStickerOrThrow(customEmojiId: CustomEmojiId): CustomEmojiSticker = getCustomEmojiStickers(listOf(customEmojiId)).first()

public suspend fun TelegramBot.getCustomEmojiStickerOrNull(customEmojiId: String): CustomEmojiSticker? = getCustomEmojiStickerOrNull(CustomEmojiId(customEmojiId))

public suspend fun TelegramBot.getCustomEmojiStickerOrThrow(customEmojiId: String): CustomEmojiSticker = getCustomEmojiStickerOrThrow(CustomEmojiId(customEmojiId))
