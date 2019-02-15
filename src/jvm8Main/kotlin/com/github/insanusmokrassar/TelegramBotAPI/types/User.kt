package com.github.insanusmokrassar.TelegramBotAPI.types

import java.util.*

val User.userLocale: Locale?
    get() = languageCode ?.let {
        Locale.forLanguageTag(it)
    }
