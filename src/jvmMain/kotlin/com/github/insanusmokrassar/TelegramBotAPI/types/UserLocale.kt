package com.github.insanusmokrassar.TelegramBotAPI.types

import java.util.*

fun CommonUser.javaLocale(): Locale? = languageCode ?.let {
    Locale.forLanguageTag(it)
}
