package dev.inmo.tgbotapi.types.message

import dev.inmo.micro_utils.serialization.mapper.MapperSerializer
import dev.inmo.tgbotapi.types.CustomEmojiId
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.message.textsources.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer

@Serializable
internal data class RawMessageEntity(
    val type: String,
    val offset: Int,
    val length: Int,
    val url: String? = null,
    val user: User? = null,
    val language: String? = null,
    val custom_emoji_id: CustomEmojiId? = null
) {
    internal val range by lazy {
        offset until (offset + length)
    }

    val priority by lazy {
        when (type) {
            // Types with potential subsources should have priority
            "mention" -> 1
            "hashtag" -> 1
            "cashtag" -> 1
            "email" -> 1
            "phone_number" -> 1
            "bold" -> 1
            "blockquote" -> 0
            "italic" -> 1
            "text_mention" -> 1
            "strikethrough" -> 1
            "underline" -> 1
            "spoiler" -> 1
            "custom_emoji" -> 1
            "bot_command" -> 2
            "url" -> 2
            "code" -> 2
            "pre" -> 2
            "text_link" -> 2
            else -> 2
        }
    }
}

internal fun RawMessageEntity.asTextSource(
    source: String,
    subParts: List<Pair<Int, TextSource>>
): TextSource {
    val sourceSubstring: String = source.substring(range)
    val subPartsWithRegulars by lazy {
        subParts.map { (it.first - offset) to it.second }.fillWithRegulars(sourceSubstring)
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
        "blockquote" -> BlockquoteTextSource(sourceSubstring, subPartsWithRegulars)
        "italic" -> ItalicTextSource(sourceSubstring, subPartsWithRegulars)
        "code" -> CodeTextSource(sourceSubstring)
        "pre" -> PreTextSource(sourceSubstring, language)
        "text_link" -> TextLinkTextSource(
            sourceSubstring,
            url ?: throw IllegalStateException("URL must not be null for text link")
        )
        "text_mention" -> TextMentionTextSource(
            sourceSubstring,
            user ?: throw IllegalStateException("User must not be null for text mention"),
            subPartsWithRegulars
        )
        "underline" -> UnderlineTextSource(sourceSubstring, subPartsWithRegulars)
        "strikethrough" -> StrikethroughTextSource(sourceSubstring, subPartsWithRegulars)
        "spoiler" -> SpoilerTextSource(sourceSubstring, subPartsWithRegulars)
        "custom_emoji" -> CustomEmojiTextSource(sourceSubstring, custom_emoji_id ?: error("For custom emoji custom_emoji_id should exists"), subPartsWithRegulars)
        else -> RegularTextSource(sourceSubstring)
    }
}

private inline operator fun <T : Comparable<T>> ClosedRange<T>.contains(other: ClosedRange<T>): Boolean {
    return start <= other.start && endInclusive >= other.endInclusive
}

internal fun List<Pair<Int, TextSource>>.fillWithRegulars(source: String): TextSourcesList {
    var index = 0
    val result = mutableListOf<TextSource>()
    for (i in indices) {
        val (offset, textSource) = get(i)
        if (offset - index > 0) {
            result.add(regular(source.substring(index, offset)))
            index = offset
        }
        result.add(textSource)
        index += textSource.source.length
    }

    if (index != source.length) {
        result.add(regular(source.substring(index, source.length)))
    }

    return result
}

private fun createTextSources(
    originalFullString: String,
    entities: RawMessageEntities
): List<Pair<Int, TextSource>> {
    val mutableEntities = entities.toMutableList().apply {
        sortBy { it.priority } // sorting to fix potential issues in source sorting of entities
        sortBy { it.offset }
    }
    val resultList = mutableListOf<Pair<Int, TextSource>>()

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
            parent.offset to parent.asTextSource(
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
            is BlockquoteTextSource -> RawMessageEntity("blockquote", offset, length)
            is ItalicTextSource -> RawMessageEntity("italic", offset, length)
            is CodeTextSource -> RawMessageEntity("code", offset, length)
            is PreTextSource -> RawMessageEntity("pre", offset, length, language = language)
            is TextLinkTextSource -> RawMessageEntity("text_link", offset, length, url)
            is TextMentionTextSource -> RawMessageEntity("text_mention", offset, length, user = user)
            is UnderlineTextSource -> RawMessageEntity("underline", offset, length)
            is StrikethroughTextSource -> RawMessageEntity("strikethrough", offset, length)
            is SpoilerTextSource -> RawMessageEntity("spoiler", offset, length)
            is CustomEmojiTextSource -> RawMessageEntity("custom_emoji", offset, length, custom_emoji_id = customEmojiId)
            is RegularTextSource -> null
        }
    ) + if (this is MultilevelTextSource) {
        subsources.toRawMessageEntities(offset)
    } else {
        emptyList()
    }
}


internal fun TextSourcesList.toRawMessageEntities(preOffset: Int = 0): List<RawMessageEntity> {
    var i = preOffset
    return flatMap { textSource ->
        textSource.toRawMessageEntities(i).also {
            i += it.maxByOrNull { it.length }?.length ?: textSource.source.length
        }
    }
}

internal fun TextSourcesList.toRawMessageEntities(): List<RawMessageEntity> = toRawMessageEntities(0)

internal fun RawMessageEntities.asTextSources(sourceString: String): TextSourcesList =
    createTextSources(sourceString, this).fillWithRegulars(sourceString)

internal typealias RawMessageEntities = List<RawMessageEntity>
