package dev.inmo.tgbotapi.requests.verifications

import dev.inmo.tgbotapi.abstracts.types.ChatRequest
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.RawMessageEntities
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.utils.extensions.makeString
import dev.inmo.tgbotapi.utils.serializers.UnitFromBooleanSerializer
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy

/** Verifies the chat with an optional description. Supply a parse mode or explicit text-link entities. */
@Serializable
data class VerifyChat(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(customDescriptionField)
    val description: String? = null,
    @SerialName(customDescriptionParseModeField)
    val customDescriptionParseMode: ParseMode? = null,
    @SerialName(customDescriptionEntitiesField)
    val customDescriptionEntities: RawMessageEntities? = null
): ChatRequest, SimpleRequest<Unit> {
    init {
        requireVerificationDescriptionFormat(customDescriptionParseMode, customDescriptionEntities)
    }

    constructor(chatId: ChatIdentifier, description: TextSourcesList) :
        this(chatId, description.makeString(), customDescriptionEntities = description.toRawMessageEntities())

    override fun method(): String = "verifyChat"
    override val resultDeserializer: DeserializationStrategy<Unit>
        get() = UnitFromBooleanSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
