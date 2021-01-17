package dev.inmo.tgbotapi.types.passport.encrypted_data

import dev.inmo.micro_utils.serialization.base64.Base64StringSerializer
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.passport.EncryptedData
import dev.inmo.tgbotapi.types.passport.encrypted_data.abstracts.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(EncryptedElementSerializer::class)
sealed class TranslatableIDDocument : WithData, WithFrontSide, WithReverseSide, WithSelfie, Translatable

@Serializable(EncryptedElementSerializer::class)
data class DriverLicense(
    @SerialName(dataField)
    override val data: EncryptedData,
    @SerialName(frontSideField)
    override val frontSide: PassportFile?,
    @SerialName(reverseSideField)
    override val reverseSide: PassportFile?,
    @SerialName(selfieField)
    override val selfie: PassportFile?,
    @SerialName(translationField)
    override val translations: List<PassportFile>,
    @SerialName(hashField)
    @Serializable(Base64StringSerializer::class)
    override val hash: String
) : TranslatableIDDocument()

@Serializable(EncryptedElementSerializer::class)
data class IdentityCard(
    @SerialName(dataField)
    override val data: EncryptedData,
    @SerialName(frontSideField)
    override val frontSide: PassportFile?,
    @SerialName(reverseSideField)
    override val reverseSide: PassportFile?,
    @SerialName(selfieField)
    override val selfie: PassportFile?,
    @SerialName(translationField)
    override val translations: List<PassportFile>,
    @SerialName(hashField)
    @Serializable(Base64StringSerializer::class)
    override val hash: String
) : TranslatableIDDocument()
