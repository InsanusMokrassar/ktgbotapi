package dev.inmo.tgbotapi.types.buttons

import dev.inmo.tgbotapi.types.inputFieldPlaceholderField
import dev.inmo.tgbotapi.types.inputFieldPlaceholderLimit
import kotlinx.serialization.*

@Serializable
data class ReplyForce(
    val selective: Boolean? = null,
    @SerialName(inputFieldPlaceholderField)
    val inputFieldPlaceholder: String? = null
) : KeyboardMarkup {
    @SerialName("force_reply")
    @Required
    val forceReply: Boolean = true

    companion object {
        val ReplyForceSelective = ReplyForce(true)
        val ReplyForceNonSelective = ReplyForce(false)
        val ReplyForceDefault = ReplyForce()
    }

    init {
        if (inputFieldPlaceholder != null && inputFieldPlaceholder.length !in inputFieldPlaceholderLimit) {
            error("Field $inputFieldPlaceholderField length must be in $inputFieldPlaceholderLimit, but was ${inputFieldPlaceholder.length}")
        }
    }
}
