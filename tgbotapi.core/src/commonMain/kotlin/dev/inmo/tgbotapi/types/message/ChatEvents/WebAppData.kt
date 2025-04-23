package dev.inmo.tgbotapi.types.message.ChatEvents

import dev.inmo.tgbotapi.types.buttonTextField
import dev.inmo.tgbotapi.types.dataField
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.PrivateEvent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WebAppData(
    @SerialName(dataField)
    val data: String,
    @SerialName(buttonTextField)
    val buttonText: String,
) : PrivateEvent
