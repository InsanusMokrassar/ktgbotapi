package dev.inmo.tgbotapi.types

import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface LinkPreviewOptions {
    val isDisabled: Boolean
    val url: String?
    val preferSmallMedia: Boolean
    val preferLargeMedia: Boolean
    val showAboveText: Boolean

    @Serializable
    data object Disabled : LinkPreviewOptions {
        @Required
        @SerialName(isDisabledField)
        override val isDisabled: Boolean = true
        override val url: String?
            get() = null
        override val preferSmallMedia: Boolean
            get() = false
        override val preferLargeMedia: Boolean
            get() = false
        override val showAboveText: Boolean
            get() = false
    }

    @Serializable
    data class Large(
        @SerialName(urlField)
        override val url: String?,
        @SerialName(showAboveTextField)
        override val showAboveText: Boolean
    ) : LinkPreviewOptions {
        @Required
        @SerialName(isDisabledField)
        override val isDisabled: Boolean = false
        @Required
        @SerialName(preferLargeMediaField)
        override val preferLargeMedia: Boolean = true
        override val preferSmallMedia: Boolean
            get() = false
    }

    @Serializable
    data class Small(
        @SerialName(urlField)
        override val url: String?,
        @SerialName(showAboveTextField)
        override val showAboveText: Boolean
    ) : LinkPreviewOptions {
        @Required
        @SerialName(isDisabledField)
        override val isDisabled: Boolean = false
        @Required
        @SerialName(preferSmallMediaField)
        override val preferSmallMedia: Boolean = true
        override val preferLargeMedia: Boolean
            get() = false
    }

    @Serializable
    data class Medium(
        @SerialName(urlField)
        override val url: String?,
        @SerialName(showAboveTextField)
        override val showAboveText: Boolean
    ) : LinkPreviewOptions {
        @Required
        @SerialName(isDisabledField)
        override val isDisabled: Boolean = false
        override val preferSmallMedia: Boolean
            get() = false
        override val preferLargeMedia: Boolean
            get() = false
    }
}