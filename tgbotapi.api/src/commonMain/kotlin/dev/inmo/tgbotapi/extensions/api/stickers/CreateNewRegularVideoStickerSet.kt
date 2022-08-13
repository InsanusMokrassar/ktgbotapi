package dev.inmo.tgbotapi.extensions.api.stickers

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.requests.abstracts.MultipartFile
import dev.inmo.tgbotapi.requests.stickers.*
import dev.inmo.tgbotapi.types.chat.CommonUser
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.stickers.MaskPosition

suspend fun TelegramBot.createNewRegularVideoStickerSet(
    userId: UserId,
    name: String,
    title: String,
    sticker: FileId,
    emojis: String
) = execute(
    CreateNewRegularVideoStickerSet(userId, name, title, sticker, emojis)
)

suspend fun TelegramBot.createNewRegularVideoStickerSet(
    userId: UserId,
    name: String,
    title: String,
    sticker: MultipartFile,
    emojis: String
) = execute(
    CreateNewRegularVideoStickerSet(userId, name, title, sticker, emojis)
)


suspend fun TelegramBot.createNewRegularVideoStickerSet(
    user: CommonUser,
    name: String,
    title: String,
    sticker: FileId,
    emojis: String
) = createNewRegularVideoStickerSet(
    user.id, name, title, sticker, emojis
)

suspend fun TelegramBot.createNewRegularVideoStickerSet(
    user: CommonUser,
    name: String,
    title: String,
    sticker: MultipartFile,
    emojis: String
) = createNewRegularVideoStickerSet(
    user.id, name, title, sticker, emojis
)
