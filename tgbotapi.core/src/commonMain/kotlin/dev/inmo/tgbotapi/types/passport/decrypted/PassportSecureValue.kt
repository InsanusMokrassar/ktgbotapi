package dev.inmo.tgbotapi.types.passport.decrypted

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.passport.credentials.*
import dev.inmo.tgbotapi.types.passport.decrypted.abstracts.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class PassportSecureValue : SecureValueIdentity, SecureValueWithData, SecureValueWithTranslations {
    override val credentials: List<EndDataCredentials>
        get() = listOfNotNull(data, frontSide, selfie) + translation
}

@Serializable
data class CommonPassportSecureValue(
    @SerialName(dataField)
    override val data: DataCredentials? = null,
    @SerialName(frontSideField)
    override val frontSide: FileCredentials? = null,
    @SerialName(selfieField)
    override val selfie: FileCredentials? = null,
    @SerialName(translationField)
    override val translation: List<FileCredentials> = emptyList(),
) : PassportSecureValue()

@Serializable
data class InternalPassportSecureValue(
    @SerialName(dataField)
    override val data: DataCredentials? = null,
    @SerialName(frontSideField)
    override val frontSide: FileCredentials? = null,
    @SerialName(selfieField)
    override val selfie: FileCredentials? = null,
    @SerialName(translationField)
    override val translation: List<FileCredentials> = emptyList(),
) : PassportSecureValue()
