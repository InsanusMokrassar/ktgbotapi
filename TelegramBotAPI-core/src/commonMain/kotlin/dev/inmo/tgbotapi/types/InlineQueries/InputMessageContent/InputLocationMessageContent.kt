package dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent

import dev.inmo.tgbotapi.CommonAbstracts.Livable
import dev.inmo.tgbotapi.CommonAbstracts.Locationed
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.InlineQueries.abstracts.InputMessageContent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InputLocationMessageContent(
    @SerialName(latitudeField)
    override val latitude: Double,
    @SerialName(longitudeField)
    override val longitude: Double,
    @SerialName(livePeriodField)
    override val livePeriod: Int? = null
) : Locationed, Livable, InputMessageContent