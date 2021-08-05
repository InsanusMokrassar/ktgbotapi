package dev.inmo.tgbotapi.CommonAbstracts

import dev.inmo.micro_utils.language_codes.IetfLanguageCode

interface WithOptionalLanguageCode {
    val ietfLanguageCode: IetfLanguageCode?

    val languageCode: String?
        get() = ietfLanguageCode ?.code
}

interface WithLanguageCode : WithOptionalLanguageCode {
    override val ietfLanguageCode: IetfLanguageCode

    override val languageCode: String
        get() = ietfLanguageCode.code
}
