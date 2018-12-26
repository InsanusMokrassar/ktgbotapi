package com.github.insanusmokrassar.TelegramBotAPI.types

import kotlinx.serialization.*
import org.joda.time.DateTime
import java.util.concurrent.TimeUnit

@Serializable
data class ResponseParameters<T : Any>(
    val ok: Boolean = false,
    @Optional
    val description: String? = null,
    @SerialName("migrate_to_chat_id")
    @Optional
    val migrateToChatId: Identifier? = null,
    @SerialName("retry_after")
    @Optional
    val retryAfter: Int? = null,
    @SerialName("error_code")
    @Optional
    val errorCode: Int? = null,
    @Optional
    val result: T? = null
) {
    @Transient
    val waitUntil: DateTime? by lazy {
        retryAfter ?.let {
            DateTime.now().plus(TimeUnit.SECONDS.toMillis(it.toLong()))
        }
    }
}
