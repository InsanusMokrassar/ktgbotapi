@file:Suppress("NOTHING_TO_INLINE", "unused")

package dev.inmo.tgbotapi.extensions.utils.formatting

import dev.inmo.tgbotapi.types.MessageEntity.textsources.MutableTextSourcesList
import dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.User

fun buildEntities(init: EntitiesBuilder.() -> Unit): TextSourcesList = EntitiesBuilder().apply(init).build()

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

    fun add(source: dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSource) {
        entitiesList.add(source)
    }

    operator fun dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSource.unaryPlus() = add(this)
    operator fun TextSourcesList.unaryPlus() = entitiesList.addAll(this)
    operator fun invoke(vararg source: dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSource) = entitiesList.addAll(source)

    operator fun String.unaryPlus() {
        add(dev.inmo.tgbotapi.types.MessageEntity.textsources.regular(this))
    }
}

inline fun EntitiesBuilder.bold(parts: TextSourcesList) = add(dev.inmo.tgbotapi.types.MessageEntity.textsources.bold(parts))

inline fun EntitiesBuilder.bold(vararg parts: dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSource) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.bold(*parts))

inline fun EntitiesBuilder.bold(text: String) = add(dev.inmo.tgbotapi.types.MessageEntity.textsources.bold(text))

inline fun EntitiesBuilder.botCommand(command: String) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.botCommand(command))

inline fun EntitiesBuilder.cashTag(parts: TextSourcesList) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.cashTag(parts))

inline fun EntitiesBuilder.cashTag(vararg parts: dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSource) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.cashTag(*parts))

inline fun EntitiesBuilder.cashTag(text: String) = add(dev.inmo.tgbotapi.types.MessageEntity.textsources.cashTag(text))

inline fun EntitiesBuilder.code(code: String) = add(dev.inmo.tgbotapi.types.MessageEntity.textsources.code(code))

inline fun EntitiesBuilder.email(parts: TextSourcesList) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.email(parts))

inline fun EntitiesBuilder.email(vararg parts: dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSource) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.email(*parts))

inline fun EntitiesBuilder.email(emailAddress: String) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.email(emailAddress))

inline fun EntitiesBuilder.hashtag(parts: TextSourcesList) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.hashtag(parts))

inline fun EntitiesBuilder.hashtag(vararg parts: dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSource) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.hashtag(*parts))

inline fun EntitiesBuilder.hashtag(hashtag: String) = add(dev.inmo.tgbotapi.types.MessageEntity.textsources.hashtag(hashtag))

inline fun EntitiesBuilder.italic(parts: TextSourcesList) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.italic(parts))

inline fun EntitiesBuilder.italic(vararg parts: dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSource) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.italic(*parts))

inline fun EntitiesBuilder.italic(text: String) = add(dev.inmo.tgbotapi.types.MessageEntity.textsources.italic(text))

inline fun EntitiesBuilder.mention(parts: TextSourcesList) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.mention(parts))

inline fun EntitiesBuilder.mention(vararg parts: dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSource) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.mention(*parts))

inline fun EntitiesBuilder.mention(whoToMention: String) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.mention(whoToMention))

inline fun EntitiesBuilder.mention(parts: TextSourcesList, user: User) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.mention(parts, user))

inline fun EntitiesBuilder.mention(
    user: User,
    vararg parts: dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSource
) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.mention(user, *parts))

inline fun EntitiesBuilder.mention(text: String, user: User) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.mention(text, user))

inline fun EntitiesBuilder.phone(parts: TextSourcesList) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.phone(parts))

inline fun EntitiesBuilder.phone(vararg parts: dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSource) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.phone(*parts))

inline fun EntitiesBuilder.phone(number: String) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.phone(number))

inline fun EntitiesBuilder.pre(code: String, language: String?) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.pre(code, language))

inline fun EntitiesBuilder.regular(text: String) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.regular(text))

inline fun EntitiesBuilder.strikethrough(parts: TextSourcesList) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.strikethrough(parts))

inline fun EntitiesBuilder.strikethrough(vararg parts: dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSource) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.strikethrough(*parts))

inline fun EntitiesBuilder.strikethrough(text: String) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.strikethrough(text))

inline fun EntitiesBuilder.link(text: String, url: String) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.link(text, url))

inline fun EntitiesBuilder.link(url: String) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.link(url))

inline fun EntitiesBuilder.underline(parts: TextSourcesList) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.underline(parts))

inline fun EntitiesBuilder.underline(vararg parts: dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSource) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.underline(*parts))

inline fun EntitiesBuilder.underline(text: String) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.underline(text))
