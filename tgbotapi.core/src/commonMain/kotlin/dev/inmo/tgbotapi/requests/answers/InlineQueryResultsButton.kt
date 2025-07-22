package dev.inmo.tgbotapi.requests.answers

import dev.inmo.tgbotapi.types.startParameterField
import dev.inmo.tgbotapi.types.textField
import dev.inmo.tgbotapi.types.webAppField
import dev.inmo.tgbotapi.types.webapps.WebAppInfo
import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(InlineQueryResultsButtonSerializer::class)
@ClassCastsIncluded
sealed interface InlineQueryResultsButton {
    val text: String

    @Serializable
    class Raw internal constructor(
        @SerialName(textField)
        val text: String,
        @SerialName(webAppField)
        val webAppInfo: WebAppInfo? = null,
        @SerialName(startParameterField)
        val deepLinkParameter: String? = null
    )

    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(InlineQueryResultsButtonSerializer::class)
    data class WebApp(
        @SerialName(textField)
        override val text: String,
        @SerialName(webAppField)
        val webAppInfo: WebAppInfo
    ) : InlineQueryResultsButton

    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(InlineQueryResultsButtonSerializer::class)
    data class Start(
        @SerialName(textField)
        override val text: String,
        @SerialName(startParameterField)
        val deepLinkParameter: String
    ) : InlineQueryResultsButton

    @ConsistentCopyVisibility
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(InlineQueryResultsButtonSerializer::class)
    data class Unknown internal constructor (
        @SerialName(textField)
        override val text: String
    ) : InlineQueryResultsButton

    companion object {
        operator fun invoke(
            text: String,
            deepLinkParameter: String
        ) = Start(text, deepLinkParameter)
        operator fun invoke(
            text: String,
            webAppInfo: WebAppInfo
        ) = WebApp(text, webAppInfo)
    }
}

object InlineQueryResultsButtonSerializer : KSerializer<InlineQueryResultsButton> {
    override val descriptor: SerialDescriptor = InlineQueryResultsButton.Raw.serializer().descriptor

    override fun deserialize(decoder: Decoder): InlineQueryResultsButton {
        val raw = InlineQueryResultsButton.Raw.serializer().deserialize(decoder)

        return when {
            raw.webAppInfo != null -> InlineQueryResultsButton.WebApp(raw.text, raw.webAppInfo)
            raw.deepLinkParameter != null -> InlineQueryResultsButton.Start(raw.text, raw.deepLinkParameter)
            else -> InlineQueryResultsButton.Unknown(raw.text)
        }
    }

    override fun serialize(encoder: Encoder, value: InlineQueryResultsButton) {
        InlineQueryResultsButton.Raw.serializer().serialize(
            encoder,
            InlineQueryResultsButton.Raw(
                value.text,
                (value as? InlineQueryResultsButton.WebApp)?.webAppInfo,
                (value as? InlineQueryResultsButton.Start)?.deepLinkParameter,
            )
        )
    }
}
