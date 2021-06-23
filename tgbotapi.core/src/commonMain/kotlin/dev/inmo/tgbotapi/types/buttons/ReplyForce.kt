package dev.inmo.tgbotapi.types.buttons

import kotlinx.serialization.*

@Serializable
data class ReplyForce(
    val selective: Boolean? = null
) : KeyboardMarkup {
    @SerialName("force_reply")
    @Required
    val forceReply: Boolean = true

    companion object {
        val ReplyForceSelective = ReplyForce(true)
        val ReplyForceNonSelective = ReplyForce(false)
        val ReplyForceDefault = ReplyForce()
    }
}

@Deprecated("Renamed", ReplaceWith("ReplyForce", "dev.inmo.tgbotapi.types.buttons.ReplyForce"))
typealias ForceReply = ReplyForce
