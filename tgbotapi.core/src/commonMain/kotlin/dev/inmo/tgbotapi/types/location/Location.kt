package dev.inmo.tgbotapi.types.location

import dev.inmo.tgbotapi.abstracts.*
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.utils.nonstrictJsonFormat
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject

/**
 * Common interface for any known telegram location. Use [LocationSerializer] in case you wish
 * to serialize/deserialize [Location]
 *
 * @see dev.inmo.tgbotapi.extensions.utils.asStaticLocation
 * @see dev.inmo.tgbotapi.extensions.utils.asLiveLocation
 */
@Serializable(LocationSerializer::class)
sealed interface Location : Locationed, HorizontallyAccured

@Serializable
data class StaticLocation(
    @SerialName(longitudeField)
    override val longitude: Double,
    @SerialName(latitudeField)
    override val latitude: Double,
    @SerialName(horizontalAccuracyField)
    override val horizontalAccuracy: Meters? = null
) : Location

@Serializable
data class LiveLocation(
    @SerialName(longitudeField)
    override val longitude: Double,
    @SerialName(latitudeField)
    override val latitude: Double,
    @SerialName(horizontalAccuracyField)
    override val horizontalAccuracy: Meters? = null,
    @SerialName(livePeriodField)
    override val livePeriod: Seconds,
    @SerialName(headingField)
    override val heading: Degrees? = null,
    @SerialName(proximityAlertRadiusField)
    override val proximityAlertRadius: Meters? = null
) : Location, Livable, ProximityAlertable, Headed

object LocationSerializer : KSerializer<Location> {
    private val internalSerializer = JsonObject.serializer()
    override val descriptor: SerialDescriptor = internalSerializer.descriptor
    override fun deserialize(decoder: Decoder): Location = internalSerializer.deserialize(decoder).let {
        if (it.containsKey(livePeriodField) && it[livePeriodField] != JsonNull) {
            nonstrictJsonFormat.decodeFromJsonElement(LiveLocation.serializer(), it)
        } else {
            nonstrictJsonFormat.decodeFromJsonElement(StaticLocation.serializer(), it)
        }
    }

    override fun serialize(encoder: Encoder, value: Location) {
        when (value) {
            is StaticLocation -> StaticLocation.serializer().serialize(encoder, value)
            is LiveLocation -> LiveLocation.serializer().serialize(encoder, value)
        }
    }
}
