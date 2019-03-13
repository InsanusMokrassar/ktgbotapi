package com.github.insanusmokrassar.TelegramBotAPI.types

import kotlinx.serialization.*

@Serializable
data class WebhookInfo(
    @SerialName(urlField)
    val url: String,
    @SerialName(pendingUpdateCountField)
    val awaitDeliery: Int,
    @SerialName(maxAllowedConnectionsField)
    @Optional
    val maxConnections: Int = 40, // default count according to documentation
    @SerialName(hasCustomCertificateField)
    @Optional
    val customCertificate: Boolean = false,
    @SerialName(allowedUpdatesField)
    @Optional
    val allowedUpdates: List<String> = ALL_UPDATES_LIST,
    @SerialName(lastErrorDateField)
    @Optional
    val lastErrorDate: TelegramDate? = null,
    @SerialName(lastErrorMessageField)
    @Optional
    val lastErrorMessage: String? = null
) {
    @Transient
    val isNotUseWebhook: Boolean = url.isEmpty()

    @Transient
    val hasError: Boolean = lastErrorMessage != null
}
