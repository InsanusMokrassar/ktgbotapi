package dev.inmo.tgbotapi.types.passport.decrypted.abstracts

import dev.inmo.tgbotapi.ksp.lib.ClassCastsIncluded
import dev.inmo.tgbotapi.types.passport.credentials.EndDataCredentials

@ClassCastsIncluded
interface SecureValue {
    val credentials: List<EndDataCredentials>
}
