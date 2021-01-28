package dev.inmo.tgbotapi.types.passport.decrypted

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.passport.credentials.DataCredentials
import dev.inmo.tgbotapi.types.passport.credentials.FileCredentials
import dev.inmo.tgbotapi.types.passport.decrypted.abstracts.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class IdentityWithReverseSideSecureValue : SecureValueIdentity, SecureValueWithData, SecureValueWithTranslations, SecureValueWithReverseSide

@Serializable
data class DriverLicenseSecureValue(
    @SerialName(dataField)
    override val data: DataCredentials,
    @SerialName(frontSideField)
    override val frontSide: FileCredentials,
    @SerialName(reverseSideField)
    override val reverseSide: FileCredentials,
    @SerialName(selfieField)
    override val selfie: FileCredentials,
    @SerialName(translationField)
    override val translation: List<FileCredentials>
) : IdentityWithReverseSideSecureValue()

@Serializable
data class IdentityCardSecureValue(
    @SerialName(dataField)
    override val data: DataCredentials,
    @SerialName(frontSideField)
    override val frontSide: FileCredentials,
    @SerialName(reverseSideField)
    override val reverseSide: FileCredentials,
    @SerialName(selfieField)
    override val selfie: FileCredentials,
    @SerialName(translationField)
    override val translation: List<FileCredentials>
) : IdentityWithReverseSideSecureValue()
