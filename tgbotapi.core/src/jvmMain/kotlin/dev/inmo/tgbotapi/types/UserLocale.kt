package dev.inmo.tgbotapi.types

import dev.inmo.micro_utils.language_codes.IetfLang
import dev.inmo.tgbotapi.types.abstracts.WithOptionalLanguageCode
import java.util.*

fun IetfLang?.javaLocale() = this ?.code ?.let { Locale.forLanguageTag(it) }

fun WithOptionalLanguageCode?.javaLocale() = this ?.ietfLanguageCode ?.javaLocale()
