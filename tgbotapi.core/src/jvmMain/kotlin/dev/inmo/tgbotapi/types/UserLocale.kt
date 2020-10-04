package dev.inmo.tgbotapi.types

import java.util.*

fun CommonUser.javaLocale(): Locale? = languageCode ?.let {
    Locale.forLanguageTag(it)
}
