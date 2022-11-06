package dev.inmo.tgbotapi.extensions.api.chat.get

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.get.GetForumTopicIconStickers

suspend fun TelegramBot.getForumTopicIconStickers() = execute(GetForumTopicIconStickers)
