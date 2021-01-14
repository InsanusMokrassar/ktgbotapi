package dev.inmo.tgbotapi.types.passport.encrypted_data

import dev.inmo.tgbotapi.types.dataField
import dev.inmo.tgbotapi.types.hashField
import dev.inmo.tgbotapi.types.passport.Base64EncodedData
import dev.inmo.tgbotapi.types.passport.EncryptedAndBase64EncodedData
import dev.inmo.tgbotapi.types.passport.encrypted_data.abstracts.WithData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(EncryptedElementSerializer::class)
data class EncryptedPersonalDetails(
    @SerialName(dataField)
    override val data: EncryptedAndBase64EncodedData,
    @SerialName(hashField)
    override val hash: Base64EncodedData
) : WithData
