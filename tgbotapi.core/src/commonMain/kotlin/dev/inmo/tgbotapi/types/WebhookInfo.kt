package dev.inmo.tgbotapi.types

import kotlinx.serialization.*

@Serializable
data class WebhookInfo(
    @SerialName(urlField)
    val url: String,
    @SerialName(pendingUpdateCountField)
    val awaitDelivery: Int,
    @SerialName(maxAllowedConnectionsField)
    val maxConnections: Int = 40, // default count according to documentation
    @SerialName(hasCustomCertificateField)
    val customCertificate: Boolean = false,
    @SerialName(allowedUpdatesField)
    val allowedUpdates: List<String> = ALL_UPDATES_LIST,
    @SerialName(lastErrorDateField)
    val lastErrorDate: TelegramDate? = null,
    @SerialName(lastErrorMessageField)
    val lastErrorMessage: String? = null
) {
    @Transient
    val isNotUseWebhook: Boolean = url.isEmpty()

    @Transient
    val hasError: Boolean = lastErrorMessage != null
}
