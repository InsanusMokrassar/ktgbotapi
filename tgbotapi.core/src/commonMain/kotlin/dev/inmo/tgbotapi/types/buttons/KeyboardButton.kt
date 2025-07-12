@file:OptIn(ExperimentalSerializationApi::class)
@file:Suppress("unused", "KDocUnresolvedReference")

package dev.inmo.tgbotapi.types.buttons

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.webapps.WebAppInfo
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.nonstrictJsonFormat
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

/**
 * Representation union of https://core.telegram.org/bots/api#keyboardbutton . See inheritors for more info
 */
@Serializable(KeyboardButtonSerializer::class)
sealed interface KeyboardButton {
    val text: String
}

/**
 * Simple button. user will send text of this button. You will be able to catch this text in updates and data using
 * [dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling.onText] in
 * case you are using Behaviour Builder OR with [dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter.messagesFlow]
 * and [kotlinx.coroutines.flow.filterIsInstance] and filtering by type
 * [dev.inmo.tgbotapi.types.message.abstracts.CommonMessage] and [dev.inmo.tgbotapi.extensions.utils.onlyTextContentMessages]
*/
@Serializable
data class SimpleKeyboardButton(
    override val text: String
) : KeyboardButton

@ConsistentCopyVisibility
@Serializable
data class UnknownKeyboardButton internal constructor(
    override val text: String,
    val raw: String
) : KeyboardButton

/**
 * Private chats only. When user will tap on this button, his contact (with his number and name) will be sent to the bot. You will be able
 * to catch this contact in updates and data using [dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling.onContact] in
 * case you are using Behaviour Builder OR with [dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter.messagesFlow]
 * and [kotlinx.coroutines.flow.filterIsInstance] and filtering by type
 * [dev.inmo.tgbotapi.types.message.abstracts.CommonMessage] and [dev.inmo.tgbotapi.extensions.utils.onlyContactContentMessages]
*/
@Serializable
data class RequestContactKeyboardButton(
    override val text: String
) : KeyboardButton {
    @SerialName(requestContactField)
    @EncodeDefault
    val requestContact: Boolean = true
}

/**
 * Private chats only. When user will tap on this button, his location will be sent to the bot. You will be able
 * to catch this location in updates and data using [dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling.onLocation] in
 * case you are using Behaviour Builder OR with [dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter.messagesFlow]
 * and [kotlinx.coroutines.flow.filterIsInstance] and filtering by type
 * [dev.inmo.tgbotapi.types.message.abstracts.CommonMessage] and [dev.inmo.tgbotapi.extensions.utils.onlyLocationContentMessages]
 */
@Serializable
data class RequestLocationKeyboardButton(
    override val text: String
) : KeyboardButton {
    @SerialName(requestLocationField)
    @Required
    @EncodeDefault
    val requestLocation: Boolean = true
}

/**
 * Private chats only. Description of the Web App that will be launched when the user presses the button. The Web App
 * will be able to send an arbitrary message on behalf of the user using the method `answerWebAppQuery`. Available only
 * in private chats between a user and the bot.
 */
@Serializable
data class WebAppKeyboardButton(
    override val text: String,
    @SerialName(webAppField)
    val webApp: WebAppInfo
) : KeyboardButton

/**
 * Private chats only. When user will tap on this button, he will be asked for the poll with [requestPoll] options. You will be able
 * to catch this poll in updates and data using [dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling.onPoll] in
 * case you are using Behaviour Builder OR with [dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter.messagesFlow]
 * and [kotlinx.coroutines.flow.filterIsInstance] and filtering by type
 * [dev.inmo.tgbotapi.types.message.abstracts.CommonMessage] and [dev.inmo.tgbotapi.extensions.utils.onlyPollContentMessages]
 */
@Serializable
data class RequestPollKeyboardButton(
    override val text: String,
    @SerialName(requestPollField)
    val requestPoll: KeyboardButtonPollType
) : KeyboardButton

/**
 * Private chats only. When user will tap on this button, he will be asked for the chat with [requestChat] options. You
 * will be able to catch this [ChatId] in updates and data using
 * [dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling.onUserShared] in case you are using Behaviour
 * Builder OR with [dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter.messagesFlow]
 * and [kotlinx.coroutines.flow.filterIsInstance].
 *
 * In case you will use [dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling.onUserShared] it is
 * recommended to use [kotlinx.coroutines.flow.Flow] [kotlinx.coroutines.flow.filter] with checking of incoming
 * [dev.inmo.tgbotapi.types.request.UsersShared.requestId]
 */
@Serializable
data class RequestUserKeyboardButton(
    override val text: String,
    @SerialName(requestUsersField)
    val requestUsers: KeyboardButtonRequestUsers
) : KeyboardButton

/**
 * Private chats only. When user will tap on this button, he will be asked for the chat with [requestChat] options. You
 * will be able to catch this [ChatId] in updates and data using
 * [dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling.onChatShared] in case you are using Behaviour
 * Builder OR with [dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter.messagesFlow]
 * and [kotlinx.coroutines.flow.filterIsInstance].
 *
 * In case you will use [dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling.onChatShared] it is
 * recommended to use [kotlinx.coroutines.flow.Flow] [kotlinx.coroutines.flow.filter] with checking of incoming
 * [dev.inmo.tgbotapi.types.request.ChatShared.requestId]
 */
@Serializable
data class RequestChatKeyboardButton(
    override val text: String,
    @SerialName(requestChatField)
    val requestChat: KeyboardButtonRequestChat
) : KeyboardButton

@RiskFeature
object KeyboardButtonSerializer : KSerializer<KeyboardButton> {
    private val internalSerializer = JsonElement.serializer()
    override val descriptor: SerialDescriptor = internalSerializer.descriptor

    override fun deserialize(decoder: Decoder): KeyboardButton {
        val asJson = internalSerializer.deserialize(decoder)

        return when {
            asJson is JsonPrimitive -> SimpleKeyboardButton(asJson.content)
            asJson is JsonObject && asJson[requestContactField] != null -> RequestContactKeyboardButton(
                asJson[textField]!!.jsonPrimitive.content
            )
            asJson is JsonObject && asJson[requestLocationField] != null -> RequestLocationKeyboardButton(
                asJson[textField]!!.jsonPrimitive.content
            )
            asJson is JsonObject && asJson[webAppField] != null -> WebAppKeyboardButton(
                asJson[textField]!!.jsonPrimitive.content,
                nonstrictJsonFormat.decodeFromJsonElement(
                    WebAppInfo.serializer(),
                    asJson[webAppField]!!
                )
            )
            asJson is JsonObject && asJson[requestPollField] != null -> RequestPollKeyboardButton(
                asJson[textField]!!.jsonPrimitive.content,
                nonstrictJsonFormat.decodeFromJsonElement(
                    KeyboardButtonPollTypeSerializer,
                    asJson[requestPollField] ?.jsonObject ?: buildJsonObject {  }
                )
            )
            asJson is JsonObject && asJson[requestUsersField] != null -> RequestUserKeyboardButton(
                asJson[textField]!!.jsonPrimitive.content,
                nonstrictJsonFormat.decodeFromJsonElement(
                    KeyboardButtonRequestUsers.serializer(),
                    asJson[requestUsersField] ?.jsonObject ?: buildJsonObject {  }
                )
            )
            asJson is JsonObject && asJson[requestChatField] != null -> RequestChatKeyboardButton(
                asJson[textField]!!.jsonPrimitive.content,
                nonstrictJsonFormat.decodeFromJsonElement(
                    KeyboardButtonRequestChat.serializer(),
                    asJson[requestChatField] ?.jsonObject ?: buildJsonObject {  }
                )
            )
            else -> UnknownKeyboardButton(
                when (asJson) {
                    is JsonObject -> asJson[textField]!!.jsonPrimitive.content
                    is JsonArray -> ""
                    is JsonPrimitive -> asJson.content
                },
                asJson.toString()
            )
        }
    }

    override fun serialize(encoder: Encoder, value: KeyboardButton) {
        when (value) {
            is RequestContactKeyboardButton -> RequestContactKeyboardButton.serializer().serialize(encoder, value)
            is RequestLocationKeyboardButton -> RequestLocationKeyboardButton.serializer().serialize(encoder, value)
            is WebAppKeyboardButton -> WebAppKeyboardButton.serializer().serialize(encoder, value)
            is RequestPollKeyboardButton -> RequestPollKeyboardButton.serializer().serialize(encoder, value)
            is SimpleKeyboardButton -> encoder.encodeString(value.text)
            is RequestUserKeyboardButton -> RequestUserKeyboardButton.serializer().serialize(encoder, value)
            is RequestChatKeyboardButton -> RequestChatKeyboardButton.serializer().serialize(encoder, value)
            is UnknownKeyboardButton -> JsonElement.serializer().serialize(encoder, nonstrictJsonFormat.parseToJsonElement(value.raw))
        }
    }
}

