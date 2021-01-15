package dev.inmo.tgbotapi.types.passport.encrypted_data

import dev.inmo.micro_utils.serialization.base64.Base64StringSerializer
import dev.inmo.tgbotapi.types.dataField
import dev.inmo.tgbotapi.types.passport.*
import dev.inmo.tgbotapi.types.passport.encrypted_data.abstracts.WithData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(EncryptedElementSerializer::class)
data class EncryptedAddress(
    @SerialName(dataField)
    override val data: EncryptedData,
    @Serializable(Base64StringSerializer::class)
    override val hash: String
) : WithData
