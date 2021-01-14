package dev.inmo.tgbotapi.types.passport.encrypted_data

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.passport.*
import dev.inmo.tgbotapi.types.passport.encrypted_data.abstracts.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(EncryptedElementSerializer::class)
sealed class Passport : WithData, WithFrontSide, WithSelfie, Translatable

@Serializable(EncryptedElementSerializer::class)
data class CommonPassport(
    @SerialName(dataField)
    override val data: EncryptedAndBase64EncodedData,
    @SerialName(frontSideField)
    override val frontSide: PassportFile?,
    @SerialName(selfieField)
    override val selfie: PassportFile?,
    @SerialName(translationField)
    override val translations: List<PassportFile>,
    @SerialName(hashField)
    override val hash: Base64EncodedData
) : Passport()
@Serializable(EncryptedElementSerializer::class)
data class InternalPassport(
    @SerialName(dataField)
    override val data: EncryptedAndBase64EncodedData,
    @SerialName(frontSideField)
    override val frontSide: PassportFile?,
    @SerialName(selfieField)
    override val selfie: PassportFile?,
    @SerialName(translationField)
    override val translations: List<PassportFile>,
    @SerialName(hashField)
    override val hash: Base64EncodedData
) : Passport()
