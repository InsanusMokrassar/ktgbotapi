package dev.inmo.tgbotapi.types.passport.encrypted_data

import dev.inmo.micro_utils.serialization.base64.Base64BytesToFromStringSerializer
import dev.inmo.tgbotapi.types.dataField
import dev.inmo.tgbotapi.types.passport.EncryptedData
import dev.inmo.tgbotapi.types.passport.encrypted_data.abstracts.PassportElementHash
import dev.inmo.tgbotapi.types.passport.encrypted_data.abstracts.WithData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EncryptedPersonalDetails(
    @SerialName(dataField)
    @Serializable(Base64BytesToFromStringSerializer::class)
    override val data: EncryptedData,
    @Serializable(Base64BytesToFromStringSerializer::class)
    override val hash: PassportElementHash
) : WithData
