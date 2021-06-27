@file:Suppress("NOTHING_TO_INLINE", "unused")

package dev.inmo.tgbotapi.extensions.utils.formatting

import dev.inmo.tgbotapi.types.MessageEntity.textsources.*
import dev.inmo.tgbotapi.types.User

typealias EntitiesBuilderBody = EntitiesBuilder.() -> Unit

fun buildEntities(init: EntitiesBuilderBody): TextSourcesList = EntitiesBuilder().apply(init).build()

/**
 * This builder can be used to provide building of [TextSource]s [List]
 *
 * @see buildEntities
 */
class EntitiesBuilder internal constructor(
    private val entitiesList: MutableTextSourcesList = mutableListOf()
) {
    /**
     * It is not safe field which contains potentially changeable [List]
     */
    val entities: TextSourcesList
        get() = entitiesList

    /**
     * @return New immutable list which will be deattached from this builder
     */
    fun build(): TextSourcesList = entities.toList()

    fun add(source: TextSource): EntitiesBuilder {
        entitiesList.add(source)
        return this
    }

    fun addAll(sources: Iterable<TextSource>): EntitiesBuilder {
        entitiesList.addAll(sources)
        return this
    }

    operator fun TextSource.unaryPlus() = add(this)
    operator fun TextSourcesList.unaryPlus() = addAll(this)
    operator fun invoke(vararg source: TextSource) = addAll(source.toList())

    operator fun String.unaryPlus(): EntitiesBuilder {
        add(dev.inmo.tgbotapi.types.MessageEntity.textsources.regular(this))
        return this@EntitiesBuilder
    }

    operator fun plus(text: String) = text.unaryPlus()
    operator fun plus(source: TextSource) = add(source)
    operator fun plus(sources: Iterable<TextSource>) = addAll(sources)

    operator fun plus(other: EntitiesBuilder) = if (other == this) {
        // do nothing; assume user
        this
    } else {
        addAll(other.entitiesList)
    }
}

inline fun EntitiesBuilder.bold(parts: TextSourcesList) = add(dev.inmo.tgbotapi.types.MessageEntity.textsources.bold(parts))
inline fun EntitiesBuilder.bold(noinline init: EntitiesBuilderBody) = add(dev.inmo.tgbotapi.types.MessageEntity.textsources.bold(buildEntities(init)))

inline fun EntitiesBuilder.bold(vararg parts: TextSource) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.bold(*parts))

inline fun EntitiesBuilder.bold(text: String) = add(dev.inmo.tgbotapi.types.MessageEntity.textsources.bold(text))

inline fun EntitiesBuilder.botCommand(command: String) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.botCommand(command))

inline fun EntitiesBuilder.cashTag(parts: TextSourcesList) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.cashTag(parts))
inline fun EntitiesBuilder.cashTag(noinline init: EntitiesBuilderBody) = add(dev.inmo.tgbotapi.types.MessageEntity.textsources.cashTag(buildEntities(init)))

inline fun EntitiesBuilder.cashTag(vararg parts: TextSource) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.cashTag(*parts))

inline fun EntitiesBuilder.cashTag(text: String) = add(dev.inmo.tgbotapi.types.MessageEntity.textsources.cashTag(text))

inline fun EntitiesBuilder.code(code: String) = add(dev.inmo.tgbotapi.types.MessageEntity.textsources.code(code))

inline fun EntitiesBuilder.email(parts: TextSourcesList) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.email(parts))
inline fun EntitiesBuilder.email(noinline init: EntitiesBuilderBody) = add(dev.inmo.tgbotapi.types.MessageEntity.textsources.email(buildEntities(init)))

inline fun EntitiesBuilder.email(vararg parts: TextSource) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.email(*parts))

inline fun EntitiesBuilder.email(emailAddress: String) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.email(emailAddress))

inline fun EntitiesBuilder.hashtag(parts: TextSourcesList) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.hashtag(parts))
inline fun EntitiesBuilder.hashtag(noinline init: EntitiesBuilderBody) = add(dev.inmo.tgbotapi.types.MessageEntity.textsources.hashtag(buildEntities(init)))

inline fun EntitiesBuilder.hashtag(vararg parts: TextSource) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.hashtag(*parts))

inline fun EntitiesBuilder.hashtag(hashtag: String) = add(dev.inmo.tgbotapi.types.MessageEntity.textsources.hashtag(hashtag))

inline fun EntitiesBuilder.italic(parts: TextSourcesList) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.italic(parts))
inline fun EntitiesBuilder.italic(noinline init: EntitiesBuilderBody) = add(dev.inmo.tgbotapi.types.MessageEntity.textsources.italic(buildEntities(init)))

inline fun EntitiesBuilder.italic(vararg parts: TextSource) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.italic(*parts))

inline fun EntitiesBuilder.italic(text: String) = add(dev.inmo.tgbotapi.types.MessageEntity.textsources.italic(text))

inline fun EntitiesBuilder.mention(parts: TextSourcesList) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.mention(parts))
inline fun EntitiesBuilder.mention(noinline init: EntitiesBuilderBody) = add(dev.inmo.tgbotapi.types.MessageEntity.textsources.mention(buildEntities(init)))

inline fun EntitiesBuilder.mention(vararg parts: TextSource) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.mention(*parts))

inline fun EntitiesBuilder.mention(whoToMention: String) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.mention(whoToMention))

inline fun EntitiesBuilder.mention(parts: TextSourcesList, user: User) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.mention(parts, user))

inline fun EntitiesBuilder.mention(
    user: User,
    vararg parts: TextSource
) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.mention(user, *parts))

inline fun EntitiesBuilder.mention(text: String, user: User) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.mention(text, user))

inline fun EntitiesBuilder.phone(parts: TextSourcesList) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.phone(parts))
inline fun EntitiesBuilder.phone(noinline init: EntitiesBuilderBody) = add(dev.inmo.tgbotapi.types.MessageEntity.textsources.phone(buildEntities(init)))

inline fun EntitiesBuilder.phone(vararg parts: TextSource) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.phone(*parts))

inline fun EntitiesBuilder.phone(number: String) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.phone(number))

inline fun EntitiesBuilder.pre(code: String, language: String?) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.pre(code, language))

inline fun EntitiesBuilder.regular(text: String) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.regular(text))

inline fun EntitiesBuilder.strikethrough(parts: TextSourcesList) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.strikethrough(parts))
inline fun EntitiesBuilder.strikethrough(noinline init: EntitiesBuilderBody) = add(dev.inmo.tgbotapi.types.MessageEntity.textsources.strikethrough(buildEntities(init)))

inline fun EntitiesBuilder.strikethrough(vararg parts: TextSource) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.strikethrough(*parts))

inline fun EntitiesBuilder.strikethrough(text: String) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.strikethrough(text))

inline fun EntitiesBuilder.link(text: String, url: String) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.link(text, url))

inline fun EntitiesBuilder.link(url: String) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.link(url))

inline fun EntitiesBuilder.underline(parts: TextSourcesList) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.underline(parts))
inline fun EntitiesBuilder.underline(noinline init: EntitiesBuilderBody) = add(dev.inmo.tgbotapi.types.MessageEntity.textsources.underline(buildEntities(init)))

inline fun EntitiesBuilder.underline(vararg parts: TextSource) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.underline(*parts))

inline fun EntitiesBuilder.underline(text: String) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.underline(text))
