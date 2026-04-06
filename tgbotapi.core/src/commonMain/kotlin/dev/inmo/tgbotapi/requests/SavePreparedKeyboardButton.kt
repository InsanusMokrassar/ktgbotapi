package dev.inmo.tgbotapi.requests

import dev.inmo.kslog.common.w
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.allowedToSavePreparedKeyboardButtons
import dev.inmo.tgbotapi.types.buttonField
import dev.inmo.tgbotapi.types.buttons.KeyboardButton
import dev.inmo.tgbotapi.types.buttons.PreparedKeyboardButton
import dev.inmo.tgbotapi.types.userIdField
import dev.inmo.tgbotapi.utils.DefaultKTgBotAPIKSLog
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy

@Serializable
data class SavePreparedKeyboardButton(
    @SerialName(userIdField)
    val userId: ChatId,
    @SerialName(buttonField)
    val button: KeyboardButton
) : SimpleRequest<PreparedKeyboardButton> {
    override val resultDeserializer: DeserializationStrategy<PreparedKeyboardButton>
        get() = PreparedKeyboardButton.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    override fun method(): String = "savePreparedKeyboardButton"

    init {
        if (button::class !in allowedToSavePreparedKeyboardButtons) {
            DefaultKTgBotAPIKSLog.w {
                "According to https://core.telegram.org/bots/api#savepreparedkeyboardbutton it is disallowed to use " +
                        "anything except request_users, request_chat, or request_managed_bot, but you passed $button " +
                        "which is not instance of any allowed buttons type"
            }
        }
    }
}
