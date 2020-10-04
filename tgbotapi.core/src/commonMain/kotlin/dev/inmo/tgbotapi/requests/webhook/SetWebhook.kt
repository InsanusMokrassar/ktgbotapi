package dev.inmo.tgbotapi.requests.webhook

import dev.inmo.tgbotapi.requests.abstracts.*
import dev.inmo.tgbotapi.requests.send.media.base.DataRequest
import dev.inmo.tgbotapi.requests.send.media.base.MultipartRequestImpl
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

private fun correctWebhookUrl(sourceUrl: String) = if (sourceUrl.contains("://")) {
    sourceUrl
} else {
    "https://$sourceUrl"
}

fun SetWebhook(
    url: String,
    certificate: MultipartFile,
    maxAllowedConnections: Int? = null,
    allowedUpdates: List<String>? = null
): MultipartRequestImpl<SetWebhook, Map<String, MultipartFile>, Boolean> = MultipartRequestImpl(
    SetWebhook(
        correctWebhookUrl(url),
        null,
        maxAllowedConnections,
        allowedUpdates
    ),
    mapOf(certificateField to certificate)
)

fun SetWebhook(
    url: String,
    certificate: FileId,
    maxAllowedConnections: Int? = null,
    allowedUpdates: List<String>? = null
): SetWebhook = SetWebhook(
    correctWebhookUrl(url),
    certificate.fileId,
    maxAllowedConnections,
    allowedUpdates
)

@Suppress("USELESS_CAST")
fun SetWebhook(
    url: String,
    certificate: InputFile,
    maxAllowedConnections: Int? = null,
    allowedUpdates: List<String>? = null
): Request<Boolean> = when (certificate) {
    is MultipartFile -> SetWebhook(correctWebhookUrl(url), certificate as MultipartFile, maxAllowedConnections, allowedUpdates)
    is FileId -> SetWebhook(correctWebhookUrl(url), certificate as FileId, maxAllowedConnections, allowedUpdates)
}

fun SetWebhook(
    url: String,
    maxAllowedConnections: Int? = null,
    allowedUpdates: List<String>? = null
) = SetWebhook(
    correctWebhookUrl(url),
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
        get() = Boolean.serializer()
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
