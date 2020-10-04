package dev.inmo.tgbotapi.extensions.utils.extensions.venue

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.venue.Venue
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

val Venue.foursquare: Foursquare?
    get() = foursquareId ?.let {
        Foursquare(it, foursquareType)
    }

fun Venue(
    location: Location,
    title: String,
    address: String,
    foursquare: Foursquare
) = Venue(location, title, address, foursquare.id, foursquare.type)

@Serializable
data class Foursquare(
    @SerialName(foursquareIdField)
    val id: FoursquareId,
    @SerialName(foursquareTypeField)
    val type: FoursquareType? = null
)
