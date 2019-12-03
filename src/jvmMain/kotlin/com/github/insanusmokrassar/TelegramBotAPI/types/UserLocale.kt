package com.github.insanusmokrassar.TelegramBotAPI.types

import java.util.*

fun User.javaLocale(): Locale? = languageCode ?.let {
    Locale.forLanguageTag(it)
}
