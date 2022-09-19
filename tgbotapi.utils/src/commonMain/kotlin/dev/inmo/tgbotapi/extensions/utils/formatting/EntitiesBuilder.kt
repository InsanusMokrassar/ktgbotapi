@file:Suppress("NOTHING_TO_INLINE", "unused")

package dev.inmo.tgbotapi.extensions.utils.formatting

import dev.inmo.micro_utils.common.joinTo
import dev.inmo.tgbotapi.types.CustomEmojiId
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.message.textsources.*
import dev.inmo.tgbotapi.utils.RiskFeature

private const val ReplacedInCoreModuleReason = "Replaced in core module"
private const val CoreModulePackage = "dev.inmo.tgbotapi.utils"

@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("EntitiesBuilderBody", "$CoreModulePackage.EntitiesBuilderBody"))
typealias EntitiesBuilderBody = EntitiesBuilder.() -> Unit
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("newLine", "$CoreModulePackage.newLine"))
val newLine = regular("\n")

@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("buildEntities(separator, init)", "$CoreModulePackage.buildEntities"))
inline fun buildEntities(separator: TextSource? = null, init: EntitiesBuilderBody): TextSourcesList = EntitiesBuilder(separator).apply(init).build()
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("buildEntities(separator, init)", "$CoreModulePackage.buildEntities"))
inline fun buildEntities(separator: String, init: EntitiesBuilderBody) = buildEntities(regular(separator), init)

/**
 * This builder can be used to provide building of [TextSource]s [List]
 *
 * @see buildEntities
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("EntitiesBuilder", "$CoreModulePackage.EntitiesBuilder"))
typealias EntitiesBuilder = dev.inmo.tgbotapi.utils.EntitiesBuilder

/**
 * Add bold using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.bold]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("bold(parts)", "$CoreModulePackage.bold"))
inline fun EntitiesBuilder.bold(parts: TextSourcesList) = add(dev.inmo.tgbotapi.types.message.textsources.bold(parts))
/**
 * Version of [EntitiesBuilder.bold] with new line at the end
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("boldln(parts)", "$CoreModulePackage.boldln"))
inline fun EntitiesBuilder.boldln(parts: TextSourcesList) = bold(parts) + newLine
/**
 * Add bold using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.bold].
 * Will reuse separator config from [buildEntities]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("bold(init)", "$CoreModulePackage.bold"))
inline fun EntitiesBuilder.bold(noinline init: EntitiesBuilderBody) = add(dev.inmo.tgbotapi.types.message.textsources.bold(
    buildEntities(separator, init)
))
/**
 * Version of [EntitiesBuilder.bold] with new line at the end.
 * Will reuse separator config from [buildEntities]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("boldln(init)", "$CoreModulePackage.boldln"))
inline fun EntitiesBuilder.boldln(noinline init: EntitiesBuilderBody) = bold(init) + newLine
/**
 * Add bold using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.bold]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("bold(parts)", "$CoreModulePackage.bold"))
inline fun EntitiesBuilder.bold(vararg parts: TextSource) = add(dev.inmo.tgbotapi.types.message.textsources.bold(*parts))
/**
 * Version of [EntitiesBuilder.bold] with new line at the end
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("boldln(parts)", "$CoreModulePackage.boldln"))
inline fun EntitiesBuilder.boldln(vararg parts: TextSource) = bold(*parts) + newLine
/**
 * Add bold using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.bold]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("bold(text)", "$CoreModulePackage.bold"))
inline fun EntitiesBuilder.bold(text: String) = add(dev.inmo.tgbotapi.types.message.textsources.bold(text))
/**
 * Version of [EntitiesBuilder.bold] with new line at the end
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("boldln(text)", "$CoreModulePackage.boldln"))
inline fun EntitiesBuilder.boldln(text: String) = bold(text) + newLine

/**
 * Add spoiler using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.spoiler]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("spoiler(parts)", "$CoreModulePackage.spoiler"))
inline fun EntitiesBuilder.spoiler(parts: TextSourcesList) = add(dev.inmo.tgbotapi.types.message.textsources.spoiler(parts))
/**
 * Version of [EntitiesBuilder.spoiler] with new line at the end
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("spoilerln(parts)", "$CoreModulePackage.spoilerln"))
inline fun EntitiesBuilder.spoilerln(parts: TextSourcesList) = spoiler(parts) + newLine
/**
 * Add spoiler using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.spoiler].
 * Will reuse separator config from [buildEntities]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("spoiler(init)", "$CoreModulePackage.spoiler"))
inline fun EntitiesBuilder.spoiler(noinline init: EntitiesBuilderBody) = add(dev.inmo.tgbotapi.types.message.textsources.spoiler(
    buildEntities(separator, init)
))
/**
 * Version of [EntitiesBuilder.spoiler] with new line at the end.
 * Will reuse separator config from [buildEntities]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("spoilerln(init)", "$CoreModulePackage.spoilerln"))
inline fun EntitiesBuilder.spoilerln(noinline init: EntitiesBuilderBody) = spoiler(init) + newLine
/**
 * Add spoiler using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.spoiler]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("spoiler(parts)", "$CoreModulePackage.spoiler"))
inline fun EntitiesBuilder.spoiler(vararg parts: TextSource) = add(dev.inmo.tgbotapi.types.message.textsources.spoiler(*parts))
/**
 * Version of [EntitiesBuilder.spoiler] with new line at the end
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("spoilerln(parts)", "$CoreModulePackage.spoilerln"))
inline fun EntitiesBuilder.spoilerln(vararg parts: TextSource) = spoiler(*parts) + newLine
/**
 * Add spoiler using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.spoiler]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("spoiler(text)", "$CoreModulePackage.spoiler"))
inline fun EntitiesBuilder.spoiler(text: String) = add(dev.inmo.tgbotapi.types.message.textsources.spoiler(text))
/**
 * Version of [EntitiesBuilder.spoiler] with new line at the end
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("spoilerln(text)", "$CoreModulePackage.spoilerln"))
inline fun EntitiesBuilder.spoilerln(text: String) = spoiler(text) + newLine


/**
 * Add botCommand using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.botCommand]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("botCommand(command)", "$CoreModulePackage.botCommand"))
inline fun EntitiesBuilder.botCommand(command: String) = add(dev.inmo.tgbotapi.types.message.textsources.botCommand(command))
/**
 * Version of [EntitiesBuilder.botCommand] with new line at the end
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("botCommandln(command)", "$CoreModulePackage.botCommandln"))
inline fun EntitiesBuilder.botCommandln(command: String) = botCommand(command) + newLine


/**
 * Add cashTag using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.cashTag]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("cashTag(parts)", "$CoreModulePackage.cashTag"))
inline fun EntitiesBuilder.cashTag(parts: TextSourcesList) = add(dev.inmo.tgbotapi.types.message.textsources.cashTag(parts))
/**
 * Version of [EntitiesBuilder.cashTag] with new line at the end
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("cashTagln(parts)", "$CoreModulePackage.cashTagln"))
inline fun EntitiesBuilder.cashTagln(parts: TextSourcesList) = cashTag(parts) + newLine
/**
 * Add cashTag using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.cashTag].
 * Will reuse separator config from [buildEntities]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("cashTag(init)", "$CoreModulePackage.cashTag"))
inline fun EntitiesBuilder.cashTag(noinline init: EntitiesBuilderBody) = add(dev.inmo.tgbotapi.types.message.textsources.cashTag(
    buildEntities(separator, init)
))
/**
 * Version of [EntitiesBuilder.cashTag] with new line at the end.
 * Will reuse separator config from [buildEntities]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("cashTagln(init)", "$CoreModulePackage.cashTagln"))
inline fun EntitiesBuilder.cashTagln(noinline init: EntitiesBuilderBody) = cashTag(init) + newLine
/**
 * Add cashTag using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.cashTag]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("cashTag(parts)", "$CoreModulePackage.cashTag"))
inline fun EntitiesBuilder.cashTag(vararg parts: TextSource) = add(dev.inmo.tgbotapi.types.message.textsources.cashTag(*parts))
/**
 * Version of [EntitiesBuilder.cashTag] with new line at the end
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("cashTagln(parts)", "$CoreModulePackage.cashTagln"))
inline fun EntitiesBuilder.cashTagln(vararg parts: TextSource) = cashTag(*parts) + newLine
/**
 * Add cashTag using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.cashTag]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("cashTag(text)", "$CoreModulePackage.cashTag"))
inline fun EntitiesBuilder.cashTag(text: String) = add(dev.inmo.tgbotapi.types.message.textsources.cashTag(text))
/**
 * Version of [EntitiesBuilder.cashTag] with new line at the end
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("cashTagln(text)", "$CoreModulePackage.cashTagln"))
inline fun EntitiesBuilder.cashTagln(text: String) = cashTag(text) + newLine


/**
 * Add code using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.code]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("code(code)", "$CoreModulePackage.code"))
inline fun EntitiesBuilder.code(code: String) = add(dev.inmo.tgbotapi.types.message.textsources.code(code))
/**
 * Version of [EntitiesBuilder.code] with new line at the end
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("codeln(code)", "$CoreModulePackage.codeln"))
inline fun EntitiesBuilder.codeln(code: String) = code(code) + newLine


/**
 * Add email using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.email]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("email(parts)", "$CoreModulePackage.email"))
inline fun EntitiesBuilder.email(parts: TextSourcesList) = add(dev.inmo.tgbotapi.types.message.textsources.email(parts))
/**
 * Version of [EntitiesBuilder.email] with new line at the end
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("emailln(parts)", "$CoreModulePackage.emailln"))
inline fun EntitiesBuilder.emailln(parts: TextSourcesList) = email(parts) + newLine
/**
 * Add email using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.email].
 * Will reuse separator config from [buildEntities]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("email(init)", "$CoreModulePackage.email"))
inline fun EntitiesBuilder.email(noinline init: EntitiesBuilderBody) = add(dev.inmo.tgbotapi.types.message.textsources.email(
    buildEntities(separator, init)
))
/**
 * Version of [EntitiesBuilder.email] with new line at the end.
 * Will reuse separator config from [buildEntities]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("emailln(init)", "$CoreModulePackage.emailln"))
inline fun EntitiesBuilder.emailln(noinline init: EntitiesBuilderBody) = email(init) + newLine
/**
 * Add email using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.email]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("email(parts)", "$CoreModulePackage.email"))
inline fun EntitiesBuilder.email(vararg parts: TextSource) = add(dev.inmo.tgbotapi.types.message.textsources.email(*parts))
/**
 * Version of [EntitiesBuilder.email] with new line at the end
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("emailln(parts)", "$CoreModulePackage.emailln"))
inline fun EntitiesBuilder.emailln(vararg parts: TextSource) = email(*parts) + newLine
/**
 * Add email using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.email]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("email(emailAddress)", "$CoreModulePackage.email"))
inline fun EntitiesBuilder.email(emailAddress: String) = add(dev.inmo.tgbotapi.types.message.textsources.email(emailAddress))
/**
 * Version of [EntitiesBuilder.email] with new line at the end
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("emailln(emailAddress)", "$CoreModulePackage.emailln"))
inline fun EntitiesBuilder.emailln(emailAddress: String) = email(emailAddress) + newLine


/**
 * Add hashtag using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.hashtag]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("hashtag(parts)", "$CoreModulePackage.hashtag"))
inline fun EntitiesBuilder.hashtag(parts: TextSourcesList) = add(dev.inmo.tgbotapi.types.message.textsources.hashtag(parts))
/**
 * Version of [EntitiesBuilder.hashtag] with new line at the end
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("hashtagln(parts)", "$CoreModulePackage.hashtagln"))
inline fun EntitiesBuilder.hashtagln(parts: TextSourcesList) = hashtag(parts) + newLine
/**
 * Add hashtag using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.hashtag].
 * Will reuse separator config from [buildEntities]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("hashtag(init)", "$CoreModulePackage.hashtag"))
inline fun EntitiesBuilder.hashtag(noinline init: EntitiesBuilderBody) = add(dev.inmo.tgbotapi.types.message.textsources.hashtag(
    buildEntities(separator, init)
))
/**
 * Version of [EntitiesBuilder.hashtag] with new line at the end.
 * Will reuse separator config from [buildEntities]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("hashtagln(init)", "$CoreModulePackage.hashtagln"))
inline fun EntitiesBuilder.hashtagln(noinline init: EntitiesBuilderBody) = hashtag(init) + newLine
/**
 * Add hashtag using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.hashtag]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("hashtag(parts)", "$CoreModulePackage.hashtag"))
inline fun EntitiesBuilder.hashtag(vararg parts: TextSource) = add(dev.inmo.tgbotapi.types.message.textsources.hashtag(*parts))
/**
 * Version of [EntitiesBuilder.hashtag] with new line at the end
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("hashtagln(parts)", "$CoreModulePackage.hashtagln"))
inline fun EntitiesBuilder.hashtagln(vararg parts: TextSource) = hashtag(*parts) + newLine
/**
 * Add hashtag using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.hashtag]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("hashtag(hashtag)", "$CoreModulePackage.hashtag"))
inline fun EntitiesBuilder.hashtag(hashtag: String) = add(dev.inmo.tgbotapi.types.message.textsources.hashtag(hashtag))
/**
 * Version of [EntitiesBuilder.hashtag] with new line at the end
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("hashtagln(hashtag)", "$CoreModulePackage.hashtagln"))
inline fun EntitiesBuilder.hashtagln(hashtag: String) = hashtag(hashtag) + newLine


/**
 * Add italic using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.italic]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("italic(parts)", "$CoreModulePackage.italic"))
inline fun EntitiesBuilder.italic(parts: TextSourcesList) = add(dev.inmo.tgbotapi.types.message.textsources.italic(parts))
/**
 * Version of [EntitiesBuilder.italic] with new line at the end
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("italicln(parts)", "$CoreModulePackage.italicln"))
inline fun EntitiesBuilder.italicln(parts: TextSourcesList) = italic(parts) + newLine
/**
 * Add italic using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.italic].
 * Will reuse separator config from [buildEntities]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("italic(init)", "$CoreModulePackage.italic"))
inline fun EntitiesBuilder.italic(noinline init: EntitiesBuilderBody) = add(dev.inmo.tgbotapi.types.message.textsources.italic(
    buildEntities(separator, init)
))
/**
 * Version of [EntitiesBuilder.italic] with new line at the end.
 * Will reuse separator config from [buildEntities]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("italicln(init)", "$CoreModulePackage.italicln"))
inline fun EntitiesBuilder.italicln(noinline init: EntitiesBuilderBody) = italic(init) + newLine
/**
 * Add italic using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.italic]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("italic(parts)", "$CoreModulePackage.italic"))
inline fun EntitiesBuilder.italic(vararg parts: TextSource) = add(dev.inmo.tgbotapi.types.message.textsources.italic(*parts))
/**
 * Version of [EntitiesBuilder.italic] with new line at the end
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("italicln(parts)", "$CoreModulePackage.italicln"))
inline fun EntitiesBuilder.italicln(vararg parts: TextSource) = italic(*parts) + newLine
/**
 * Add italic using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.italic]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("italic(text)", "$CoreModulePackage.italic"))
inline fun EntitiesBuilder.italic(text: String) = add(dev.inmo.tgbotapi.types.message.textsources.italic(text))
/**
 * Version of [EntitiesBuilder.italic] with new line at the end
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("italicln(text)", "$CoreModulePackage.italicln"))
inline fun EntitiesBuilder.italicln(text: String) = italic(text) + newLine


/**
 * Add mention using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.mention]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("mention(parts)", "$CoreModulePackage.mention"))
inline fun EntitiesBuilder.mention(parts: TextSourcesList) = add(dev.inmo.tgbotapi.types.message.textsources.mention(parts))
/**
 * Version of [EntitiesBuilder.mention] with new line at the end
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("mentionln(parts)", "$CoreModulePackage.mentionln"))
inline fun EntitiesBuilder.mentionln(parts: TextSourcesList) = mention(parts) + newLine
/**
 * Add mention using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.mention].
 * Will reuse separator config from [buildEntities]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("mention(init)", "$CoreModulePackage.mention"))
inline fun EntitiesBuilder.mention(noinline init: EntitiesBuilderBody) = add(dev.inmo.tgbotapi.types.message.textsources.mention(
    buildEntities(separator, init)
))
/**
 * Version of [EntitiesBuilder.mention] with new line at the end.
 * Will reuse separator config from [buildEntities]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("mentionln(init)", "$CoreModulePackage.mentionln"))
inline fun EntitiesBuilder.mentionln(noinline init: EntitiesBuilderBody) = mention(init) + newLine
/**
 * Add mention using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.mention]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("mention(parts)", "$CoreModulePackage.mention"))
inline fun EntitiesBuilder.mention(vararg parts: TextSource) = add(dev.inmo.tgbotapi.types.message.textsources.mention(*parts))
/**
 * Version of [EntitiesBuilder.mention] with new line at the end
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("mentionln(parts)", "$CoreModulePackage.mentionln"))
inline fun EntitiesBuilder.mentionln(vararg parts: TextSource) = mention(*parts) + newLine
/**
 * Add mention using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.mention]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("mention(whoToMention)", "$CoreModulePackage.mention"))
inline fun EntitiesBuilder.mention(whoToMention: String) = add(dev.inmo.tgbotapi.types.message.textsources.mention(whoToMention))
/**
 * Version of [EntitiesBuilder.mention] with new line at the end
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("mentionln(whoToMention)", "$CoreModulePackage.mentionln"))
inline fun EntitiesBuilder.mentionln(whoToMention: String) = mention(whoToMention) + newLine
/**
 * Add mention using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.mention]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("mention(parts)", "$CoreModulePackage.mention"))
inline fun EntitiesBuilder.mention(parts: TextSourcesList, user: User) = add(dev.inmo.tgbotapi.types.message.textsources.mention(parts, user))
/**
 * Version of [EntitiesBuilder.mention] with new line at the end
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("mentionln(parts, user)", "$CoreModulePackage.mentionln"))
inline fun EntitiesBuilder.mentionln(parts: TextSourcesList, user: User) = mention(parts) + newLine
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("mention(user, parts)", "$CoreModulePackage.mention"))
inline fun EntitiesBuilder.mention(
    user: User,
    vararg parts: TextSource
) = add(dev.inmo.tgbotapi.types.message.textsources.mention(user, *parts))
/**
 * Version of [EntitiesBuilder.mention] with new line at the end
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("mentionln(user, parts)", "$CoreModulePackage.mentionln"))
inline fun EntitiesBuilder.mentionln(user: User, vararg parts: TextSource) = mention(user, *parts) + newLine
/**
 * Add mention using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.mention]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("mention(text, user)", "$CoreModulePackage.mention"))
inline fun EntitiesBuilder.mention(text: String, user: User) = add(dev.inmo.tgbotapi.types.message.textsources.mention(text, user))
/**
 * Version of [EntitiesBuilder.mention] with new line at the end
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("mentionln(text, user)", "$CoreModulePackage.mentionln"))
inline fun EntitiesBuilder.mentionln(text: String, user: User) = mention(text) + newLine


/**
 * Add phone using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.phone]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("phone(parts)", "$CoreModulePackage.phone"))
inline fun EntitiesBuilder.phone(parts: TextSourcesList) = add(dev.inmo.tgbotapi.types.message.textsources.phone(parts))
/**
 * Version of [EntitiesBuilder.phone] with new line at the end
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("phoneln(parts)", "$CoreModulePackage.phoneln"))
inline fun EntitiesBuilder.phoneln(parts: TextSourcesList) = phone(parts) + newLine
/**
 * Add phone using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.phone].
 * Will reuse separator config from [buildEntities]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("phone(init)", "$CoreModulePackage.phone"))
inline fun EntitiesBuilder.phone(noinline init: EntitiesBuilderBody) = add(dev.inmo.tgbotapi.types.message.textsources.phone(
    buildEntities(separator, init)
))
/**
 * Version of [EntitiesBuilder.phone] with new line at the end.
 * Will reuse separator config from [buildEntities]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("phoneln(init)", "$CoreModulePackage.phoneln"))
inline fun EntitiesBuilder.phoneln(noinline init: EntitiesBuilderBody) = phone(init) + newLine
/**
 * Add phone using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.phone]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("phone(parts)", "$CoreModulePackage.phone"))
inline fun EntitiesBuilder.phone(vararg parts: TextSource) = add(dev.inmo.tgbotapi.types.message.textsources.phone(*parts))
/**
 * Version of [EntitiesBuilder.phone] with new line at the end
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("phoneln(parts)", "$CoreModulePackage.phoneln"))
inline fun EntitiesBuilder.phoneln(vararg parts: TextSource) = phone(*parts) + newLine
/**
 * Add phone using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.phone]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("phone(number)", "$CoreModulePackage.phone"))
inline fun EntitiesBuilder.phone(number: String) = add(dev.inmo.tgbotapi.types.message.textsources.phone(number))
/**
 * Version of [EntitiesBuilder.phone] with new line at the end
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("phoneln(number)", "$CoreModulePackage.phoneln"))
inline fun EntitiesBuilder.phoneln(number: String) = phone(number) + newLine


/**
 * Add pre using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.pre]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("pre(code, language)", "$CoreModulePackage.pre"))
inline fun EntitiesBuilder.pre(code: String, language: String?) = add(dev.inmo.tgbotapi.types.message.textsources.pre(code, language))
/**
 * Version of [EntitiesBuilder.pre] with new line at the end
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("preln(code, language)", "$CoreModulePackage.preln"))
inline fun EntitiesBuilder.preln(code: String, language: String?) = pre(code) + newLine

/**
 * Will add simple [dev.inmo.tgbotapi.types.message.textsources.regular] [TextSource]
 *
 * @see RegularTextSource
 * @see dev.inmo.tgbotapi.extensions.utils.formatting.regularln
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("regular(text)", "$CoreModulePackage.regular"))
inline fun EntitiesBuilder.regular(text: String) =
    add(dev.inmo.tgbotapi.types.message.textsources.regular(text))
/**
 * Will add simple [dev.inmo.tgbotapi.types.message.textsources.regular] [TextSource] and "\n" at the end
 *
 * @see RegularTextSource
 * @see dev.inmo.tgbotapi.extensions.utils.formatting.regular
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("regularln(text)", "$CoreModulePackage.regularln"))
inline fun EntitiesBuilder.regularln(text: String) = regular(text) + newLine


/**
 * Add strikethrough using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.strikethrough]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("strikethrough(parts)", "$CoreModulePackage.strikethrough"))
inline fun EntitiesBuilder.strikethrough(parts: TextSourcesList) = add(dev.inmo.tgbotapi.types.message.textsources.strikethrough(parts))
/**
 * Version of [EntitiesBuilder.strikethrough] with new line at the end
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("strikethroughln(parts)", "$CoreModulePackage.strikethroughln"))
inline fun EntitiesBuilder.strikethroughln(parts: TextSourcesList) = strikethrough(parts) + newLine
/**
 * Add strikethrough using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.strikethrough].
 * Will reuse separator config from [buildEntities]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("strikethrough(init)", "$CoreModulePackage.strikethrough"))
inline fun EntitiesBuilder.strikethrough(noinline init: EntitiesBuilderBody) = add(dev.inmo.tgbotapi.types.message.textsources.strikethrough(
    buildEntities(separator, init)
))
/**
 * Version of [EntitiesBuilder.strikethrough] with new line at the end.
 * Will reuse separator config from [buildEntities]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("strikethroughln(init)", "$CoreModulePackage.strikethroughln"))
inline fun EntitiesBuilder.strikethroughln(noinline init: EntitiesBuilderBody) = strikethrough(init) + newLine
/**
 * Add strikethrough using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.strikethrough]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("strikethrough(parts)", "$CoreModulePackage.strikethrough"))
inline fun EntitiesBuilder.strikethrough(vararg parts: TextSource) = add(dev.inmo.tgbotapi.types.message.textsources.strikethrough(*parts))
/**
 * Version of [EntitiesBuilder.strikethrough] with new line at the end
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("strikethroughln(parts)", "$CoreModulePackage.strikethroughln"))
inline fun EntitiesBuilder.strikethroughln(vararg parts: TextSource) = strikethrough(*parts) + newLine
/**
 * Add strikethrough using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.strikethrough]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("strikethrough(text)", "$CoreModulePackage.strikethrough"))
inline fun EntitiesBuilder.strikethrough(text: String) = add(dev.inmo.tgbotapi.types.message.textsources.strikethrough(text))
/**
 * Version of [EntitiesBuilder.strikethrough] with new line at the end
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("strikethroughln(text)", "$CoreModulePackage.strikethroughln"))
inline fun EntitiesBuilder.strikethroughln(text: String) = strikethrough(text) + newLine


/**
 * Add link using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.link]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("link(text, url)", "$CoreModulePackage.link"))
inline fun EntitiesBuilder.link(text: String, url: String) = add(dev.inmo.tgbotapi.types.message.textsources.link(text, url))
/**
 * Version of [EntitiesBuilder.link] with new line at the end
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("linkln(text, url)", "$CoreModulePackage.linkln"))
inline fun EntitiesBuilder.linkln(text: String, url: String) = link(text, url) + newLine
/**
 * Add link using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.link]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("link(url)", "$CoreModulePackage.link"))
inline fun EntitiesBuilder.link(url: String) = add(dev.inmo.tgbotapi.types.message.textsources.link(url))
/**
 * Version of [EntitiesBuilder.link] with new line at the end
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("linkln(url)", "$CoreModulePackage.linkln"))
inline fun EntitiesBuilder.linkln(url: String) = link(url) + newLine


/**
 * Add underline using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.underline]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("underline(parts)", "$CoreModulePackage.underline"))
inline fun EntitiesBuilder.underline(parts: TextSourcesList) = add(dev.inmo.tgbotapi.types.message.textsources.underline(parts))
/**
 * Version of [EntitiesBuilder.underline] with new line at the end
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("underlineln(parts)", "$CoreModulePackage.underlineln"))
inline fun EntitiesBuilder.underlineln(parts: TextSourcesList) = underline(parts) + newLine
/**
 * Add underline using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.underline].
 * Will reuse separator config from [buildEntities]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("underline(init)", "$CoreModulePackage.underline"))
inline fun EntitiesBuilder.underline(noinline init: EntitiesBuilderBody) = add(dev.inmo.tgbotapi.types.message.textsources.underline(
    buildEntities(separator, init)
))
/**
 * Version of [EntitiesBuilder.underline] with new line at the end.
 * Will reuse separator config from [buildEntities]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("underlineln(init)", "$CoreModulePackage.underlineln"))
inline fun EntitiesBuilder.underlineln(noinline init: EntitiesBuilderBody) = underline(init) + newLine
/**
 * Add underline using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.underline]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("underline(parts)", "$CoreModulePackage.underline"))
inline fun EntitiesBuilder.underline(vararg parts: TextSource) = add(dev.inmo.tgbotapi.types.message.textsources.underline(*parts))
/**
 * Version of [EntitiesBuilder.underline] with new line at the end
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("underlineln(parts)", "$CoreModulePackage.underlineln"))
inline fun EntitiesBuilder.underlineln(vararg parts: TextSource) = underline(*parts) + newLine
/**
 * Add underline using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.underline]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("underline(text)", "$CoreModulePackage.underline"))
inline fun EntitiesBuilder.underline(text: String) = add(dev.inmo.tgbotapi.types.message.textsources.underline(text))
/**
 * Version of [EntitiesBuilder.underline] with new line at the end
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("underlineln(text)", "$CoreModulePackage.underlineln"))
inline fun EntitiesBuilder.underlineln(text: String) = underline(text) + newLine


/**
 * Add customEmoji using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.customEmoji]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("customEmoji(customEmojiId, parts)", "$CoreModulePackage.customEmoji"))
inline fun EntitiesBuilder.customEmoji(customEmojiId: CustomEmojiId, parts: TextSourcesList) = add(dev.inmo.tgbotapi.types.message.textsources.customEmoji(customEmojiId, parts))
/**
 * Version of [EntitiesBuilder.customEmoji] with new line at the end
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("customEmojiln(customEmojiId, parts)", "$CoreModulePackage.customEmojiln"))
inline fun EntitiesBuilder.customEmojiln(customEmojiId: CustomEmojiId, parts: TextSourcesList) = customEmoji(customEmojiId, parts) + newLine
/**
 * Add customEmoji using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.customEmoji].
 * Will reuse separator config from [buildEntities]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("customEmoji(customEmojiId, init)", "$CoreModulePackage.customEmoji"))
inline fun EntitiesBuilder.customEmoji(customEmojiId: CustomEmojiId, noinline init: EntitiesBuilderBody) = add(dev.inmo.tgbotapi.types.message.textsources.customEmoji(customEmojiId, buildEntities(separator, init)))
/**
 * Version of [EntitiesBuilder.customEmoji] with new line at the end.
 * Will reuse separator config from [buildEntities]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("customEmojiln(customEmojiId, init)", "$CoreModulePackage.customEmojiln"))
inline fun EntitiesBuilder.customEmojiln(customEmojiId: CustomEmojiId, noinline init: EntitiesBuilderBody) = customEmoji(customEmojiId, init) + newLine
/**
 * Add customEmoji using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.customEmoji]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("customEmoji(customEmojiId, parts)", "$CoreModulePackage.customEmoji"))
inline fun EntitiesBuilder.customEmoji(customEmojiId: CustomEmojiId, vararg parts: TextSource) = add(dev.inmo.tgbotapi.types.message.textsources.customEmoji(customEmojiId, *parts))
/**
 * Version of [EntitiesBuilder.customEmoji] with new line at the end
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("customEmojiln(customEmojiId, parts)", "$CoreModulePackage.customEmojiln"))
inline fun EntitiesBuilder.customEmojiln(customEmojiId: CustomEmojiId, vararg parts: TextSource) = customEmoji(customEmojiId, *parts) + newLine
/**
 * Add customEmoji using [EntitiesBuilder.add] with [dev.inmo.tgbotapi.types.message.textsources.customEmoji]
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("customEmoji(customEmojiId, text)", "$CoreModulePackage.customEmoji"))
inline fun EntitiesBuilder.customEmoji(customEmojiId: CustomEmojiId, text: String) = add(dev.inmo.tgbotapi.types.message.textsources.customEmoji(customEmojiId, text))
/**
 * Version of [EntitiesBuilder.customEmoji] with new line at the end
 */
@Deprecated(ReplacedInCoreModuleReason, ReplaceWith("customEmojiln(customEmojiId, text)", "$CoreModulePackage.customEmojiln"))
inline fun EntitiesBuilder.customEmojiln(customEmojiId: CustomEmojiId, text: String) = customEmoji(customEmojiId, text) + newLine
