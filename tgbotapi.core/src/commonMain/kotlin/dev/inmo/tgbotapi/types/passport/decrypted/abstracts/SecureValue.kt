package dev.inmo.tgbotapi.types.passport.decrypted.abstracts

import dev.inmo.tgbotapi.types.passport.credentials.EndDataCredentials

interface SecureValue {
    val credentials: List<EndDataCredentials>
}
