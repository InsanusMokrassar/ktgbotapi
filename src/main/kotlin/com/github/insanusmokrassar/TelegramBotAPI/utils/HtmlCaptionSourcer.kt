package com.github.insanusmokrassar.TelegramBotAPI.utils

import com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.MessageEntity
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.HTMLParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.captionLength
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.TextContent
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts.CaptionedMediaContent
import com.github.insanusmokrassar.TelegramBotAPI.types.textLength

fun createHtmlText(
    entities: List<MessageEntity>,
    partLength: Int = 4096
): List<String> = createFormattedText(entities, partLength, HTMLParseMode)

fun CaptionedMediaContent.toHtmlCaptions(): List<String> = createHtmlText(
    fullEntitiesList(),
    captionLength.endInclusive + 1
)

fun TextContent.toHtmlTexts(): List<String> = createHtmlText(
    fullEntitiesList(),
    textLength.endInclusive + 1
)
