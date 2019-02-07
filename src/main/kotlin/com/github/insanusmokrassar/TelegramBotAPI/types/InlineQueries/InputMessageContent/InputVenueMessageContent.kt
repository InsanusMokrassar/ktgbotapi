package com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InputMessageContent

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.abstracts.InputMessageContent
import kotlinx.serialization.*

@Serializable
data class InputVenueMessageContent(
    @SerialName(latitudeField)
    override val latitude: Double,
    @SerialName(longitudeField)
    override val longitude: Double,
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
) : Locationed, CommonVenueData, InputMessageContent
