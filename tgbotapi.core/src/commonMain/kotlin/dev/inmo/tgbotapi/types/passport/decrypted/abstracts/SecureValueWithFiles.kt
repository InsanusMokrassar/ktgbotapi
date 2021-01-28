package dev.inmo.tgbotapi.types.passport.decrypted.abstracts

import dev.inmo.tgbotapi.types.passport.credentials.FileCredentials

interface SecureValueWithFiles : SecureValue {
    val files: List<FileCredentials>
}