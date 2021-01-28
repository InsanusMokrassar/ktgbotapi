package dev.inmo.tgbotapi.types.passport.decrypted.abstracts

import dev.inmo.tgbotapi.types.passport.credentials.DataCredentials

interface SecureValueWithData : SecureValue {
    val data: DataCredentials?
}