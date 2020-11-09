package dev.inmo.tgbotapi.extensions.utils.formatting

import dev.inmo.tgbotapi.CommonAbstracts.TextSource
import dev.inmo.tgbotapi.types.User

fun buildEntities(init: EntitiesBuilder.() -> Unit): List<TextSource> {
    val builder = EntitiesBuilder()
    builder.init()
    return builder.build()
}

class EntitiesBuilder internal constructor(
    private val entitiesList: MutableList<TextSource> = mutableListOf()
) {
    fun build(): List<TextSource> {
        return entitiesList
    }

    fun add(source: TextSource) {
        entitiesList.add(source)
    }

    operator fun String.unaryPlus() {
        add(dev.inmo.tgbotapi.types.MessageEntity.textsources.regular(this))
    }
}

fun EntitiesBuilder.bold(parts: List<TextSource>) = add(dev.inmo.tgbotapi.types.MessageEntity.textsources.bold(parts))

fun EntitiesBuilder.bold(vararg parts: TextSource) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.bold(*parts))

fun EntitiesBuilder.bold(text: String) = add(dev.inmo.tgbotapi.types.MessageEntity.textsources.bold(text))

fun EntitiesBuilder.botCommand(command: String) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.botCommand(command))

fun EntitiesBuilder.cashTag(parts: List<TextSource>) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.cashTag(parts))

fun EntitiesBuilder.cashTag(vararg parts: TextSource) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.cashTag(*parts))

fun EntitiesBuilder.cashTag(text: String) = add(dev.inmo.tgbotapi.types.MessageEntity.textsources.cashTag(text))

fun EntitiesBuilder.code(code: String) = add(dev.inmo.tgbotapi.types.MessageEntity.textsources.code(code))

fun EntitiesBuilder.email(parts: List<TextSource>) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.email(parts))

fun EntitiesBuilder.email(vararg parts: TextSource) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.email(*parts))

fun EntitiesBuilder.email(emailAddress: String) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.email(emailAddress))

fun EntitiesBuilder.hashtag(parts: List<TextSource>) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.hashtag(parts))

fun EntitiesBuilder.hashtag(vararg parts: TextSource) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.hashtag(*parts))

fun EntitiesBuilder.hashtag(hashtag: String) = add(dev.inmo.tgbotapi.types.MessageEntity.textsources.hashtag(hashtag))

fun EntitiesBuilder.italic(parts: List<TextSource>) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.italic(parts))

fun EntitiesBuilder.italic(vararg parts: TextSource) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.italic(*parts))

fun EntitiesBuilder.italic(text: String) = add(dev.inmo.tgbotapi.types.MessageEntity.textsources.italic(text))

fun EntitiesBuilder.mention(parts: List<TextSource>) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.mention(parts))

fun EntitiesBuilder.mention(vararg parts: TextSource) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.mention(*parts))

fun EntitiesBuilder.mention(whoToMention: String) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.mention(whoToMention))

fun EntitiesBuilder.mention(parts: List<TextSource>, user: User) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.mention(parts, user))

fun EntitiesBuilder.mention(user: User, vararg parts: TextSource) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.mention(user, *parts))

fun EntitiesBuilder.mention(text: String, user: User) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.mention(text, user))

fun EntitiesBuilder.phone(parts: List<TextSource>) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.phone(parts))

fun EntitiesBuilder.phone(vararg parts: TextSource) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.phone(*parts))

fun EntitiesBuilder.phone(number: String) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.phone(number))

fun EntitiesBuilder.pre(code: String, language: String?) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.pre(code, language))

fun EntitiesBuilder.regular(text: String) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.regular(text))

fun EntitiesBuilder.strikethrough(parts: List<TextSource>) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.strikethrough(parts))

fun EntitiesBuilder.strikethrough(vararg parts: TextSource) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.strikethrough(*parts))

fun EntitiesBuilder.strikethrough(text: String) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.strikethrough(text))

fun EntitiesBuilder.link(text: String, url: String) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.link(text, url))

fun EntitiesBuilder.link(url: String) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.link(url))

fun EntitiesBuilder.underline(parts: List<TextSource>) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.underline(parts))

fun EntitiesBuilder.underline(vararg parts: TextSource) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.underline(*parts))

fun EntitiesBuilder.underline(text: String) =
    add(dev.inmo.tgbotapi.types.MessageEntity.textsources.underline(text))
