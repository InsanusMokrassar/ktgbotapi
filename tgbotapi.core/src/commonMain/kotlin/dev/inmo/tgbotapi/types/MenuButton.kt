package dev.inmo.tgbotapi.types

import dev.inmo.tgbotapi.types.webapps.WebAppInfo
import dev.inmo.tgbotapi.utils.RiskFeature
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

@Serializable(MenuButtonSerializer::class)
@OptIn(ExperimentalSerializationApi::class)
sealed interface MenuButton {
    @EncodeDefault
    val type: String

    @Serializable
    object Commands : MenuButton {
        @Required
        @EncodeDefault
        override val type: String
            get() = "commands"
    }

    @Serializable
    data class WebApp(
        val text: String,
        @SerialName(webAppField)
        val webApp: WebAppInfo,
    ) : MenuButton {
        @Required
        @EncodeDefault
        override val type: String
            get() = Companion.type

        companion object {
            val type: String
                get() = "web_app"
        }
    }

    @Serializable
    object Default : MenuButton {
        @Required
        @EncodeDefault
        override val type: String
            get() = "default"
    }

    @Serializable
    @RiskFeature
    data class Unknown(
        override val type: String,
        val rawJson: JsonElement,
    ) : MenuButton
}

@Serializable
internal data class MenuButtonSurrogate(
    val type: String,
    val text: String? = null,
    @SerialName(webAppField)
    val webApp: WebAppInfo? = null,
    val srcJsonElement: JsonElement? = null,
)

@Serializer(MenuButton::class)
object MenuButtonSerializer : KSerializer<MenuButton> {
    override val descriptor: SerialDescriptor
        get() = MenuButtonSurrogate.serializer().descriptor

    override fun deserialize(decoder: Decoder): MenuButton {
        val surrogate = if (decoder is JsonDecoder) {
            val json = JsonElement.serializer().deserialize(decoder)
            runCatching {
                decoder.json.decodeFromJsonElement(MenuButtonSurrogate.serializer(), json)
            }.onFailure {
                return MenuButton.Unknown(
                    runCatching { json.jsonObject[typeField] ?.jsonPrimitive ?.content }.getOrNull() ?: "",
                    json,
                )
            }.getOrThrow().copy(
                srcJsonElement = json,
            )
        } else {
            MenuButtonSurrogate.serializer().deserialize(decoder)
        }

        return when (surrogate.type) {
            MenuButton.Commands.type -> MenuButton.Commands
            MenuButton.Default.type -> MenuButton.Default
            MenuButton.WebApp.type ->
                if (surrogate.text != null && surrogate.webApp != null) {
                    MenuButton.WebApp(surrogate.text, surrogate.webApp)
                } else {
                    null
                }
            else -> null
        } ?: MenuButton.Unknown(
            surrogate.type,
            surrogate.srcJsonElement ?: buildJsonObject { },
        )
    }

    override fun serialize(
        encoder: Encoder,
        value: MenuButton,
    ) {
        encoder.encodeSerializableValue(
            MenuButtonSurrogate.serializer(),
            when (value) {
                MenuButton.Default,
                MenuButton.Commands,
                -> MenuButtonSurrogate(value.type)
                is MenuButton.WebApp -> MenuButtonSurrogate(value.type, value.text, value.webApp)
                is MenuButton.Unknown -> {
                    encoder.encodeSerializableValue(
                        JsonElement.serializer(),
                        value.rawJson,
                    )
                    return
                }
            },
        )
    }
}
