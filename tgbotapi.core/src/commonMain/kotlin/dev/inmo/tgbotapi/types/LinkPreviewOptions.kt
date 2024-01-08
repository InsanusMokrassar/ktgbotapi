package dev.inmo.tgbotapi.types

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(LinkPreviewOptions.Companion::class)
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

    @Serializable
    private data class Surrogate(
        @SerialName(isDisabledField)
        val isDisabled: Boolean = true,
        @SerialName(urlField)
        val url: String? = null,
        @SerialName(preferSmallMediaField)
        val preferSmallMedia: Boolean = false,
        @SerialName(preferLargeMediaField)
        val preferLargeMedia: Boolean = false,
        @SerialName(showAboveTextField)
        val showAboveText: Boolean = false,
    ) {

    }

    companion object : KSerializer<LinkPreviewOptions> {
        override val descriptor: SerialDescriptor
            get() = Surrogate.serializer().descriptor

        override fun deserialize(decoder: Decoder): LinkPreviewOptions {
            val surrogate = Surrogate.serializer().deserialize(decoder)

            return when {
                surrogate.isDisabled -> Disabled
                surrogate.preferLargeMedia -> Large(surrogate.url, surrogate.showAboveText)
                surrogate.preferSmallMedia -> Small(surrogate.url, surrogate.showAboveText)
                else -> Medium(surrogate.url, surrogate.showAboveText)
            }
        }

        override fun serialize(encoder: Encoder, value: LinkPreviewOptions) {
            when (value) {
                is Disabled -> Disabled.serializer().serialize(encoder, value)
                is Large -> Large.serializer().serialize(encoder, value)
                is Medium -> Medium.serializer().serialize(encoder, value)
                is Small -> Small.serializer().serialize(encoder, value)
            }
        }

    }
}