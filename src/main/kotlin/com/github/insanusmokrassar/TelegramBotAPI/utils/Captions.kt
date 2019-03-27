package com.github.insanusmokrassar.TelegramBotAPI.utils

import com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.MessageEntity
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.RegularTextMessageEntity
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.*
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.TextContent
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts.CaptionedMediaContent

fun CaptionedMediaContent.fullEntitiesList(): List<MessageEntity> = caption ?.let {
    convertToFullMessageEntityList(it, captionEntities)
} ?: emptyList()

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

fun createFormattedText(
    entities: List<MessageEntity>,
    partLength: Int = 4096,
    mode: ParseMode = MarkdownParseMode
): List<String> {
    val texts = mutableListOf<String>()
    val textBuilder = StringBuilder(partLength)
    for (entity in entities) {
        val string = when (mode) {
            is MarkdownParseMode -> entity.asMarkdownSource
            is HTMLParseMode -> entity.asHtmlSource
        }
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
