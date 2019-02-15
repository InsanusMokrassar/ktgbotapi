package com.github.insanusmokrassar.TelegramBotAPI.types

import com.soywiz.klock.DateTime
import kotlinx.serialization.*

@Serializable
data class ResponseParametersRaw(
    @SerialName("migrate_to_chat_id")
    @Optional
    private val migrateToChatId: ChatId? = null,
    @SerialName("retry_after")
    @Optional
    private val retryAfter: Long? = null
) {
    @Transient
    private val createTime: Long = DateTime.now().milliseconds.toLong()
    @Transient
    val error: RequestError? by lazy {
        when {
            migrateToChatId != null -> MigrateChatId(migrateToChatId);
            retryAfter != null -> RetryAfterError(retryAfter, createTime);
            else -> null
        }
    }
}
