package dev.inmo.tgbotapi.webapps

import dev.inmo.micro_utils.language_codes.IetfLanguageCode
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.CommonBot
import dev.inmo.tgbotapi.types.chat.CommonUser

external interface WebAppUser {
    val id: Identifier
    @JsName(isBotField)
    val isBot: Boolean?
    @JsName(firstNameField)
    val firstName: String
    @JsName(lastNameField)
    val lastName: String?
    @JsName(usernameField)
    val username: String?
    @JsName(languageCodeField)
    val languageCode: String?
    val is_premium: Boolean?
    @JsName(photoUrlField)
    val photoUrl: String?
}

val WebAppUser.isPremium
    get() = is_premium == true

fun WebAppUser.asUser() = if (isBot == true) {
    CommonBot(
        UserId(id),
        username ?.let(::Username) ?: error("Username is absent for bot, but must exists"),
        firstName,
        lastName ?: ""
    )
} else {
    CommonUser(
        UserId(id),
        firstName,
        lastName ?: "",
        username ?.let(::Username),
        languageCode ?.let(::IetfLanguageCode),
        isPremium = isPremium
    )
}
