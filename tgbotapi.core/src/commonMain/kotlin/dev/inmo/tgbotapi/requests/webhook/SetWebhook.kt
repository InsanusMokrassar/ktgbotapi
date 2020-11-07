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
    ipAddress: String? = null,
    maxAllowedConnections: Int? = null,
    allowedUpdates: List<String>? = null,
    dropPendingUpdates: Boolean? = null
): MultipartRequestImpl<SetWebhook, Map<String, MultipartFile>, Boolean> = MultipartRequestImpl(
    SetWebhook(
        correctWebhookUrl(url),
        null as String?,
        ipAddress,
        maxAllowedConnections,
        allowedUpdates,
        dropPendingUpdates
    ),
    mapOf(certificateField to certificate)
)

fun SetWebhook(
    url: String,
    certificate: FileId,
    ipAddress: String? = null,
    maxAllowedConnections: Int? = null,
    allowedUpdates: List<String>? = null,
    dropPendingUpdates: Boolean? = null
): SetWebhook = SetWebhook(
    correctWebhookUrl(url),
    certificate.fileId,
    ipAddress,
    maxAllowedConnections,
    allowedUpdates,
    dropPendingUpdates
)

/**
 * Use this method to specify a url and receive incoming updates via an outgoing webhook. Whenever there is an update
 * for the bot, we will send an HTTPS POST request to the specified url, containing a JSON-serialized Update.
 *
 * If you'd like to make sure that the Webhook request comes from Telegram, we recommend using a secret path in the [url],
 * e.g. https://www.example.com/<token>. Since nobody else knows your bot's token, you can be pretty sure it's us.
 */
@Suppress("USELESS_CAST")
fun SetWebhook(
    url: String,
    certificate: InputFile,
    ipAddress: String? = null,
    maxAllowedConnections: Int? = null,
    allowedUpdates: List<String>? = null,
    dropPendingUpdates: Boolean? = null
): Request<Boolean> = when (certificate) {
    is MultipartFile -> SetWebhook(correctWebhookUrl(url), certificate as MultipartFile, ipAddress, maxAllowedConnections, allowedUpdates, dropPendingUpdates)
    is FileId -> SetWebhook(correctWebhookUrl(url), certificate as FileId, ipAddress, maxAllowedConnections, allowedUpdates, dropPendingUpdates)
}

/**
 * Use this method to specify a url and receive incoming updates via an outgoing webhook. Whenever there is an update
 * for the bot, we will send an HTTPS POST request to the specified url, containing a JSON-serialized Update.
 *
 * If you'd like to make sure that the Webhook request comes from Telegram, we recommend using a secret path in the [url],
 * e.g. https://www.example.com/<token>. Since nobody else knows your bot's token, you can be pretty sure it's us.
 */
@Suppress("USELESS_CAST")
fun SetWebhook(
    url: String,
    ipAddress: String? = null,
    maxAllowedConnections: Int? = null,
    allowedUpdates: List<String>? = null,
    dropPendingUpdates: Boolean? = null
): Request<Boolean> = SetWebhook(
    correctWebhookUrl(url),
    null,
    ipAddress,
    maxAllowedConnections,
    allowedUpdates,
    dropPendingUpdates
)

/**
 * Use this method to specify a url and receive incoming updates via an outgoing webhook. Whenever there is an update
 * for the bot, we will send an HTTPS POST request to the specified url, containing a JSON-serialized Update.
 *
 * If you'd like to make sure that the Webhook request comes from Telegram, we recommend using a secret path in the [url],
 * e.g. https://www.example.com/<token>. Since nobody else knows your bot's token, you can be pretty sure it's us.
 */
@Serializable
data class SetWebhook internal constructor(
    @SerialName(urlField)
    val url: String,
    @SerialName(certificateField)
    val certificateFile: String? = null,
    @SerialName(ipAddressField)
    val ipAddress: String? = null,
    @SerialName(maxAllowedConnectionsField)
    val maxAllowedConnections: Int? = null,
    @SerialName(allowedUpdatesField)
    val allowedUpdates: List<String>? = null,
    @SerialName(dropPendingUpdatesField)
    val dropPendingUpdates: Boolean? = null
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
