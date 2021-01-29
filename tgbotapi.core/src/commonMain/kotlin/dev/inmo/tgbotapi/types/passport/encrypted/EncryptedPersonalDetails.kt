package dev.inmo.tgbotapi.types.passport.encrypted

import dev.inmo.micro_utils.serialization.base64.Base64BytesToFromStringSerializer
import dev.inmo.tgbotapi.types.dataField
import dev.inmo.tgbotapi.types.passport.credentials.EncryptedData
import dev.inmo.tgbotapi.types.passport.encrypted.abstracts.PassportElementHash
import dev.inmo.tgbotapi.types.passport.encrypted.abstracts.EncryptedPassportElementWithData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EncryptedPersonalDetails(
    @SerialName(dataField)
    @Serializable(Base64BytesToFromStringSerializer::class)
    override val data: EncryptedData,
    @Serializable(Base64BytesToFromStringSerializer::class)
    override val hash: PassportElementHash
) : EncryptedPassportElementWithData
