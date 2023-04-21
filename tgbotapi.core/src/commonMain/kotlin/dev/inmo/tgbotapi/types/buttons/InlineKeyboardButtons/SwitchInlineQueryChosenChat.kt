package dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons

import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SwitchInlineQueryChosenChat(
    @SerialName(queryField)
    val query: String? = null,
    @SerialName(allowUserChatsField)
    val allowUsers: Boolean? = null,
    @SerialName(allowBotChatsField)
    val allowBots: Boolean? = null,
    @SerialName(allowGroupChatsField)
    val allowGroups: Boolean? = null,
    @SerialName(allowChannelChatsField)
    val allowChannels: Boolean? = null,
)
