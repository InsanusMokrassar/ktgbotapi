package dev.inmo.tgbotapi.types.MessageEntity

import dev.inmo.tgbotapi.CommonAbstracts.MultilevelTextSource
import dev.inmo.tgbotapi.CommonAbstracts.TextSource
import dev.inmo.tgbotapi.types.MessageEntity.textsources.*
import dev.inmo.tgbotapi.types.User
import kotlinx.serialization.Serializable

@Serializable
internal data class RawMessageEntity(
    val type: String,
    val offset: Int,
    val length: Int,
    val url: String? = null,
    val user: User? = null,
    val language: String? = null
) {
    internal val range by lazy {
        offset until (offset + length)
    }
}

internal fun RawMessageEntity.asTextSource(
    source: String,
    subParts: List<TextSource>
): TextSource {
    val sourceSubstring: String = source.substring(range)
    val subPartsWithRegulars by lazy {
        subParts.fillWithRegulars(sourceSubstring)
    }
    return when (type) {
        "mention" -> MentionTextSource(sourceSubstring, subPartsWithRegulars)
        "hashtag" -> HashTagTextSource(sourceSubstring, subPartsWithRegulars)
        "cashtag" -> CashTagTextSource(sourceSubstring, subPartsWithRegulars)
        "bot_command" -> BotCommandTextSource(sourceSubstring)
        "url" -> URLTextSource(sourceSubstring)
        "email" -> EMailTextSource(sourceSubstring, subPartsWithRegulars)
        "phone_number" -> PhoneNumberTextSource(sourceSubstring, subPartsWithRegulars)
        "bold" -> BoldTextSource(sourceSubstring, subPartsWithRegulars)
        "italic" -> ItalicTextSource(sourceSubstring, subPartsWithRegulars)
        "code" -> CodeTextSource(sourceSubstring)
        "pre" -> PreTextSource(sourceSubstring, language)
        "text_link" -> TextLinkTextSource(sourceSubstring, url ?: throw IllegalStateException("URL must not be null for text link"))
        "text_mention" -> TextMentionTextSource(sourceSubstring, user ?: throw IllegalStateException("User must not be null for text mention"), subPartsWithRegulars)
        "underline" -> UnderlineTextSource(sourceSubstring, subPartsWithRegulars)
        "strikethrough" -> StrikethroughTextSource(sourceSubstring, subPartsWithRegulars)
        else -> RegularTextSource(sourceSubstring)
    }
}

private inline operator fun <T : Comparable<T>> ClosedRange<T>.contains(other: ClosedRange<T>): Boolean {
    return start <= other.start && endInclusive >= other.endInclusive
}

internal fun List<TextSource>.fillWithRegulars(source: String): List<TextSource> {
    var index = 0
    val result = mutableListOf<TextSource>()
    for (i in 0 until size) {
        val textSource = get(i)
        val thisSourceInStart = source.startsWith(textSource.source, index)
        if (!thisSourceInStart) {
            val regularEndIndex = source.indexOf(textSource.source)
            result.add(regular(source.substring(index, regularEndIndex)))
            index = regularEndIndex
        }
        result.add(textSource)
        index += textSource.source.length
    }

    if (index != source.length) {
        result.add(regular(source.substring(index, source.length)))
    }

    return result
}

private fun createTextSources(originalFullString: String, entities: RawMessageEntities): List<TextSource> {
    val mutableEntities = entities.toMutableList().apply { sortBy { it.offset } }
    val resultList = mutableListOf<TextSource>()

    while (mutableEntities.isNotEmpty()) {
        var parent = mutableEntities.removeFirst()
        val subentities = mutableListOf<RawMessageEntity>()
        val toAddCutted = mutableListOf<RawMessageEntity>()
        while (mutableEntities.isNotEmpty()) {
            val potentialParent = mutableEntities.first()
            when {
                potentialParent.range.first > parent.range.last -> break
                potentialParent.range in parent.range -> {
                    subentities.add(potentialParent)
                }
                potentialParent.offset == parent.offset && potentialParent.length > parent.length -> {
                    subentities.add(parent)
                    parent = potentialParent
                }
                else -> { // need to cut
                    toAddCutted.add(potentialParent)
                }
            }
            mutableEntities.remove(potentialParent)
        }
        val subtextSources = if (subentities.isNotEmpty()) {
            mutableEntities.removeAll(subentities)
            if (toAddCutted.isNotEmpty()) {
                val borderIndex = parent.range.last + 1
                mutableEntities.addAll(
                    0,
                    toAddCutted.map {
                        val firstLength = borderIndex - it.offset
                        subentities.add(it.copy(length = firstLength))
                        it.copy(
                            offset = borderIndex,
                            length = it.length - firstLength
                        )
                    }
                )
            }
            createTextSources(originalFullString, subentities)
        } else {
            emptyList()
        }
        resultList.add(
            parent.asTextSource(
                originalFullString,
                subtextSources
            )
        )
    }

    return resultList
}

internal fun TextSource.toRawMessageEntities(offset: Int = 0): List<RawMessageEntity> {
    val source = source
    val length = source.length
    return listOfNotNull(
        when (this) {
            is MentionTextSource -> RawMessageEntity("mention", offset, length)
            is HashTagTextSource -> RawMessageEntity("hashtag", offset, length)
            is CashTagTextSource -> RawMessageEntity("cashtag", offset, length)
            is BotCommandTextSource -> RawMessageEntity("bot_command", offset, length)
            is URLTextSource -> RawMessageEntity("url", offset, length)
            is EMailTextSource -> RawMessageEntity("email", offset, length)
            is PhoneNumberTextSource -> RawMessageEntity("phone_number", offset, length)
            is BoldTextSource -> RawMessageEntity("bold", offset, length)
            is ItalicTextSource -> RawMessageEntity("italic", offset, length)
            is CodeTextSource -> RawMessageEntity("code", offset, length)
            is PreTextSource -> RawMessageEntity("pre", offset, length, language = language)
            is TextLinkTextSource -> RawMessageEntity("text_link", offset, length, url)
            is TextMentionTextSource -> RawMessageEntity("text_mention", offset, length, user = user)
            is UnderlineTextSource -> RawMessageEntity("underline", offset, length)
            is StrikethroughTextSource -> RawMessageEntity("strikethrough", offset, length)
            else -> null
        }
    ) + if (this is MultilevelTextSource) {
        subsources.toRawMessageEntities(offset)
    } else {
        emptyList()
    }
}


internal fun List<TextSource>.toRawMessageEntities(preOffset: Int = 0): List<RawMessageEntity> {
    var i = preOffset
    return flatMap {
        it.toRawMessageEntities(i).also {
            i += it.maxByOrNull { it.length }!!.length + 1
        }
    }
}

fun String.removeLeading(word: String) = if (startsWith(word)){
    substring(word.length)
} else {
    this
}

internal fun List<TextSource>.toRawMessageEntities(): List<RawMessageEntity> = toRawMessageEntities(0)

internal fun RawMessageEntities.asTextSources(sourceString: String): List<TextSource> = createTextSources(sourceString, this).fillWithRegulars(sourceString)

internal typealias RawMessageEntities = List<RawMessageEntity>
