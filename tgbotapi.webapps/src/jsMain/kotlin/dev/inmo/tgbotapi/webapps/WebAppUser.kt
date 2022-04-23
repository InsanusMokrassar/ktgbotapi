package dev.inmo.tgbotapi.webapps

import dev.inmo.micro_utils.language_codes.IetfLanguageCode
import dev.inmo.tgbotapi.types.*

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
    @JsName(photoUrlField)
    val photoUrl: String?
}

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
        languageCode ?.let(::IetfLanguageCode)
    )
}
