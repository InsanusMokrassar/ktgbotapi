package dev.inmo.tgbotapi.requests.business_connection

import dev.inmo.tgbotapi.requests.abstracts.BusinessRequest
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.Username
import dev.inmo.tgbotapi.types.acceptedGiftTypesField
import dev.inmo.tgbotapi.types.businessConnectionIdField
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.chatIdField
import dev.inmo.tgbotapi.types.firstNameField
import dev.inmo.tgbotapi.types.gifts.AcceptedGiftTypes
import dev.inmo.tgbotapi.types.lastNameField
import dev.inmo.tgbotapi.types.message.RawMessage
import dev.inmo.tgbotapi.types.messageIdField
import dev.inmo.tgbotapi.types.messageIdsField
import dev.inmo.tgbotapi.types.showGiftButtonField
import dev.inmo.tgbotapi.types.usernameField
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.builtins.serializer

@Serializable
data class SetBusinessAccountGiftSettings(
    @SerialName(businessConnectionIdField)
    override val businessConnectionId: BusinessConnectionId,
    @SerialName(showGiftButtonField)
    val showGiftButton: Boolean,
    @SerialName(acceptedGiftTypesField)
    val acceptedGiftTypes: AcceptedGiftTypes
) : BusinessRequest.Simple<Boolean> {
    override fun method(): String = "setBusinessAccountGiftSettings"

    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}