package dev.inmo.tgbotapi.types.passport.decrypted

import dev.inmo.tgbotapi.types.dataField
import dev.inmo.tgbotapi.types.passport.credentials.DataCredentials
import dev.inmo.tgbotapi.types.passport.decrypted.abstracts.SecureValueWithData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddressSecureValue(
    @SerialName(dataField)
    override val data: DataCredentials
) : SecureValueWithData