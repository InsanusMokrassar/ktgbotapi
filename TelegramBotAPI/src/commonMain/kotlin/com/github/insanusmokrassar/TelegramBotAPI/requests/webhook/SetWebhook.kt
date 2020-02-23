package com.github.insanusmokrassar.TelegramBotAPI.requests.webhook

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.media.base.DataRequest
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.media.base.MultipartRequestImpl
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import kotlinx.serialization.*
import kotlinx.serialization.internal.BooleanSerializer

fun SetWebhook(
    url: String,
    certificate: InputFile,
    maxAllowedConnections: Int? = null,
    allowedUpdates: List<String>? = null
) : Request<Boolean> {
    val data = SetWebhook(
        url,
        (certificate as? FileId) ?.fileId,
        maxAllowedConnections,
        allowedUpdates
    )
    return when (certificate) {
        is FileId -> data
        is MultipartFile -> MultipartRequestImpl(
            data,
            mapOf(certificateField to certificate)
        )
    }
}

fun SetWebhook(
    url: String,
    maxAllowedConnections: Int? = null,
    allowedUpdates: List<String>? = null
) : Request<Boolean> = SetWebhook(
    url,
    null,
    maxAllowedConnections,
    allowedUpdates
)

@Serializable
data class SetWebhook internal constructor(
    @SerialName(urlField)
    val url: String,
    @SerialName(certificateField)
    val certificateFile: String? = null,
    @SerialName(maxAllowedConnectionsField)
    val maxAllowedConnections: Int? = null,
    @SerialName(allowedUpdatesField)
    val allowedUpdates: List<String>? = null
) : DataRequest<Boolean> {
    override fun method(): String = "setWebhook"
    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = BooleanSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    init {
        maxAllowedConnections ?.let {
            if (it !in allowedConnectionsLength) {
                throw IllegalArgumentException("Allowed connection for webhook must be in $allowedConnectionsLength range (but passed $it)")
            }
        }
    }
}
