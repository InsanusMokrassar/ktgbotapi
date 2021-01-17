package dev.inmo.tgbotapi.types.passport.encrypted_data

import dev.inmo.micro_utils.serialization.base64.Base64StringSerializer
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.passport.encrypted_data.abstracts.FilesCollection
import dev.inmo.tgbotapi.types.passport.encrypted_data.abstracts.Translatable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(EncryptedElementSerializer::class)
sealed class TranslatableFilesCollection : Translatable, FilesCollection

@Serializable
data class UtilityBill(
    @SerialName(filesField)
    override val files: List<PassportFile>,
    @SerialName(translationField)
    override val translations: List<PassportFile>,
    @SerialName(hashField)
    @Serializable(Base64StringSerializer::class)
    override val hash: String
) : TranslatableFilesCollection()
@Serializable
data class BankStatement(
    @SerialName(filesField)
    override val files: List<PassportFile>,
    @SerialName(translationField)
    override val translations: List<PassportFile> = emptyList(),
    @SerialName(hashField)
    @Serializable(Base64StringSerializer::class)
    override val hash: String
) : TranslatableFilesCollection()
@Serializable
data class RentalAgreement(
    @SerialName(filesField)
    override val files: List<PassportFile>,
    @SerialName(translationField)
    override val translations: List<PassportFile> = emptyList(),
    @SerialName(hashField)
    @Serializable(Base64StringSerializer::class)
    override val hash: String
) : TranslatableFilesCollection()
@Serializable
data class PassportRegistration(
    @SerialName(filesField)
    override val files: List<PassportFile>,
    @SerialName(translationField)
    override val translations: List<PassportFile> = emptyList(),
    @SerialName(hashField)
    @Serializable(Base64StringSerializer::class)
    override val hash: String
) : TranslatableFilesCollection()
@Serializable
data class TemporaryRegistration(
    @SerialName(filesField)
    override val files: List<PassportFile>,
    @SerialName(translationField)
    override val translations: List<PassportFile> = emptyList(),
    @SerialName(hashField)
    @Serializable(Base64StringSerializer::class)
    override val hash: String
) : TranslatableFilesCollection()

