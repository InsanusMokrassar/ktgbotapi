package dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons

import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class SwitchInlineQueryChosenChat(
    @SerialName(queryField)
    val query: String? = null,
    @SerialName(allowUserChatsField)
    @EncodeDefault
    val allowUsers: Boolean = false,
    @SerialName(allowBotChatsField)
    @EncodeDefault
    val allowBots: Boolean = false,
    @SerialName(allowGroupChatsField)
    @EncodeDefault
    val allowGroups: Boolean = false,
    @SerialName(allowChannelChatsField)
    @EncodeDefault
    val allowChannels: Boolean = false,
) {
    init {
        require(allowUsers || allowBots || allowGroups || allowChannels) {
            "Bot must allow to choose at least one of available variants in choosing of inline query recipient"
        }
    }
}
