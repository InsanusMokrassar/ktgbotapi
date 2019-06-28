package com.github.insanusmokrassar.TelegramBotAPI.utils

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.CaptionedInput
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.MessageEntity
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.MarkdownParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.captionLength
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.TextContent
import com.github.insanusmokrassar.TelegramBotAPI.types.textLength

@Deprecated(
    "Deprecated because old version have problem with long texts, but new one must return list of strings"
)
fun createMarkdownText(
    text: String,
    messageEntities: List<MessageEntity>
): String {
    return createMarkdownText(
        convertToFullMessageEntityList(text, messageEntities)
    ).first()
}

fun createMarkdownText(
    entities: List<MessageEntity>,
    partLength: Int = 4096
): List<String> = createFormattedText(entities, partLength, MarkdownParseMode)

@Deprecated(
    "Deprecated because old version have problem with long texts, but new one must return list of strings",
    ReplaceWith(
        "toMarkdownCaptions().firstOrNull()",
        "com.github.insanusmokrassar.TelegramBotAPI.utils.toMarkdownCaptions"
    )
)
fun CaptionedInput.toMarkdownCaption(): String? = toMarkdownCaptions().firstOrNull()

fun CaptionedInput.toMarkdownCaptions(): List<String> = createMarkdownText(
    fullEntitiesList(),
    captionLength.endInclusive + 1
)

@Deprecated(
    "Deprecated because old version have problem with long texts, but new one must return list of strings",
    ReplaceWith(
        "toMarkdownTexts().first()",
        "com.github.insanusmokrassar.TelegramBotAPI.utils.toMarkdownTexts"
    )
)
fun TextContent.toMarkdownText(): String = toMarkdownTexts().first()

fun TextContent.toMarkdownTexts(): List<String> = createMarkdownText(
    fullEntitiesList(),
    textLength.endInclusive + 1
)
