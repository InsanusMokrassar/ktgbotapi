package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.MultilevelTextSource
import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextPart
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources.*
import com.github.insanusmokrassar.TelegramBotAPI.types.User
import com.github.insanusmokrassar.TelegramBotAPI.utils.shiftSourcesToTheLeft
import kotlinx.serialization.Serializable

@Serializable
internal data class RawMessageEntity(
    val type: String,
    val offset: Int,
    val length: Int,
    val url: String? = null,
    val user: User? = null
)

internal fun RawMessageEntity.asTextParts(source: String, subParts: List<TextPart>): List<TextPart> {
    val sourceSubstring = source.substring(offset, offset + length)
    val range = offset until (offset + length)
    val shiftedSubParts = subParts.shiftSourcesToTheLeft(offset)
    return when (type) {
        "mention" -> MentionTextSource(sourceSubstring, shiftedSubParts)
        "hashtag" -> HashTagTextSource(sourceSubstring, shiftedSubParts)
        "cashtag" -> CashTagTextSource(sourceSubstring, shiftedSubParts)
        "bot_command" -> BotCommandTextSource(sourceSubstring, shiftedSubParts)
        "url" -> URLTextSource(sourceSubstring)
        "email" -> EMailTextSource(sourceSubstring, shiftedSubParts)
        "phone_number" -> PhoneNumberTextSource(sourceSubstring, shiftedSubParts)
        "bold" -> BoldTextSource(sourceSubstring, shiftedSubParts)
        "italic" -> ItalicTextSource(sourceSubstring, shiftedSubParts)
        "code" -> CodeTextSource(sourceSubstring)
        "pre" -> PreTextSource(sourceSubstring)
        "text_link" -> TextLinkTextSource(sourceSubstring, url ?: throw IllegalStateException("URL must not be null for text link"))
        "text_mention" -> TextMentionTextSource(sourceSubstring, user ?: throw IllegalStateException("User must not be null for text mention"), shiftedSubParts)
        "underline" -> UnderlineTextSource(sourceSubstring, shiftedSubParts)
        "strikethrough" -> StrikethroughTextSource(sourceSubstring, shiftedSubParts)
        else -> RegularTextSource(sourceSubstring)
    }.let {
        val part = TextPart(range, it)
        if (it !is MultilevelTextSource && subParts.isNotEmpty()) {
            (subParts + part).sortedBy { currentPart -> currentPart.range.first }
        } else {
            listOf(part)
        }
    }
}

internal fun createTextPart(from: String, entities: RawMessageEntities): List<TextPart> {
    val mutableEntities = entities.toMutableList()
    mutableEntities.sortBy { it.offset }
    val resultList = mutableListOf<TextPart>()

    while (mutableEntities.isNotEmpty()) {
        val currentFirst = mutableEntities.removeAt(0)
        val subEntities = if (mutableEntities.isNotEmpty()) {
            val lastIndex = currentFirst.offset + currentFirst.length
            val subEntities = mutableListOf<RawMessageEntity>()
            while (mutableEntities.isNotEmpty()) {
                val currentPossibleSubEntity = mutableEntities.first()
                if (currentPossibleSubEntity.offset < lastIndex) {
                    subEntities.add(currentPossibleSubEntity)
                    mutableEntities.removeAt(0)
                } else {
                    break
                }
            }
            subEntities
        } else {
            emptyList<RawMessageEntity>()
        }

        resultList.addAll(
            currentFirst.asTextParts(
                from,
                createTextPart(from, subEntities)
            )
        )
    }

    return resultList
}

internal fun RawMessageEntities.asTextParts(sourceString: String): List<TextPart> = createTextPart(sourceString, this)

internal typealias RawMessageEntities = List<RawMessageEntity>
