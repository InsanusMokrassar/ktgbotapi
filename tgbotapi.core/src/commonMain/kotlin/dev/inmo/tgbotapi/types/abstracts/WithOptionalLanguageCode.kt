package dev.inmo.tgbotapi.types.abstracts

import dev.inmo.micro_utils.language_codes.IetfLang

interface WithOptionalLanguageCode {
    val ietfLanguageCode: IetfLang?

    val languageCode: String?
        get() = ietfLanguageCode ?.code
}

