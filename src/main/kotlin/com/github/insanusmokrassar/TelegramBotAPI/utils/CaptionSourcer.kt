package com.github.insanusmokrassar.TelegramBotAPI.utils

import com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.MessageEntity
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.TextContent
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts.CaptionedMediaContent
import com.github.insanusmokrassar.TelegramBotAPI.utils.extensions.toMarkdown

fun createMarkdownText(
    text: String,
    messageEntities: List<MessageEntity>
): String {
    val builder = StringBuilder()
    var offset = 0
    for (entity in messageEntities) {
        builder.append(
            text.substring(offset until entity.offset).toMarkdown()
        )
        builder.append(
            entity.asMarkdownSource
        )
        offset += entity.length
    }
    builder.append(
        text.substring(offset).toMarkdown()
    )
    return builder.toString()
}

fun CaptionedMediaContent.toMarkdownCaption(): String? = caption ?.let {
    createMarkdownText(
        it,
        captionEntities
    )
}

fun TextContent.toMarkdownText(): String = createMarkdownText(
    text,
    entities
)
