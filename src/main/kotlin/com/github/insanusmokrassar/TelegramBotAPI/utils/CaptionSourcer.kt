package com.github.insanusmokrassar.TelegramBotAPI.utils

import com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.MessageEntity
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.RegularTextMessageEntity
import com.github.insanusmokrassar.TelegramBotAPI.types.captionLength
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.TextContent
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts.CaptionedMediaContent
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
): List<String> {
    val texts = mutableListOf<String>()
    val textBuilder = StringBuilder(partLength)
    for (entity in entities) {
        val string = entity.asMarkdownSource
        if (textBuilder.length + string.length > partLength) {
            if (textBuilder.isNotEmpty()) {
                texts.add(textBuilder.toString())
                textBuilder.clear()
            }
            val chunked = string.chunked(partLength)
            val last = chunked.last()
            textBuilder.append(last)
            val listToAdd = if (chunked.size > 1) {
                chunked.subList(0, chunked.size - 1)
            } else {
                emptyList()
            }
            listToAdd.forEach {
                texts.add(it)
            }
        } else {
            textBuilder.append(string)
        }
    }
    if (textBuilder.isNotEmpty()) {
        texts.add(textBuilder.toString())
        textBuilder.clear()
    }
    return texts
}

@Deprecated(
    "Deprecated because old version have problem with long texts, but new one must return list of strings"
)
fun CaptionedMediaContent.toMarkdownCaption(): String? = toMarkdownCaptions().firstOrNull()

fun CaptionedMediaContent.toMarkdownCaptions(): List<String> = createMarkdownText(
    fullEntitiesList(),
    captionLength.endInclusive + 1
)

fun CaptionedMediaContent.fullEntitiesList(): List<MessageEntity> = caption ?.let {
    convertToFullMessageEntityList(it, captionEntities)
} ?: emptyList()

@Deprecated(
    "Deprecated because old version have problem with long texts, but new one must return list of strings"
)
fun TextContent.toMarkdownText(): String = toMarkdownTexts().first()

fun TextContent.toMarkdownTexts(): List<String> = createMarkdownText(
    fullEntitiesList(),
    textLength.endInclusive + 1
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
