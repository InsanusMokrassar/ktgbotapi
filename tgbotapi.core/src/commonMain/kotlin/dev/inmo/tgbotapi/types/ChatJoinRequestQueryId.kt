package dev.inmo.tgbotapi.types

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Identifier of a join request query.
 *
 * @see <a href="https://core.telegram.org/bots/api#chatjoinrequest">ChatJoinRequest.query_id</a>
 */
@Serializable
@JvmInline
value class ChatJoinRequestQueryId(
    val string: String
) {
    override fun toString(): String {
        return string
    }
}
