package dev.inmo.tgbotapi.types.passport.encrypted_data

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.passport.Base64EncodedData
import dev.inmo.tgbotapi.types.passport.encrypted_data.abstracts.FilesCollection
import dev.inmo.tgbotapi.types.passport.encrypted_data.abstracts.Translatable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(EncryptedElementSerializer::class)
sealed class TranslatableFilesCollection : Translatable, FilesCollection

@Serializable(EncryptedElementSerializer::class)
data class UtilityBill(
    @SerialName(filesField)
    override val files: List<PassportFile>,
    @SerialName(translationField)
    override val translations: List<PassportFile>,
    @SerialName(hashField)
    override val hash: Base64EncodedData
) : TranslatableFilesCollection()
@Serializable(EncryptedElementSerializer::class)
data class BankStatement(
    @SerialName(filesField)
    override val files: List<PassportFile>,
    @SerialName(translationField)
    override val translations: List<PassportFile>,
    @SerialName(hashField)
    override val hash: Base64EncodedData
) : TranslatableFilesCollection()
@Serializable(EncryptedElementSerializer::class)
data class RentalAgreement(
    @SerialName(filesField)
    override val files: List<PassportFile>,
    @SerialName(translationField)
    override val translations: List<PassportFile>,
    @SerialName(hashField)
    override val hash: Base64EncodedData
) : TranslatableFilesCollection()
@Serializable(EncryptedElementSerializer::class)
data class PassportRegistration(
    @SerialName(filesField)
    override val files: List<PassportFile>,
    @SerialName(translationField)
    override val translations: List<PassportFile>,
    @SerialName(hashField)
    override val hash: Base64EncodedData
) : TranslatableFilesCollection()
@Serializable(EncryptedElementSerializer::class)
data class TemporaryRegistration(
    @SerialName(filesField)
    override val files: List<PassportFile>,
    @SerialName(translationField)
    override val translations: List<PassportFile>,
    @SerialName(hashField)
    override val hash: Base64EncodedData
) : TranslatableFilesCollection()

