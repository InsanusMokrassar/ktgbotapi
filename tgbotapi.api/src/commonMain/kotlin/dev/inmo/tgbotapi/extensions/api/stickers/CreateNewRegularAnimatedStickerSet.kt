package dev.inmo.tgbotapi.extensions.api.stickers

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.requests.abstracts.MultipartFile
import dev.inmo.tgbotapi.requests.stickers.*
import dev.inmo.tgbotapi.types.chat.CommonUser
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.stickers.MaskPosition

suspend fun TelegramBot.createNewRegularAnimatedStickerSet(
    userId: UserId,
    name: String,
    title: String,
    sticker: FileId,
    emojis: String
) = execute(
    CreateNewRegularAnimatedStickerSet(userId, name, title, sticker, emojis)
)

suspend fun TelegramBot.createNewRegularAnimatedStickerSet(
    userId: UserId,
    name: String,
    title: String,
    sticker: MultipartFile,
    emojis: String
) = execute(
    CreateNewRegularAnimatedStickerSet(userId, name, title, sticker, emojis)
)


suspend fun TelegramBot.createNewRegularAnimatedStickerSet(
    user: CommonUser,
    name: String,
    title: String,
    sticker: FileId,
    emojis: String
) = createNewRegularAnimatedStickerSet(
    user.id, name, title, sticker, emojis
)

suspend fun TelegramBot.createNewRegularAnimatedStickerSet(
    user: CommonUser,
    name: String,
    title: String,
    sticker: MultipartFile,
    emojis: String
) = createNewRegularAnimatedStickerSet(
    user.id, name, title, sticker, emojis
)
