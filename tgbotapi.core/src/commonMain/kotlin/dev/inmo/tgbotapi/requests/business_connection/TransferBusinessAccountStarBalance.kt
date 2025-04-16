package dev.inmo.tgbotapi.requests.business_connection

import dev.inmo.tgbotapi.requests.abstracts.BusinessRequest
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.Username
import dev.inmo.tgbotapi.types.bioField
import dev.inmo.tgbotapi.types.businessConnectionIdField
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.chatIdField
import dev.inmo.tgbotapi.types.firstNameField
import dev.inmo.tgbotapi.types.lastNameField
import dev.inmo.tgbotapi.types.message.RawMessage
import dev.inmo.tgbotapi.types.messageIdField
import dev.inmo.tgbotapi.types.messageIdsField
import dev.inmo.tgbotapi.types.payments.stars.StarAmount
import dev.inmo.tgbotapi.types.starCountField
import dev.inmo.tgbotapi.types.usernameField
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.builtins.serializer

@Serializable
data class TransferBusinessAccountStarBalance(
    @SerialName(businessConnectionIdField)
    override val businessConnectionId: BusinessConnectionId,
    @SerialName(starCountField)
    val starCount: Int
) : BusinessRequest.Simple<Boolean> {
    override fun method(): String = "transferBusinessAccountStars"

    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}