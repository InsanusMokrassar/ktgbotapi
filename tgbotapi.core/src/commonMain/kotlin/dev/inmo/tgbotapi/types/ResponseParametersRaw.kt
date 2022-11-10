package dev.inmo.tgbotapi.types

import com.soywiz.klock.DateTime
import kotlinx.serialization.*

@Serializable
data class ResponseParametersRaw(
    @SerialName("migrate_to_chat_id")
    private val migrateToChatId: IdChatIdentifier? = null,
    @SerialName("retry_after")
    private val retryAfter: Seconds? = null
) {
    @Transient
    private val createTime: Long = DateTime.now().unixMillisLong
    val error: RequestError? by lazy {
        when {
            migrateToChatId != null -> MigrateChatId(migrateToChatId);
            retryAfter != null -> RetryAfterError(retryAfter, createTime);
            else -> null
        }
    }
}
