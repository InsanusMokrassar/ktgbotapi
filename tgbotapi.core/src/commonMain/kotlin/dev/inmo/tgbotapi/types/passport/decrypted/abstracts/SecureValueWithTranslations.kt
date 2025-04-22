package dev.inmo.tgbotapi.types.passport.decrypted.abstracts

import dev.inmo.tgbotapi.types.passport.credentials.FileCredentials

interface SecureValueWithTranslations : SecureValue {
    val translation: List<FileCredentials>
}
