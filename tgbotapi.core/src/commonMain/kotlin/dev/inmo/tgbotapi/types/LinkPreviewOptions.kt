@file:OptIn(ExperimentalSerializationApi::class)
@file:Suppress("RemoveRedundantQualifierName")

package dev.inmo.tgbotapi.types

import kotlinx.serialization.*
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

    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(LinkPreviewOptions.Companion::class)
    data object Disabled : LinkPreviewOptions {
        @Required
        @EncodeDefault
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

    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(LinkPreviewOptions.Companion::class)
    data class Large(
        @SerialName(urlField)
        override val url: String?,
        @SerialName(showAboveTextField)
        override val showAboveText: Boolean
    ) : LinkPreviewOptions {
        @Required
        @EncodeDefault
        @SerialName(isDisabledField)
        override val isDisabled: Boolean = false
        @Required
        @EncodeDefault
        @SerialName(preferLargeMediaField)
        override val preferLargeMedia: Boolean = true
        override val preferSmallMedia: Boolean
            get() = false
    }

    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(LinkPreviewOptions.Companion::class)
    data class Small(
        @SerialName(urlField)
        override val url: String?,
        @SerialName(showAboveTextField)
        override val showAboveText: Boolean
    ) : LinkPreviewOptions {
        @Required
        @EncodeDefault
        @SerialName(isDisabledField)
        override val isDisabled: Boolean = false
        @Required
        @EncodeDefault
        @SerialName(preferSmallMediaField)
        override val preferSmallMedia: Boolean = true
        override val preferLargeMedia: Boolean
            get() = false
    }

    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(LinkPreviewOptions.Companion::class)
    data class Default(
        @SerialName(urlField)
        override val url: String?,
        @SerialName(showAboveTextField)
        override val showAboveText: Boolean
    ) : LinkPreviewOptions {
        @Required
        @EncodeDefault
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
        val isDisabled: Boolean = false,
        @SerialName(urlField)
        val url: String? = null,
        @SerialName(preferSmallMediaField)
        val preferSmallMedia: Boolean = false,
        @SerialName(preferLargeMediaField)
        val preferLargeMedia: Boolean = false,
        @SerialName(showAboveTextField)
        val showAboveText: Boolean = false,
    )

    companion object : KSerializer<LinkPreviewOptions> {
        override val descriptor: SerialDescriptor
            get() = Surrogate.serializer().descriptor

        override fun deserialize(decoder: Decoder): LinkPreviewOptions {
            val surrogate = Surrogate.serializer().deserialize(decoder)

            return when {
                surrogate.isDisabled -> Disabled
                surrogate.preferLargeMedia -> Large(surrogate.url, surrogate.showAboveText)
                surrogate.preferSmallMedia -> Small(surrogate.url, surrogate.showAboveText)
                else -> Default(surrogate.url, surrogate.showAboveText)
            }
        }

        override fun serialize(encoder: Encoder, value: LinkPreviewOptions) {
            Surrogate.serializer().serialize(
                encoder,
                Surrogate(
                    isDisabled = value.isDisabled,
                    url = value.url,
                    preferSmallMedia = value.preferSmallMedia,
                    preferLargeMedia = value.preferLargeMedia,
                    showAboveText = value.showAboveText
                )
            )
        }
    }
}