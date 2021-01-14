package dev.inmo.tgbotapi.types.passport.encrypted_data

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.passport.Base64EncodedData
import dev.inmo.tgbotapi.types.passport.EncryptedAndBase64EncodedData
import dev.inmo.tgbotapi.types.passport.encrypted_data.abstracts.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(EncryptedElementSerializer::class)
sealed class TranslatableIDDocument : WithData, WithFrontSide, WithReverseSide, WithSelfie, Translatable

@Serializable(EncryptedElementSerializer::class)
data class DriverLicense(
    @SerialName(dataField)
    override val data: EncryptedAndBase64EncodedData,
    @SerialName(frontSideField)
    override val frontSide: PassportFile?,
    @SerialName(reverseSideField)
    override val reverseSide: PassportFile?,
    @SerialName(selfieField)
    override val selfie: PassportFile?,
    @SerialName(translationField)
    override val translations: List<PassportFile>,
    @SerialName(hashField)
    override val hash: Base64EncodedData
) : TranslatableIDDocument()

@Serializable(EncryptedElementSerializer::class)
data class IdentityCard(
    @SerialName(dataField)
    override val data: EncryptedAndBase64EncodedData,
    @SerialName(frontSideField)
    override val frontSide: PassportFile?,
    @SerialName(reverseSideField)
    override val reverseSide: PassportFile?,
    @SerialName(selfieField)
    override val selfie: PassportFile?,
    @SerialName(translationField)
    override val translations: List<PassportFile>,
    @SerialName(hashField)
    override val hash: Base64EncodedData
) : TranslatableIDDocument()
