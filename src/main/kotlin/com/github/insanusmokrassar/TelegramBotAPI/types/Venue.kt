package com.github.insanusmokrassar.TelegramBotAPI.types

import kotlinx.serialization.*

@Serializable
data class Venue(
    @SerialName(locationField)
    val location: Location,
    @SerialName(titleField)
    val title: String,
    @SerialName(addressField)
    val address: String,
    @SerialName(foursquareIdField)
    @Optional
    val foursquareId: String? = null,
    @SerialName(foursquareTypeField)
    @Optional
    val foursquareType: String? = null // TODO:: Rewrite with enum or interface
)
