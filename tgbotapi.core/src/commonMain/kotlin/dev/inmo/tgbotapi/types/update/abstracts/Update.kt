package dev.inmo.tgbotapi.types.update.abstracts

import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import dev.inmo.tgbotapi.types.UpdateId
import dev.inmo.tgbotapi.types.update.RawUpdate
import dev.inmo.tgbotapi.types.updateIdField
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.decodeDataAndJson
import dev.inmo.tgbotapi.utils.nonstrictJsonFormat
import dev.inmo.tgbotapi.utils.serializers.CallbackCustomizableDeserializationStrategy
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.longOrNull

@ClassCastsIncluded
interface Update {
    val updateId: UpdateId
    val data: Any
}

data class UnknownUpdate(
    override val updateId: UpdateId,
    override val data: JsonElement,
    val throwable: Throwable? = null
) : Update {
    val rawJson: JsonElement
        get() = data
}

@RiskFeature
object UpdateSerializerWithoutSerialization : KSerializer<Update> {
    override val descriptor: SerialDescriptor = JsonElement.serializer().descriptor

    override fun deserialize(decoder: Decoder): Update = UpdateDeserializationStrategy.deserialize(decoder)

    override fun serialize(encoder: Encoder, value: Update) = throw UnsupportedOperationException()
}

/**
 * Use this object to deserialize objects with type [Update]. Currently it is restricted to use this
 * [DeserializationStrategy] only with JSON
 *
 * @see StringFormat.parse
 * @see kotlinx.serialization.json.Json.parse
 */
object UpdateDeserializationStrategy : CallbackCustomizableDeserializationStrategy<Update>(
    JsonElement.serializer().descriptor,
    { _, jsonElement ->
        nonstrictJsonFormat.decodeFromJsonElement(
            RawUpdate.serializer(),
            jsonElement!!
        ).asUpdate(
            jsonElement
        )
    },
    { it, _, jsonElement ->
        UnknownUpdate(
            UpdateId((jsonElement as? JsonObject) ?.get(updateIdField) ?.jsonPrimitive ?.longOrNull ?: -1L),
            jsonElement!!,
            it
        )
    }
)
