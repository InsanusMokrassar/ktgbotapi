@file:Suppress("unused")
@file:OptIn(ExperimentalSerializationApi::class)

package dev.inmo.tgbotapi.types.buttons

import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.*

@Serializable
data class ReplyForce(
    val selective: Boolean? = null,
    @SerialName(inputFieldPlaceholderField)
    val inputFieldPlaceholder: String? = null
) : KeyboardMarkup {
    @SerialName(forceReplyField)
    @Required
    @EncodeDefault
    val forceReply: Boolean = true

    companion object {
        fun Selective(inputFieldPlaceholder: String? = null) = ReplyForce(true, inputFieldPlaceholder)
        fun NonSelective(inputFieldPlaceholder: String? = null) = ReplyForce(false, inputFieldPlaceholder)
        val Selective = Selective()
        val NonSelective = NonSelective()
        val Default = ReplyForce()
    }

    init {
        if (inputFieldPlaceholder != null && inputFieldPlaceholder.length !in inputFieldPlaceholderLimit) {
            error("Field $inputFieldPlaceholderField length must be in $inputFieldPlaceholderLimit, but was ${inputFieldPlaceholder.length}")
        }
    }
}
