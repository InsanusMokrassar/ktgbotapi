package com.github.insanusmokrassar.TelegramBotAPI.utils

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.CaptionedInput
import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.MessageEntity
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.MarkdownParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.captionLength
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.TextContent
import com.github.insanusmokrassar.TelegramBotAPI.types.textLength

fun createMarkdownText(
    entities: List<TextSource>,
    partLength: Int = 4096
): List<String> = createFormattedText(entities, partLength, MarkdownParseMode)

fun CaptionedInput.toMarkdownCaptions(): List<String> = createMarkdownText(
    fullEntitiesList(),
    captionLength.endInclusive + 1
)

fun TextContent.toMarkdownTexts(): List<String> = createMarkdownText(
    fullEntitiesList(),
    textLength.endInclusive + 1
)
