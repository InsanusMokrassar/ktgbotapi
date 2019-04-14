package com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.InputMessageContent

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.Livable
import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.Locationed
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.abstracts.InputMessageContent
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