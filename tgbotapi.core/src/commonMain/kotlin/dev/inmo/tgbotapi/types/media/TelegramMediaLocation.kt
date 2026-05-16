package dev.inmo.tgbotapi.types.media

import dev.inmo.tgbotapi.abstracts.HorizontallyAccured
import dev.inmo.tgbotapi.abstracts.Locationed
import dev.inmo.tgbotapi.types.Meters
import dev.inmo.tgbotapi.types.horizontalAccuracyField
import dev.inmo.tgbotapi.types.latitudeField
import dev.inmo.tgbotapi.types.longitudeField
import dev.inmo.tgbotapi.types.typeField
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TelegramMediaLocation(
    @SerialName(latitudeField)
    override val latitude: Double,
    @SerialName(longitudeField)
    override val longitude: Double,
    @SerialName(horizontalAccuracyField)
    override val horizontalAccuracy: Meters? = null,
) : Locationed, HorizontallyAccured, InputPollMedia, InputPollOptionMedia {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    companion object {
        const val TYPE = "location"
    }
}
