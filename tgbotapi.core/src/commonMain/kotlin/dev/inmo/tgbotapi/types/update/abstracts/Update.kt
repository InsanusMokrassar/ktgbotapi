package dev.inmo.tgbotapi.types.update.abstracts

import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import dev.inmo.tgbotapi.types.UpdateId
import dev.inmo.tgbotapi.types.update.RawUpdate
import dev.inmo.tgbotapi.types.updateIdField
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.decodeDataAndJson
import dev.inmo.tgbotapi.utils.nonstrictJsonFormat
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
object UpdateDeserializationStrategy : DeserializationStrategy<Update> {
    private val _customDeserializationStrategies = LinkedHashSet<JsonDeserializerStrategy>()

    /**
     * Contains [JsonDeserializerStrategy] which will be used in [deserialize] method when standard
     * [RawUpdate] serializer will be unable to create [RawUpdate] (and [Update] as well)
     */
    val customDeserializationStrategies: Set<JsonDeserializerStrategy>
        get() = _customDeserializationStrategies.toSet()
    fun interface JsonDeserializerStrategy {
        fun deserializeOrNull(json: JsonElement): Update?
    }

    override val descriptor: SerialDescriptor = JsonElement.serializer().descriptor

    override fun deserialize(decoder: Decoder): Update {
        val asJson = JsonElement.serializer().deserialize(decoder)
        return runCatching {
            nonstrictJsonFormat.decodeFromJsonElement(
                RawUpdate.serializer(),
                asJson
            ).asUpdate(
                asJson
            )
        }.getOrElse {
            customDeserializationStrategies.firstNotNullOfOrNull {
                it.deserializeOrNull(asJson)
            } ?: UnknownUpdate(
                UpdateId((asJson as? JsonObject) ?.get(updateIdField) ?.jsonPrimitive ?.longOrNull ?: -1L),
                asJson,
                it
            )
        }
    }

    /**
     * Adding [deserializationStrategy] into [customDeserializationStrategies] for using in case of unknown update
     */
    fun addUpdateDeserializationStrategy(
        deserializationStrategy: JsonDeserializerStrategy
    ) = _customDeserializationStrategies.add(deserializationStrategy)

    /**
     * Removing [deserializationStrategy] from [customDeserializationStrategies]
     */
    fun removeUpdateDeserializationStrategy(
        deserializationStrategy: JsonDeserializerStrategy
    ) = _customDeserializationStrategies.remove(deserializationStrategy)
}
