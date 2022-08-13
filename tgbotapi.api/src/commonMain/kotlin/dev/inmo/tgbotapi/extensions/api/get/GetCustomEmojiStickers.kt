package dev.inmo.tgbotapi.extensions.api.get

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.get.GetCustomEmojiStickers
import dev.inmo.tgbotapi.requests.get.GetStickerSet
import dev.inmo.tgbotapi.types.CustomEmojiId
import dev.inmo.tgbotapi.types.files.Sticker
import kotlin.js.JsName
import kotlin.jvm.JvmName

suspend fun TelegramBot.getCustomEmojiStickers(
    customEmojiIds: List<CustomEmojiId>
) = execute(
    GetCustomEmojiStickers(customEmojiIds)
)

@JvmName("getCustomEmojiStickersWithStringsList")
@JsName("getCustomEmojiStickersWithStringsList")
suspend fun TelegramBot.getCustomEmojiStickers(
    customEmojiIds: List<String>
) = getCustomEmojiStickers(customEmojiIds.map(::CustomEmojiId))

suspend fun TelegramBot.getCustomEmojiStickerOrNull(
    customEmojiId: CustomEmojiId
) = getCustomEmojiStickers(listOf(customEmojiId)).firstOrNull()

suspend fun TelegramBot.getCustomEmojiStickerOrThrow(
    customEmojiId: CustomEmojiId
) = getCustomEmojiStickers(listOf(customEmojiId)).first()

suspend fun TelegramBot.getCustomEmojiStickerOrNull(
    customEmojiId: String
) = getCustomEmojiStickerOrNull(CustomEmojiId(customEmojiId))

suspend fun TelegramBot.getCustomEmojiStickerOrThrow(
    customEmojiId: String
) = getCustomEmojiStickerOrThrow(CustomEmojiId(customEmojiId))
