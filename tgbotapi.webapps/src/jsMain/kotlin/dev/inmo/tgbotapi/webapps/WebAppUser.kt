package dev.inmo.tgbotapi.webapps

import dev.inmo.micro_utils.language_codes.IetfLang
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
    @JsName(addedToAttachmentMenuField)
    val addedToAttachmentMenu: Boolean?
    @JsName(allowsWriteToPMField)
    val allowsWriteToPM: Boolean?
}

val WebAppUser.isPremium
    get() = is_premium == true

fun WebAppUser.asUser() = if (isBot == true) {
    CommonBot(
        id = UserId(id),
        firstName = firstName,
        lastName = lastName ?: "",
        username = username ?.let(::Username)
    )
} else {
    CommonUser(
        id = UserId(id),
        firstName = firstName,
        lastName = lastName ?: "",
        username = username ?.let(::Username),
        ietfLanguageCode = languageCode ?.let(::IetfLang),
        isPremium = isPremium
    )
}
