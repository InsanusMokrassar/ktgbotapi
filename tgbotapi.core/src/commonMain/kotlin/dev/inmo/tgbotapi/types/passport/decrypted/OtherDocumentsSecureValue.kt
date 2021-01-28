package dev.inmo.tgbotapi.types.passport.decrypted

import dev.inmo.tgbotapi.types.filesField
import dev.inmo.tgbotapi.types.passport.credentials.FileCredentials
import dev.inmo.tgbotapi.types.passport.decrypted.abstracts.SecureValueWithFiles
import dev.inmo.tgbotapi.types.passport.decrypted.abstracts.SecureValueWithTranslations
import dev.inmo.tgbotapi.types.translationField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class OtherDocumentsSecureValue : SecureValueWithTranslations, SecureValueWithFiles

@Serializable
data class UtilityBillSecureValue(
    @SerialName(translationField)
    override val translation: List<FileCredentials>,
    @SerialName(filesField)
    override val files: List<FileCredentials>
) : OtherDocumentsSecureValue()

@Serializable
data class BankStatementSecureValue(
    @SerialName(translationField)
    override val translation: List<FileCredentials>,
    @SerialName(filesField)
    override val files: List<FileCredentials>
) : OtherDocumentsSecureValue()

@Serializable
data class RentalAgreementSecureValue(
    @SerialName(translationField)
    override val translation: List<FileCredentials>,
    @SerialName(filesField)
    override val files: List<FileCredentials>
) : OtherDocumentsSecureValue()

@Serializable
data class PassportRegistrationSecureValue(
    @SerialName(translationField)
    override val translation: List<FileCredentials>,
    @SerialName(filesField)
    override val files: List<FileCredentials>
) : OtherDocumentsSecureValue()

@Serializable
data class TemporalRegistrationSecureValue(
    @SerialName(translationField)
    override val translation: List<FileCredentials>,
    @SerialName(filesField)
    override val files: List<FileCredentials>
) : OtherDocumentsSecureValue()
