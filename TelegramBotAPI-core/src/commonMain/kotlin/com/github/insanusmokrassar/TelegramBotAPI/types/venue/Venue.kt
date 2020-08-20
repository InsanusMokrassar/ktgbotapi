package com.github.insanusmokrassar.TelegramBotAPI.types.venue

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.CommonVenueData
import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.Locationed
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Venue(
    @SerialName(locationField)
    val location: Location,
    @SerialName(titleField)
    override val title: String,
    @SerialName(addressField)
    override val address: String,
    @SerialName(foursquareIdField)
    override val foursquareId: FoursquareId? = null,
    @SerialName(foursquareTypeField)
    override val foursquareType: FoursquareType? = null
) : CommonVenueData, Locationed by location
