package dev.inmo.tgbotapi.types.rich

import dev.inmo.tgbotapi.types.LoginURL
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.CopyTextButtonData
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.DisabledButton
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.SwitchInlineQueryChosenChat as SwitchInlineQueryChosenChatParameters
import dev.inmo.tgbotapi.types.webapps.WebAppInfo
import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonEncoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject

/**
 * A button in a rich message. Every subtype contains exactly one Bot API button action. [text] may contain only plain
 * text, custom emoji and date-time entities. [LoginUrl] does not support [LoginURL.botUsername].
 *
 * @see <a href="https://core.telegram.org/bots/api#richmessagebutton">RichMessageButton</a>
 */
@Serializable(RichMessageButtonSerializer::class)
@ClassCastsIncluded
sealed interface RichMessageButton {
    val text: RichText
    val style: RichMessageButtonStyle?

    @Serializable
    data class Url(
        @SerialName(textField) override val text: RichText,
        @SerialName(urlField) val url: String,
        @SerialName(styleField) override val style: RichMessageButtonStyle? = null
    ) : RichMessageButton {
        init { validateRichMessageButton(text, style) }
    }

    @Serializable
    data class CallbackData(
        @SerialName(textField) override val text: RichText,
        @SerialName(callbackDataField) val callbackData: String,
        @SerialName(styleField) override val style: RichMessageButtonStyle? = null
    ) : RichMessageButton {
        init { validateRichMessageButton(text, style, allowLinkStyle = true) }
    }

    @Serializable
    data class WebApp(
        @SerialName(textField) override val text: RichText,
        @SerialName(webAppField) val webApp: WebAppInfo,
        @SerialName(styleField) override val style: RichMessageButtonStyle? = null
    ) : RichMessageButton {
        init { validateRichMessageButton(text, style) }
    }

    @Serializable
    data class LoginUrl(
        @SerialName(textField) override val text: RichText,
        @SerialName(loginUrlField) val loginUrl: LoginURL,
        @SerialName(styleField) override val style: RichMessageButtonStyle? = null
    ) : RichMessageButton {
        init {
            validateRichMessageButton(text, style)
            require(loginUrl.botUsername == null) { "Rich message login URL buttons do not support bot usernames" }
        }
    }

    @Serializable
    data class SwitchInlineQuery(
        @SerialName(textField) override val text: RichText,
        @SerialName(switchInlineQueryField) val switchInlineQuery: String,
        @SerialName(styleField) override val style: RichMessageButtonStyle? = null
    ) : RichMessageButton {
        init { validateRichMessageButton(text, style) }
    }

    @Serializable
    data class SwitchInlineQueryCurrentChat(
        @SerialName(textField) override val text: RichText,
        @SerialName(switchInlineQueryCurrentChatField) val switchInlineQueryCurrentChat: String,
        @SerialName(styleField) override val style: RichMessageButtonStyle? = null
    ) : RichMessageButton {
        init { validateRichMessageButton(text, style) }
    }

    @Serializable
    data class SwitchInlineQueryChosenChat(
        @SerialName(textField) override val text: RichText,
        @SerialName(switchInlineQueryChosenChatField) val switchInlineQueryChosenChat: SwitchInlineQueryChosenChatParameters,
        @SerialName(styleField) override val style: RichMessageButtonStyle? = null
    ) : RichMessageButton {
        init { validateRichMessageButton(text, style) }
    }

    @Serializable
    data class CopyText(
        @SerialName(textField) override val text: RichText,
        @SerialName(copyTextField) val copyText: CopyTextButtonData,
        @SerialName(styleField) override val style: RichMessageButtonStyle? = null
    ) : RichMessageButton {
        init { validateRichMessageButton(text, style) }
    }

    @Serializable
    data class Disabled(
        @SerialName(textField) override val text: RichText,
        @SerialName(styleField) override val style: RichMessageButtonStyle? = null
    ) : RichMessageButton {
        init { validateRichMessageButton(text, style) }
        @SerialName(disabledField)
        @kotlinx.serialization.EncodeDefault
        val disabled: DisabledButton = DisabledButton
    }
}

private fun validateRichMessageButton(
    text: RichText,
    style: RichMessageButtonStyle?,
    allowLinkStyle: Boolean = false
) {
    require(text.isValidRichMessageButtonText()) {
        "Rich message button text can contain only plain text, custom emoji and date-time entities"
    }
    require(allowLinkStyle || style != RichMessageButtonStyle.Link) {
        "The link style is allowed only for callback buttons"
    }
}

private fun RichText.isValidRichMessageButtonText(): Boolean = when (this) {
    is RichTextPlain -> true
    is RichTextCustomEmoji -> true
    is RichTextDateTime -> text.isValidRichMessageButtonText()
    is RichTextGroup -> parts.all { it.isValidRichMessageButtonText() }
    else -> false
}

/** Closed set of styles supported by [RichMessageButton]. */
@Serializable(RichMessageButtonStyle.Serializer::class)
sealed interface RichMessageButtonStyle {
    val name: String

    @Serializable(Serializer::class)
    data object Danger : RichMessageButtonStyle { override val name = "danger" }
    @Serializable(Serializer::class)
    data object Success : RichMessageButtonStyle { override val name = "success" }
    @Serializable(Serializer::class)
    data object Primary : RichMessageButtonStyle { override val name = "primary" }
    @Serializable(Serializer::class)
    data object Link : RichMessageButtonStyle { override val name = "link" }

    object Serializer : KSerializer<RichMessageButtonStyle> {
        override val descriptor: SerialDescriptor = String.serializer().descriptor
        override fun serialize(encoder: Encoder, value: RichMessageButtonStyle) = encoder.encodeString(value.name)
        override fun deserialize(decoder: Decoder): RichMessageButtonStyle = when (val name = decoder.decodeString()) {
            Danger.name -> Danger
            Success.name -> Success
            Primary.name -> Primary
            Link.name -> Link
            else -> throw SerializationException("Unknown RichMessageButtonStyle: $name")
        }
    }
}

object RichMessageButtonSerializer : KSerializer<RichMessageButton> {
    override val descriptor: SerialDescriptor = JsonObject.serializer().descriptor
    private val actionFields = setOf(
        urlField, callbackDataField, webAppField, loginUrlField, switchInlineQueryField,
        switchInlineQueryCurrentChatField, switchInlineQueryChosenChatField, copyTextField, disabledField
    )

    override fun deserialize(decoder: Decoder): RichMessageButton {
        val input = decoder as? JsonDecoder ?: throw SerializationException("RichMessageButton requires JSON")
        val element = input.decodeJsonElement().jsonObject
        val actions = element.keys.intersect(actionFields)
        if (actions.size != 1) throw SerializationException("RichMessageButton requires exactly one action, got $actions")
        return when (actions.single()) {
            urlField -> input.json.decodeFromJsonElement(RichMessageButton.Url.serializer(), element)
            callbackDataField -> input.json.decodeFromJsonElement(RichMessageButton.CallbackData.serializer(), element)
            webAppField -> input.json.decodeFromJsonElement(RichMessageButton.WebApp.serializer(), element)
            loginUrlField -> input.json.decodeFromJsonElement(RichMessageButton.LoginUrl.serializer(), element)
            switchInlineQueryField -> input.json.decodeFromJsonElement(RichMessageButton.SwitchInlineQuery.serializer(), element)
            switchInlineQueryCurrentChatField -> input.json.decodeFromJsonElement(RichMessageButton.SwitchInlineQueryCurrentChat.serializer(), element)
            switchInlineQueryChosenChatField -> input.json.decodeFromJsonElement(RichMessageButton.SwitchInlineQueryChosenChat.serializer(), element)
            copyTextField -> input.json.decodeFromJsonElement(RichMessageButton.CopyText.serializer(), element)
            disabledField -> input.json.decodeFromJsonElement(RichMessageButton.Disabled.serializer(), element)
            else -> error("Unreachable RichMessageButton action")
        }
    }

    override fun serialize(encoder: Encoder, value: RichMessageButton) {
        val output = encoder as? JsonEncoder ?: throw SerializationException("RichMessageButton requires JSON")
        val element = when (value) {
            is RichMessageButton.Url -> output.json.encodeToJsonElement(RichMessageButton.Url.serializer(), value)
            is RichMessageButton.CallbackData -> output.json.encodeToJsonElement(RichMessageButton.CallbackData.serializer(), value)
            is RichMessageButton.WebApp -> output.json.encodeToJsonElement(RichMessageButton.WebApp.serializer(), value)
            is RichMessageButton.LoginUrl -> output.json.encodeToJsonElement(RichMessageButton.LoginUrl.serializer(), value)
            is RichMessageButton.SwitchInlineQuery -> output.json.encodeToJsonElement(RichMessageButton.SwitchInlineQuery.serializer(), value)
            is RichMessageButton.SwitchInlineQueryCurrentChat -> output.json.encodeToJsonElement(RichMessageButton.SwitchInlineQueryCurrentChat.serializer(), value)
            is RichMessageButton.SwitchInlineQueryChosenChat -> output.json.encodeToJsonElement(RichMessageButton.SwitchInlineQueryChosenChat.serializer(), value)
            is RichMessageButton.CopyText -> output.json.encodeToJsonElement(RichMessageButton.CopyText.serializer(), value)
            is RichMessageButton.Disabled -> output.json.encodeToJsonElement(RichMessageButton.Disabled.serializer(), value)
        }
        output.encodeJsonElement(element)
    }
}

/** Horizontal alignment of [RichBlockButtons]. */
@Serializable(RichBlockButtonAlignment.Serializer::class)
sealed interface RichBlockButtonAlignment {
    val name: String

    @Serializable(Serializer::class)
    data object Left : RichBlockButtonAlignment { override val name = "left" }
    @Serializable(Serializer::class)
    data object Center : RichBlockButtonAlignment { override val name = "center" }
    @Serializable(Serializer::class)
    data object Right : RichBlockButtonAlignment { override val name = "right" }

    object Serializer : KSerializer<RichBlockButtonAlignment> {
        override val descriptor: SerialDescriptor = String.serializer().descriptor
        override fun serialize(encoder: Encoder, value: RichBlockButtonAlignment) = encoder.encodeString(value.name)
        override fun deserialize(decoder: Decoder): RichBlockButtonAlignment = when (val name = decoder.decodeString()) {
            Left.name -> Left
            Center.name -> Center
            Right.name -> Right
            else -> throw SerializationException("Unknown RichBlockButtonAlignment: $name")
        }
    }
}

internal fun RichMessageButton.toRichMarkup(text: String): String {
    val attributes = mutableListOf<String>()
    fun attribute(name: String, value: String) {
        attributes.add("$name=\"${value.escapeRichMarkupAttribute()}\"")
    }
    when (this) {
        is RichMessageButton.Url -> {
            attribute("type", "url")
            attribute("url", url)
        }
        is RichMessageButton.CallbackData -> {
            attribute("type", "callback_data")
            attribute("data", callbackData)
        }
        is RichMessageButton.WebApp -> {
            attribute("type", "web_app")
            attribute("url", webApp.url)
        }
        is RichMessageButton.LoginUrl -> {
            attribute("type", "login_url")
            attribute("url", loginUrl.url)
            loginUrl.forwardText?.let { attribute("forward-text", it) }
            loginUrl.botUsername?.let { attribute("bot-username", it) }
            if (loginUrl.requestWriteAccess == true) attributes.add("request-write-access")
        }
        is RichMessageButton.SwitchInlineQuery -> {
            attribute("type", "switch_inline_query")
            attribute("query", switchInlineQuery)
        }
        is RichMessageButton.SwitchInlineQueryCurrentChat -> {
            attribute("type", "switch_inline_query_current_chat")
            attribute("query", switchInlineQueryCurrentChat)
        }
        is RichMessageButton.SwitchInlineQueryChosenChat -> {
            attribute("type", "switch_inline_query_chosen_chat")
            switchInlineQueryChosenChat.query?.let { attribute("query", it) }
            if (switchInlineQueryChosenChat.allowUsers) attributes.add("allow-user-chats")
            if (switchInlineQueryChosenChat.allowBots) attributes.add("allow-bot-chats")
            if (switchInlineQueryChosenChat.allowGroups) attributes.add("allow-group-chats")
            if (switchInlineQueryChosenChat.allowChannels) attributes.add("allow-channel-chats")
        }
        is RichMessageButton.CopyText -> {
            attribute("type", "copy_text")
            attribute("text", copyText.text)
        }
        is RichMessageButton.Disabled -> attribute("type", "disabled")
    }
    style?.let { attribute("style", it.name) }
    return "<tg-button ${attributes.joinToString(" ")}>$text</tg-button>"
}

private fun String.escapeRichMarkupAttribute(): String = replace("&", "&amp;")
    .replace("\"", "&quot;")
    .replace("<", "&lt;")
    .replace(">", "&gt;")
