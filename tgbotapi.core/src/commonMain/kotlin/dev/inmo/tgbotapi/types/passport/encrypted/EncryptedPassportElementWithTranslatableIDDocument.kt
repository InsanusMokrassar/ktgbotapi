package dev.inmo.tgbotapi.types.passport.encrypted

import dev.inmo.micro_utils.serialization.base64.Base64BytesToFromStringSerializer
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.passport.credentials.EncryptedData
import dev.inmo.tgbotapi.types.passport.encrypted.abstracts.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(EncryptedElementSerializer::class)
sealed class EncryptedPassportElementWithTranslatableIDDocument : EncryptedPassportElementWithData, EncryptedPassportElementWithFrontSide, EncryptedPassportElementWithReverseSide, EncryptedPassportElementWithSelfie, EncryptedPassportElementTranslatable

@Serializable
data class DriverLicense(
    @SerialName(dataField)
    @Serializable(Base64BytesToFromStringSerializer::class)
    override val data: EncryptedData,
    @SerialName(frontSideField)
    override val frontSide: PassportFile? = null,
    @SerialName(reverseSideField)
    override val reverseSide: PassportFile? = null,
    @SerialName(selfieField)
    override val selfie: PassportFile? = null,
    @SerialName(translationField)
    override val translations: List<PassportFile> = emptyList(),
    @SerialName(hashField)
    @Serializable(Base64BytesToFromStringSerializer::class)
    override val hash: PassportElementHash
) : EncryptedPassportElementWithTranslatableIDDocument()

@Serializable
data class IdentityCard(
    @SerialName(dataField)
    @Serializable(Base64BytesToFromStringSerializer::class)
    override val data: EncryptedData,
    @SerialName(frontSideField)
    override val frontSide: PassportFile? = null,
    @SerialName(reverseSideField)
    override val reverseSide: PassportFile? = null,
    @SerialName(selfieField)
    override val selfie: PassportFile? = null,
    @SerialName(translationField)
    override val translations: List<PassportFile> = emptyList(),
    @SerialName(hashField)
    @Serializable(Base64BytesToFromStringSerializer::class)
    override val hash: PassportElementHash
) : EncryptedPassportElementWithTranslatableIDDocument()
