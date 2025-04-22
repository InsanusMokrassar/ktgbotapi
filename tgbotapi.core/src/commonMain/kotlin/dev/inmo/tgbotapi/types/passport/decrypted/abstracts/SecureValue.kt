package dev.inmo.tgbotapi.types.passport.decrypted.abstracts

import dev.inmo.tgbotapi.types.passport.credentials.EndDataCredentials
import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded

@ClassCastsIncluded
interface SecureValue {
    val credentials: List<EndDataCredentials>
}
