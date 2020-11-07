package dev.inmo.tgbotapi.types.location

import dev.inmo.tgbotapi.CommonAbstracts.*
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.utils.nonstrictJsonFormat
import kotlinx.serialization.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject

@Serializable(LocationSerializer::class)
sealed class Location : Locationed, HorizontallyAccured

@Serializable
data class StaticLocation(
    @SerialName(longitudeField)
    override val longitude: Double,
    @SerialName(latitudeField)
    override val latitude: Double,
    @SerialName(horizontalAccuracyField)
    override val horizontalAccuracy: Meters? = null
) : Location()

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
) : Location(), Livable, ProximityAlertable, Headed

@Serializer(Location::class)
object LocationSerializer : KSerializer<Location> {
    override fun deserialize(decoder: Decoder): Location = JsonObject.serializer().deserialize(decoder).let {
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
