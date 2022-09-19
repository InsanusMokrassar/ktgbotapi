@file:Suppress("NOTHING_TO_INLINE", "unused")

package dev.inmo.tgbotapi.utils

import dev.inmo.micro_utils.common.joinTo
import dev.inmo.tgbotapi.types.CustomEmojiId
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.message.textsources.*

typealias EntitiesBuilderBody = EntitiesBuilder.() -> Unit
val newLine = regular("\n")

inline fun buildEntities(separator: TextSource? = null, init: EntitiesBuilderBody): TextSourcesList = EntitiesBuilder(separator).apply(init).build()
inline fun buildEntities(separator: String, init: EntitiesBuilderBody) = buildEntities(regular(separator), init)

/**
 * This builder can be used to provide building of [TextSource]s [List]
 *
 * @see buildEntities
 */
class EntitiesBuilder(
    val separator: TextSource? = null
) {
    private val entitiesList: MutableTextSourcesList = mutableListOf()

    /**
     * It is not safe field which contains potentially changeable [List]
     */
    val entities: TextSourcesList
        get() = entitiesList

    /**
     * @return New immutable list which will be deattached from this builder
     */
    fun build(): TextSourcesList = if (separator != null) entities.joinTo(separator) else entities.toList()

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
        add(dev.inmo.tgbotapi.types.message.textsources.regular(this))
        return this@EntitiesBuilder
    }

    operator fun plus(text: String) = text.unaryPlus()
    operator fun plus(source: TextSource) = add(source)
    operator fun plus(sources: Iterable<TextSource>) = addAll(sources)

    operator fun plus(other: EntitiesBuilder) = if (other == this) {
        // do nothing; assume user is using something like regular("Hello, ") + bold("world") in buildEntities
        this
    } else {
        addAll(other.entitiesList)
    }
}

/**
 * Add bold using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.bold]
 */
inline fun EntitiesBuilder.bold(parts: TextSourcesList) = add(dev.inmo.tgbotapi.types.message.textsources.bold(parts))
/**
 * Version of [EntitiesBuilder.bold] with new line at the end
 */
inline fun EntitiesBuilder.boldln(parts: TextSourcesList) = bold(parts) + newLine
/**
 * Add bold using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.bold].
 * Will reuse separator config from [buildEntities]
 */
inline fun EntitiesBuilder.bold(noinline init: EntitiesBuilderBody) = add(dev.inmo.tgbotapi.types.message.textsources.bold(
    buildEntities(separator, init)
))
/**
 * Version of [EntitiesBuilder.bold] with new line at the end.
 * Will reuse separator config from [buildEntities]
 */
inline fun EntitiesBuilder.boldln(noinline init: EntitiesBuilderBody) = bold(init) + newLine
/**
 * Add bold using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.bold]
 */
inline fun EntitiesBuilder.bold(vararg parts: TextSource) = add(dev.inmo.tgbotapi.types.message.textsources.bold(*parts))
/**
 * Version of [EntitiesBuilder.bold] with new line at the end
 */
inline fun EntitiesBuilder.boldln(vararg parts: TextSource) = bold(*parts) + newLine
/**
 * Add bold using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.bold]
 */
inline fun EntitiesBuilder.bold(text: String) = add(dev.inmo.tgbotapi.types.message.textsources.bold(text))
/**
 * Version of [EntitiesBuilder.bold] with new line at the end
 */
inline fun EntitiesBuilder.boldln(text: String) = bold(text) + newLine

/**
 * Add spoiler using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.spoiler]
 */
inline fun EntitiesBuilder.spoiler(parts: TextSourcesList) = add(dev.inmo.tgbotapi.types.message.textsources.spoiler(parts))
/**
 * Version of [EntitiesBuilder.spoiler] with new line at the end
 */
inline fun EntitiesBuilder.spoilerln(parts: TextSourcesList) = spoiler(parts) + newLine
/**
 * Add spoiler using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.spoiler].
 * Will reuse separator config from [buildEntities]
 */
inline fun EntitiesBuilder.spoiler(noinline init: EntitiesBuilderBody) = add(dev.inmo.tgbotapi.types.message.textsources.spoiler(
    buildEntities(separator, init)
))
/**
 * Version of [EntitiesBuilder.spoiler] with new line at the end.
 * Will reuse separator config from [buildEntities]
 */
inline fun EntitiesBuilder.spoilerln(noinline init: EntitiesBuilderBody) = spoiler(init) + newLine
/**
 * Add spoiler using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.spoiler]
 */
inline fun EntitiesBuilder.spoiler(vararg parts: TextSource) = add(dev.inmo.tgbotapi.types.message.textsources.spoiler(*parts))
/**
 * Version of [EntitiesBuilder.spoiler] with new line at the end
 */
inline fun EntitiesBuilder.spoilerln(vararg parts: TextSource) = spoiler(*parts) + newLine
/**
 * Add spoiler using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.spoiler]
 */
inline fun EntitiesBuilder.spoiler(text: String) = add(dev.inmo.tgbotapi.types.message.textsources.spoiler(text))
/**
 * Version of [EntitiesBuilder.spoiler] with new line at the end
 */
inline fun EntitiesBuilder.spoilerln(text: String) = spoiler(text) + newLine


/**
 * Add botCommand using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.botCommand]
 */
inline fun EntitiesBuilder.botCommand(command: String) = add(dev.inmo.tgbotapi.types.message.textsources.botCommand(command))
/**
 * Version of [EntitiesBuilder.botCommand] with new line at the end
 */
inline fun EntitiesBuilder.botCommandln(command: String) = botCommand(command) + newLine


/**
 * Add cashTag using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.cashTag]
 */
inline fun EntitiesBuilder.cashTag(parts: TextSourcesList) = add(dev.inmo.tgbotapi.types.message.textsources.cashTag(parts))
/**
 * Version of [EntitiesBuilder.cashTag] with new line at the end
 */
inline fun EntitiesBuilder.cashTagln(parts: TextSourcesList) = cashTag(parts) + newLine
/**
 * Add cashTag using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.cashTag].
 * Will reuse separator config from [buildEntities]
 */
inline fun EntitiesBuilder.cashTag(noinline init: EntitiesBuilderBody) = add(dev.inmo.tgbotapi.types.message.textsources.cashTag(
    buildEntities(separator, init)
))
/**
 * Version of [EntitiesBuilder.cashTag] with new line at the end.
 * Will reuse separator config from [buildEntities]
 */
inline fun EntitiesBuilder.cashTagln(noinline init: EntitiesBuilderBody) = cashTag(init) + newLine
/**
 * Add cashTag using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.cashTag]
 */
inline fun EntitiesBuilder.cashTag(vararg parts: TextSource) = add(dev.inmo.tgbotapi.types.message.textsources.cashTag(*parts))
/**
 * Version of [EntitiesBuilder.cashTag] with new line at the end
 */
inline fun EntitiesBuilder.cashTagln(vararg parts: TextSource) = cashTag(*parts) + newLine
/**
 * Add cashTag using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.cashTag]
 */
inline fun EntitiesBuilder.cashTag(text: String) = add(dev.inmo.tgbotapi.types.message.textsources.cashTag(text))
/**
 * Version of [EntitiesBuilder.cashTag] with new line at the end
 */
inline fun EntitiesBuilder.cashTagln(text: String) = cashTag(text) + newLine


/**
 * Add code using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.code]
 */
inline fun EntitiesBuilder.code(code: String) = add(dev.inmo.tgbotapi.types.message.textsources.code(code))
/**
 * Version of [EntitiesBuilder.code] with new line at the end
 */
inline fun EntitiesBuilder.codeln(code: String) = code(code) + newLine


/**
 * Add email using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.email]
 */
inline fun EntitiesBuilder.email(parts: TextSourcesList) = add(dev.inmo.tgbotapi.types.message.textsources.email(parts))
/**
 * Version of [EntitiesBuilder.email] with new line at the end
 */
inline fun EntitiesBuilder.emailln(parts: TextSourcesList) = email(parts) + newLine
/**
 * Add email using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.email].
 * Will reuse separator config from [buildEntities]
 */
inline fun EntitiesBuilder.email(noinline init: EntitiesBuilderBody) = add(dev.inmo.tgbotapi.types.message.textsources.email(
    buildEntities(separator, init)
))
/**
 * Version of [EntitiesBuilder.email] with new line at the end.
 * Will reuse separator config from [buildEntities]
 */
inline fun EntitiesBuilder.emailln(noinline init: EntitiesBuilderBody) = email(init) + newLine
/**
 * Add email using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.email]
 */
inline fun EntitiesBuilder.email(vararg parts: TextSource) = add(dev.inmo.tgbotapi.types.message.textsources.email(*parts))
/**
 * Version of [EntitiesBuilder.email] with new line at the end
 */
inline fun EntitiesBuilder.emailln(vararg parts: TextSource) = email(*parts) + newLine
/**
 * Add email using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.email]
 */
inline fun EntitiesBuilder.email(emailAddress: String) = add(dev.inmo.tgbotapi.types.message.textsources.email(emailAddress))
/**
 * Version of [EntitiesBuilder.email] with new line at the end
 */
inline fun EntitiesBuilder.emailln(emailAddress: String) = email(emailAddress) + newLine


/**
 * Add hashtag using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.hashtag]
 */
inline fun EntitiesBuilder.hashtag(parts: TextSourcesList) = add(dev.inmo.tgbotapi.types.message.textsources.hashtag(parts))
/**
 * Version of [EntitiesBuilder.hashtag] with new line at the end
 */
inline fun EntitiesBuilder.hashtagln(parts: TextSourcesList) = hashtag(parts) + newLine
/**
 * Add hashtag using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.hashtag].
 * Will reuse separator config from [buildEntities]
 */
inline fun EntitiesBuilder.hashtag(noinline init: EntitiesBuilderBody) = add(dev.inmo.tgbotapi.types.message.textsources.hashtag(
    buildEntities(separator, init)
))
/**
 * Version of [EntitiesBuilder.hashtag] with new line at the end.
 * Will reuse separator config from [buildEntities]
 */
inline fun EntitiesBuilder.hashtagln(noinline init: EntitiesBuilderBody) = hashtag(init) + newLine
/**
 * Add hashtag using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.hashtag]
 */
inline fun EntitiesBuilder.hashtag(vararg parts: TextSource) = add(dev.inmo.tgbotapi.types.message.textsources.hashtag(*parts))
/**
 * Version of [EntitiesBuilder.hashtag] with new line at the end
 */
inline fun EntitiesBuilder.hashtagln(vararg parts: TextSource) = hashtag(*parts) + newLine
/**
 * Add hashtag using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.hashtag]
 */
inline fun EntitiesBuilder.hashtag(hashtag: String) = add(dev.inmo.tgbotapi.types.message.textsources.hashtag(hashtag))
/**
 * Version of [EntitiesBuilder.hashtag] with new line at the end
 */
inline fun EntitiesBuilder.hashtagln(hashtag: String) = hashtag(hashtag) + newLine


/**
 * Add italic using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.italic]
 */
inline fun EntitiesBuilder.italic(parts: TextSourcesList) = add(dev.inmo.tgbotapi.types.message.textsources.italic(parts))
/**
 * Version of [EntitiesBuilder.italic] with new line at the end
 */
inline fun EntitiesBuilder.italicln(parts: TextSourcesList) = italic(parts) + newLine
/**
 * Add italic using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.italic].
 * Will reuse separator config from [buildEntities]
 */
inline fun EntitiesBuilder.italic(noinline init: EntitiesBuilderBody) = add(dev.inmo.tgbotapi.types.message.textsources.italic(
    buildEntities(separator, init)
))
/**
 * Version of [EntitiesBuilder.italic] with new line at the end.
 * Will reuse separator config from [buildEntities]
 */
inline fun EntitiesBuilder.italicln(noinline init: EntitiesBuilderBody) = italic(init) + newLine
/**
 * Add italic using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.italic]
 */
inline fun EntitiesBuilder.italic(vararg parts: TextSource) = add(dev.inmo.tgbotapi.types.message.textsources.italic(*parts))
/**
 * Version of [EntitiesBuilder.italic] with new line at the end
 */
inline fun EntitiesBuilder.italicln(vararg parts: TextSource) = italic(*parts) + newLine
/**
 * Add italic using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.italic]
 */
inline fun EntitiesBuilder.italic(text: String) = add(dev.inmo.tgbotapi.types.message.textsources.italic(text))
/**
 * Version of [EntitiesBuilder.italic] with new line at the end
 */
inline fun EntitiesBuilder.italicln(text: String) = italic(text) + newLine


/**
 * Add mention using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.mention]
 */
inline fun EntitiesBuilder.mention(parts: TextSourcesList) = add(dev.inmo.tgbotapi.types.message.textsources.mention(parts))
/**
 * Version of [EntitiesBuilder.mention] with new line at the end
 */
inline fun EntitiesBuilder.mentionln(parts: TextSourcesList) = mention(parts) + newLine
/**
 * Add mention using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.mention].
 * Will reuse separator config from [buildEntities]
 */
inline fun EntitiesBuilder.mention(noinline init: EntitiesBuilderBody) = add(dev.inmo.tgbotapi.types.message.textsources.mention(
    buildEntities(separator, init)
))
/**
 * Version of [EntitiesBuilder.mention] with new line at the end.
 * Will reuse separator config from [buildEntities]
 */
inline fun EntitiesBuilder.mentionln(noinline init: EntitiesBuilderBody) = mention(init) + newLine
/**
 * Add mention using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.mention]
 */
inline fun EntitiesBuilder.mention(vararg parts: TextSource) = add(dev.inmo.tgbotapi.types.message.textsources.mention(*parts))
/**
 * Version of [EntitiesBuilder.mention] with new line at the end
 */
inline fun EntitiesBuilder.mentionln(vararg parts: TextSource) = mention(*parts) + newLine
/**
 * Add mention using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.mention]
 */
inline fun EntitiesBuilder.mention(whoToMention: String) = add(dev.inmo.tgbotapi.types.message.textsources.mention(whoToMention))
/**
 * Version of [EntitiesBuilder.mention] with new line at the end
 */
inline fun EntitiesBuilder.mentionln(whoToMention: String) = mention(whoToMention) + newLine
/**
 * Add mention using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.mention]
 */
inline fun EntitiesBuilder.mention(parts: TextSourcesList, user: User) = add(dev.inmo.tgbotapi.types.message.textsources.mention(parts, user))
/**
 * Version of [EntitiesBuilder.mention] with new line at the end
 */
inline fun EntitiesBuilder.mentionln(parts: TextSourcesList, user: User) = mention(parts, user) + newLine
inline fun EntitiesBuilder.mention(
    user: User,
    vararg parts: TextSource
) = add(dev.inmo.tgbotapi.types.message.textsources.mention(user, *parts))
/**
 * Version of [EntitiesBuilder.mention] with new line at the end
 */
inline fun EntitiesBuilder.mentionln(user: User, vararg parts: TextSource) = mention(user, *parts) + newLine
/**
 * Add mention using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.mention]
 */
inline fun EntitiesBuilder.mention(text: String, user: User) = add(dev.inmo.tgbotapi.types.message.textsources.mention(text, user))
/**
 * Version of [EntitiesBuilder.mention] with new line at the end
 */
inline fun EntitiesBuilder.mentionln(text: String, user: User) = mention(text, user) + newLine


/**
 * Add phone using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.phone]
 */
inline fun EntitiesBuilder.phone(parts: TextSourcesList) = add(dev.inmo.tgbotapi.types.message.textsources.phone(parts))
/**
 * Version of [EntitiesBuilder.phone] with new line at the end
 */
inline fun EntitiesBuilder.phoneln(parts: TextSourcesList) = phone(parts) + newLine
/**
 * Add phone using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.phone].
 * Will reuse separator config from [buildEntities]
 */
inline fun EntitiesBuilder.phone(noinline init: EntitiesBuilderBody) = add(dev.inmo.tgbotapi.types.message.textsources.phone(
    buildEntities(separator, init)
))
/**
 * Version of [EntitiesBuilder.phone] with new line at the end.
 * Will reuse separator config from [buildEntities]
 */
inline fun EntitiesBuilder.phoneln(noinline init: EntitiesBuilderBody) = phone(init) + newLine
/**
 * Add phone using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.phone]
 */
inline fun EntitiesBuilder.phone(vararg parts: TextSource) = add(dev.inmo.tgbotapi.types.message.textsources.phone(*parts))
/**
 * Version of [EntitiesBuilder.phone] with new line at the end
 */
inline fun EntitiesBuilder.phoneln(vararg parts: TextSource) = phone(*parts) + newLine
/**
 * Add phone using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.phone]
 */
inline fun EntitiesBuilder.phone(number: String) = add(dev.inmo.tgbotapi.types.message.textsources.phone(number))
/**
 * Version of [EntitiesBuilder.phone] with new line at the end
 */
inline fun EntitiesBuilder.phoneln(number: String) = phone(number) + newLine


/**
 * Add pre using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.pre]
 */
inline fun EntitiesBuilder.pre(code: String, language: String?) = add(dev.inmo.tgbotapi.types.message.textsources.pre(code, language))
/**
 * Version of [EntitiesBuilder.pre] with new line at the end
 */
inline fun EntitiesBuilder.preln(code: String, language: String?) = pre(code, language) + newLine

/**
 * Will add simple [dev.inmo.tgbotapi.types.message.textsources.regular] [TextSource]
 *
 * @see RegularTextSource
 * @see dev.inmo.tgbotapi.extensions.utils.formatting.regularln
 */
inline fun EntitiesBuilder.regular(text: String) =
    add(dev.inmo.tgbotapi.types.message.textsources.regular(text))
/**
 * Will add simple [dev.inmo.tgbotapi.types.message.textsources.regular] [TextSource] and "\n" at the end
 *
 * @see RegularTextSource
 * @see dev.inmo.tgbotapi.extensions.utils.formatting.regular
 */
inline fun EntitiesBuilder.regularln(text: String) = regular(text) + newLine


/**
 * Add strikethrough using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.strikethrough]
 */
inline fun EntitiesBuilder.strikethrough(parts: TextSourcesList) = add(dev.inmo.tgbotapi.types.message.textsources.strikethrough(parts))
/**
 * Version of [EntitiesBuilder.strikethrough] with new line at the end
 */
inline fun EntitiesBuilder.strikethroughln(parts: TextSourcesList) = strikethrough(parts) + newLine
/**
 * Add strikethrough using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.strikethrough].
 * Will reuse separator config from [buildEntities]
 */
inline fun EntitiesBuilder.strikethrough(noinline init: EntitiesBuilderBody) = add(dev.inmo.tgbotapi.types.message.textsources.strikethrough(
    buildEntities(separator, init)
))
/**
 * Version of [EntitiesBuilder.strikethrough] with new line at the end.
 * Will reuse separator config from [buildEntities]
 */
inline fun EntitiesBuilder.strikethroughln(noinline init: EntitiesBuilderBody) = strikethrough(init) + newLine
/**
 * Add strikethrough using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.strikethrough]
 */
inline fun EntitiesBuilder.strikethrough(vararg parts: TextSource) = add(dev.inmo.tgbotapi.types.message.textsources.strikethrough(*parts))
/**
 * Version of [EntitiesBuilder.strikethrough] with new line at the end
 */
inline fun EntitiesBuilder.strikethroughln(vararg parts: TextSource) = strikethrough(*parts) + newLine
/**
 * Add strikethrough using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.strikethrough]
 */
inline fun EntitiesBuilder.strikethrough(text: String) = add(dev.inmo.tgbotapi.types.message.textsources.strikethrough(text))
/**
 * Version of [EntitiesBuilder.strikethrough] with new line at the end
 */
inline fun EntitiesBuilder.strikethroughln(text: String) = strikethrough(text) + newLine


/**
 * Add link using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.link]
 */
inline fun EntitiesBuilder.link(text: String, url: String) = add(dev.inmo.tgbotapi.types.message.textsources.link(text, url))
/**
 * Version of [EntitiesBuilder.link] with new line at the end
 */
inline fun EntitiesBuilder.linkln(text: String, url: String) = link(text, url) + newLine
/**
 * Add link using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.link]
 */
inline fun EntitiesBuilder.link(url: String) = add(dev.inmo.tgbotapi.types.message.textsources.link(url))
/**
 * Version of [EntitiesBuilder.link] with new line at the end
 */
inline fun EntitiesBuilder.linkln(url: String) = link(url) + newLine


/**
 * Add underline using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.underline]
 */
inline fun EntitiesBuilder.underline(parts: TextSourcesList) = add(dev.inmo.tgbotapi.types.message.textsources.underline(parts))
/**
 * Version of [EntitiesBuilder.underline] with new line at the end
 */
inline fun EntitiesBuilder.underlineln(parts: TextSourcesList) = underline(parts) + newLine
/**
 * Add underline using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.underline].
 * Will reuse separator config from [buildEntities]
 */
inline fun EntitiesBuilder.underline(noinline init: EntitiesBuilderBody) = add(dev.inmo.tgbotapi.types.message.textsources.underline(
    buildEntities(separator, init)
))
/**
 * Version of [EntitiesBuilder.underline] with new line at the end.
 * Will reuse separator config from [buildEntities]
 */
inline fun EntitiesBuilder.underlineln(noinline init: EntitiesBuilderBody) = underline(init) + newLine
/**
 * Add underline using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.underline]
 */
inline fun EntitiesBuilder.underline(vararg parts: TextSource) = add(dev.inmo.tgbotapi.types.message.textsources.underline(*parts))
/**
 * Version of [EntitiesBuilder.underline] with new line at the end
 */
inline fun EntitiesBuilder.underlineln(vararg parts: TextSource) = underline(*parts) + newLine
/**
 * Add underline using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.underline]
 */
inline fun EntitiesBuilder.underline(text: String) = add(dev.inmo.tgbotapi.types.message.textsources.underline(text))
/**
 * Version of [EntitiesBuilder.underline] with new line at the end
 */
inline fun EntitiesBuilder.underlineln(text: String) = underline(text) + newLine


/**
 * Add customEmoji using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.customEmoji]
 */
inline fun EntitiesBuilder.customEmoji(customEmojiId: CustomEmojiId, parts: TextSourcesList) = add(dev.inmo.tgbotapi.types.message.textsources.customEmoji(customEmojiId, parts))
/**
 * Version of [EntitiesBuilder.customEmoji] with new line at the end
 */
inline fun EntitiesBuilder.customEmojiln(customEmojiId: CustomEmojiId, parts: TextSourcesList) = customEmoji(customEmojiId, parts) + newLine
/**
 * Add customEmoji using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.customEmoji].
 * Will reuse separator config from [buildEntities]
 */
inline fun EntitiesBuilder.customEmoji(customEmojiId: CustomEmojiId, noinline init: EntitiesBuilderBody) = add(dev.inmo.tgbotapi.types.message.textsources.customEmoji(customEmojiId, buildEntities(separator, init)))
/**
 * Version of [EntitiesBuilder.customEmoji] with new line at the end.
 * Will reuse separator config from [buildEntities]
 */
inline fun EntitiesBuilder.customEmojiln(customEmojiId: CustomEmojiId, noinline init: EntitiesBuilderBody) = customEmoji(customEmojiId, init) + newLine
/**
 * Add customEmoji using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.customEmoji]
 */
inline fun EntitiesBuilder.customEmoji(customEmojiId: CustomEmojiId, vararg parts: TextSource) = add(dev.inmo.tgbotapi.types.message.textsources.customEmoji(customEmojiId, *parts))
/**
 * Version of [EntitiesBuilder.customEmoji] with new line at the end
 */
inline fun EntitiesBuilder.customEmojiln(customEmojiId: CustomEmojiId, vararg parts: TextSource) = customEmoji(customEmojiId, *parts) + newLine
/**
 * Add customEmoji using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.customEmoji]
 */
inline fun EntitiesBuilder.customEmoji(customEmojiId: CustomEmojiId, text: String) = add(dev.inmo.tgbotapi.types.message.textsources.customEmoji(customEmojiId, text))
/**
 * Version of [EntitiesBuilder.customEmoji] with new line at the end
 */
inline fun EntitiesBuilder.customEmojiln(customEmojiId: CustomEmojiId, text: String) = customEmoji(customEmojiId, text) + newLine
