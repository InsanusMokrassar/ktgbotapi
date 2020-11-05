package dev.inmo.tgbotapi.types.MessageEntity

import dev.inmo.tgbotapi.CommonAbstracts.*
import dev.inmo.tgbotapi.types.MessageEntity.textsources.*
import dev.inmo.tgbotapi.types.User
import dev.inmo.tgbotapi.utils.shiftSourcesToTheLeft
import kotlinx.serialization.Serializable

@Serializable
internal data class RawMessageEntity(
    val type: String,
    val offset: Int,
    val length: Int,
    val url: String? = null,
    val user: User? = null,
    val language: String? = null
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
        "pre" -> PreTextSource(sourceSubstring, language)
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

internal fun List<TextPart>.asRawMessageEntities(): List<RawMessageEntity> = mapNotNull {
    val source = it.source
    when (source) {
        is MentionTextSource -> RawMessageEntity("mention", it.range.first, it.range.last - it.range.first)
        is HashTagTextSource -> RawMessageEntity("hashtag", it.range.first, it.range.last - it.range.first)
        is CashTagTextSource -> RawMessageEntity("cashtag", it.range.first, it.range.last - it.range.first)
        is BotCommandTextSource -> RawMessageEntity("bot_command", it.range.first, it.range.last - it.range.first)
        is URLTextSource -> RawMessageEntity("url", it.range.first, it.range.last - it.range.first)
        is EMailTextSource -> RawMessageEntity("email", it.range.first, it.range.last - it.range.first)
        is PhoneNumberTextSource -> RawMessageEntity("phone_number", it.range.first, it.range.last - it.range.first)
        is BoldTextSource -> RawMessageEntity("bold", it.range.first, it.range.last - it.range.first)
        is ItalicTextSource -> RawMessageEntity("italic", it.range.first, it.range.last - it.range.first)
        is CodeTextSource -> RawMessageEntity("code", it.range.first, it.range.last - it.range.first)
        is PreTextSource -> RawMessageEntity("pre", it.range.first, it.range.last - it.range.first, language = source.language)
        is TextLinkTextSource -> RawMessageEntity("text_link", it.range.first, it.range.last - it.range.first, source.url)
        is TextMentionTextSource -> RawMessageEntity("text_mention", it.range.first, it.range.last - it.range.first, user = source.user)
        is UnderlineTextSource -> RawMessageEntity("underline", it.range.first, it.range.last - it.range.first)
        is StrikethroughTextSource -> RawMessageEntity("strikethrough", it.range.first, it.range.last - it.range.first)
        else -> null
    }
}

internal fun List<TextSource>.toRawMessageEntities(): List<RawMessageEntity> {
    var i = 0
    return map {
        TextPart(
            i until it.source.length,
            it
        ).also {
            i = it.range.last + 1
        }
    }.asRawMessageEntities()
}

internal fun RawMessageEntities.asTextParts(sourceString: String): List<TextPart> = createTextPart(sourceString, this)

internal typealias RawMessageEntities = List<RawMessageEntity>
