package dev.inmo.tgbotapi.types.passport.decrypted

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.passport.credentials.DataCredentials
import dev.inmo.tgbotapi.types.passport.credentials.FileCredentials
import dev.inmo.tgbotapi.types.passport.decrypted.abstracts.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class PassportSecureValue : SecureValueIdentity, SecureValueWithData, SecureValueWithTranslations

@Serializable
data class CommonPassportSecureValue(
    @SerialName(dataField)
    override val data: DataCredentials,
    @SerialName(frontSideField)
    override val frontSide: FileCredentials,
    @SerialName(selfieField)
    override val selfie: FileCredentials,
    @SerialName(translationField)
    override val translation: List<FileCredentials>
) : PassportSecureValue()

@Serializable
data class InternalPassportSecureValue(
    @SerialName(dataField)
    override val data: DataCredentials,
    @SerialName(frontSideField)
    override val frontSide: FileCredentials,
    @SerialName(selfieField)
    override val selfie: FileCredentials,
    @SerialName(translationField)
    override val translation: List<FileCredentials>
) : PassportSecureValue()

