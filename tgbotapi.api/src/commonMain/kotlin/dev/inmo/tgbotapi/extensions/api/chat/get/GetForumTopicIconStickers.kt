package dev.inmo.tgbotapi.extensions.api.chat.get

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.get.GetForumTopicIconStickers
import dev.inmo.tgbotapi.types.files.Sticker

public suspend fun TelegramBot.getForumTopicIconStickers(): List<Sticker> = execute(GetForumTopicIconStickers)
