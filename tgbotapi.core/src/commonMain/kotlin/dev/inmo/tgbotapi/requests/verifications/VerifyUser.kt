package dev.inmo.tgbotapi.requests.verifications

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

/** Verifies the user with an optional description. Supply a parse mode or explicit text-link entities. */
@Serializable
data class VerifyUser(
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @SerialName(userIdField)
    val userId: UserId,
    @SerialName(customDescriptionField)
    val description: String? = null,
    @SerialName(customDescriptionParseModeField)
    val customDescriptionParseMode: ParseMode? = null,
    @SerialName(customDescriptionEntitiesField)
    val customDescriptionEntities: RawMessageEntities? = null
): SimpleRequest<Unit> {
    init {
        requireVerificationDescriptionFormat(customDescriptionParseMode, customDescriptionEntities)
    }

    constructor(userId: UserId, description: TextSourcesList) :
        this(userId, description.makeString(), customDescriptionEntities = description.toRawMessageEntities())

    override fun method(): String = "verifyUser"
    override val resultDeserializer: DeserializationStrategy<Unit>
        get() = UnitFromBooleanSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}

internal fun requireVerificationDescriptionFormat(parseMode: ParseMode?, entities: RawMessageEntities?) {
    require(parseMode == null || entities == null) {
        "Specify either custom_description_parse_mode or custom_description_entities."
    }
    require(entities == null || entities.all { it.type == "text_link" && it.url != null }) {
        "Verification descriptions support only text_link entities with a URL."
    }
}
