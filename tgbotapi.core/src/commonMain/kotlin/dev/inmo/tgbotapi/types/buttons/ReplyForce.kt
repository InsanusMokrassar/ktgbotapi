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
        fun Selective(inputFieldPlaceholder: String? = null) = ReplyForce(true, inputFieldPlaceholder)
        fun NonSelective(inputFieldPlaceholder: String? = null) = ReplyForce(false, inputFieldPlaceholder)
        val Selective = Selective()
        val NonSelective = NonSelective()
        val Default = ReplyForce()

        @Deprecated("Renamed", ReplaceWith("ReplyForce.Selective"))
        inline val ReplyForceSelective
            get() = Selective
        @Deprecated("Renamed", ReplaceWith("ReplyForce.NonSelective"))
        inline val ReplyForceNonSelective
            get() = NonSelective
        @Deprecated("Renamed", ReplaceWith("ReplyForce.Default"))
        val ReplyForceDefault = ReplyForce()
    }

    init {
        if (inputFieldPlaceholder != null && inputFieldPlaceholder.length !in inputFieldPlaceholderLimit) {
            error("Field $inputFieldPlaceholderField length must be in $inputFieldPlaceholderLimit, but was ${inputFieldPlaceholder.length}")
        }
    }
}
