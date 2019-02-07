package com.github.insanusmokrassar.TelegramBotAPI.types

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.CommonVenueData
import kotlinx.serialization.*

@Serializable
data class Venue(
    @SerialName(locationField)
    val location: Location,
    @SerialName(titleField)
    override val title: String,
    @SerialName(addressField)
    override val address: String,
    @SerialName(foursquareIdField)
    @Optional
    override val foursquareId: String? = null,
    @SerialName(foursquareTypeField)
    @Optional
    override val foursquareType: String? = null
) : CommonVenueData
