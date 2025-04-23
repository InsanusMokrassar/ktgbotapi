package dev.inmo.tgbotapi.types.files

import dev.inmo.tgbotapi.types.ReplyInfo

interface MediaContentVariant : ReplyInfo.External.ContentVariant, TelegramMediaFile
