package dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons

import dev.inmo.kslog.common.w
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.utils.DefaultKTgBotAPIKSLog
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class SwitchInlineQueryChosenChat (
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
        if (allowUsers == false && allowBots == false && allowGroups == false && allowChannels == false) {
            DefaultKTgBotAPIKSLog.w("SwitchInlineQueryChosenChat", "Bot must allow to choose at least one of available variants in choosing of inline query recipient")
        }
    }
}
