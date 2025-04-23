package dev.inmo.tgbotapi.types.passport.decrypted

import dev.inmo.tgbotapi.types.filesField
import dev.inmo.tgbotapi.types.passport.credentials.EndDataCredentials
import dev.inmo.tgbotapi.types.passport.credentials.FileCredentials
import dev.inmo.tgbotapi.types.passport.decrypted.abstracts.SecureValueWithFiles
import dev.inmo.tgbotapi.types.passport.decrypted.abstracts.SecureValueWithTranslations
import dev.inmo.tgbotapi.types.translationField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class OtherDocumentsSecureValue : SecureValueWithTranslations, SecureValueWithFiles {
    override val credentials: List<EndDataCredentials>
        get() = translation + files
}

@Serializable
data class UtilityBillSecureValue(
    @SerialName(translationField)
    override val translation: List<FileCredentials> = emptyList(),
    @SerialName(filesField)
    override val files: List<FileCredentials> = emptyList(),
) : OtherDocumentsSecureValue()

@Serializable
data class BankStatementSecureValue(
    @SerialName(translationField)
    override val translation: List<FileCredentials> = emptyList(),
    @SerialName(filesField)
    override val files: List<FileCredentials> = emptyList(),
) : OtherDocumentsSecureValue()

@Serializable
data class RentalAgreementSecureValue(
    @SerialName(translationField)
    override val translation: List<FileCredentials> = emptyList(),
    @SerialName(filesField)
    override val files: List<FileCredentials> = emptyList(),
) : OtherDocumentsSecureValue()

@Serializable
data class PassportRegistrationSecureValue(
    @SerialName(translationField)
    override val translation: List<FileCredentials> = emptyList(),
    @SerialName(filesField)
    override val files: List<FileCredentials> = emptyList(),
) : OtherDocumentsSecureValue()

@Serializable
data class TemporalRegistrationSecureValue(
    @SerialName(translationField)
    override val translation: List<FileCredentials> = emptyList(),
    @SerialName(filesField)
    override val files: List<FileCredentials> = emptyList(),
) : OtherDocumentsSecureValue()
