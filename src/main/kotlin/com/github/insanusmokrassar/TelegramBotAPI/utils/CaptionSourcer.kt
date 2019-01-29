package com.github.insanusmokrassar.TelegramBotAPI.utils

import com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.MessageEntity
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.RegularTextMessageEntity
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.TextContent
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts.CaptionedMediaContent
import com.github.insanusmokrassar.TelegramBotAPI.utils.extensions.toMarkdown

fun createMarkdownText(
    text: String,
    messageEntities: List<MessageEntity>
): String {
    return convertToFullMessageEntityList(text, messageEntities).joinToString("", "", "") { it.asMarkdownSource }
}

fun CaptionedMediaContent.toMarkdownCaption(): String? = caption ?.let {
    createMarkdownText(
        it,
        captionEntities
    )
}

fun CaptionedMediaContent.fullEntitiesList(): List<MessageEntity> = caption ?.let {
    convertToFullMessageEntityList(it, captionEntities)
} ?: emptyList()

fun TextContent.toMarkdownText(): String = createMarkdownText(
    text,
    entities
)

fun TextContent.fullEntitiesList(): List<MessageEntity> = convertToFullMessageEntityList(text, entities)

fun convertToFullMessageEntityList(
    text: String,
    messageEntities: List<MessageEntity>
): List<MessageEntity> {
    val result = mutableListOf<MessageEntity>()

    var offset = 0
    for (entity in messageEntities) {
        val newEntitySize = entity.offset - offset
        if (newEntitySize > 0) {
            val regularEntity = RegularTextMessageEntity(offset, newEntitySize, text.substring(offset, entity.offset))
            result.add(regularEntity)
            offset += regularEntity.length
        }
        result.add(entity)
        offset += entity.length
    }
    val newEntitySize = text.length - offset
    if (newEntitySize > 0) {
        result.add(RegularTextMessageEntity(offset, newEntitySize, text.substring(offset, text.length)))
    }
    return result
}
