package dev.inmo.tgbotapi.types.MessageEntity

import dev.inmo.tgbotapi.CommonAbstracts.*
import dev.inmo.tgbotapi.types.MessageEntity.textsources.*
import dev.inmo.tgbotapi.types.User
import dev.inmo.tgbotapi.utils.internal.fullListOfSubSource
import dev.inmo.tgbotapi.utils.internal.shiftSourcesToTheLeft
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

internal fun RawMessageEntity.asTextParts(
    source: String,
    subParts: List<TextPart>
): List<TextPart> {
    val sourceSubstring: String = source.substring(offset, offset + length)
    val range = offset until (offset + length)
    val shiftedSubSources = sourceSubstring.fullListOfSubSource(subParts.shiftSourcesToTheLeft(offset)).justTextSources()
    return when (type) {
        "mention" -> MentionTextSource(sourceSubstring, shiftedSubSources)
        "hashtag" -> HashTagTextSource(sourceSubstring, shiftedSubSources)
        "cashtag" -> CashTagTextSource(sourceSubstring, shiftedSubSources)
        "bot_command" -> BotCommandTextSource(sourceSubstring)
        "url" -> URLTextSource(sourceSubstring)
        "email" -> EMailTextSource(sourceSubstring, shiftedSubSources)
        "phone_number" -> PhoneNumberTextSource(sourceSubstring, shiftedSubSources)
        "bold" -> BoldTextSource(sourceSubstring, shiftedSubSources)
        "italic" -> ItalicTextSource(sourceSubstring, shiftedSubSources)
        "code" -> CodeTextSource(sourceSubstring)
        "pre" -> PreTextSource(sourceSubstring, language)
        "text_link" -> TextLinkTextSource(sourceSubstring, url ?: throw IllegalStateException("URL must not be null for text link"))
        "text_mention" -> TextMentionTextSource(sourceSubstring, user ?: throw IllegalStateException("User must not be null for text mention"), shiftedSubSources)
        "underline" -> UnderlineTextSource(sourceSubstring, shiftedSubSources)
        "strikethrough" -> StrikethroughTextSource(sourceSubstring, shiftedSubSources)
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

internal fun createTextPart(originalFullString: String, entities: RawMessageEntities): List<TextPart> {
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
                originalFullString,
                createTextPart(originalFullString, subEntities)
            )
        )
    }

    return resultList
}

internal fun TextPart.asRawMessageEntities(): List<RawMessageEntity> {
    val source = source
    val length = range.last - range.first + 1

    return listOfNotNull(
        when (source) {
            is MentionTextSource -> RawMessageEntity("mention", range.first, length)
            is HashTagTextSource -> RawMessageEntity("hashtag", range.first, length)
            is CashTagTextSource -> RawMessageEntity("cashtag", range.first, length)
            is BotCommandTextSource -> RawMessageEntity("bot_command", range.first, length)
            is URLTextSource -> RawMessageEntity("url", range.first, length)
            is EMailTextSource -> RawMessageEntity("email", range.first, length)
            is PhoneNumberTextSource -> RawMessageEntity("phone_number", range.first, length)
            is BoldTextSource -> RawMessageEntity("bold", range.first, length)
            is ItalicTextSource -> RawMessageEntity("italic", range.first, length)
            is CodeTextSource -> RawMessageEntity("code", range.first, length)
            is PreTextSource -> RawMessageEntity("pre", range.first, length, language = source.language)
            is TextLinkTextSource -> RawMessageEntity("text_link", range.first, length, source.url)
            is TextMentionTextSource -> RawMessageEntity("text_mention", range.first, length, user = source.user)
            is UnderlineTextSource -> RawMessageEntity("underline", range.first, length)
            is StrikethroughTextSource -> RawMessageEntity("strikethrough", range.first, length)
            else -> null
        }
    ) + if (source is MultilevelTextSource) {
        source.textParts(range.first).asRawMessageEntities()
    } else {
        emptyList()
    }
}

internal fun List<TextPart>.asRawMessageEntities(): List<RawMessageEntity> = flatMap { it.asRawMessageEntities() }

internal fun List<TextSource>.toTextParts(preOffset: Int = 0): List<TextPart> {
    var i = preOffset
    return map {
        TextPart(
            i until (i + it.source.length),
            it
        ).also {
            i = it.range.last + 1
        }
    }
}

fun String.removeLeading(word: String) = if (startsWith(word)){
    substring(word.length)
} else {
    this
}

internal fun List<TextSource>.toRawMessageEntities(): List<RawMessageEntity> = toTextParts().asRawMessageEntities()

internal fun RawMessageEntities.asTextParts(sourceString: String): List<TextPart> = sourceString.fullListOfSubSource(
    createTextPart(sourceString, this)
)

internal typealias RawMessageEntities = List<RawMessageEntity>
