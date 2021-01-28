package dev.inmo.tgbotapi.types.passport.decrypted.abstracts

import dev.inmo.tgbotapi.types.passport.credentials.FileCredentials

interface SecureValueIdentity : SecureValue {
    val frontSide: FileCredentials
    val selfie: FileCredentials
}