package com.github.insanusmokrassar.TelegramBotAPI.types

import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.PrivateChat
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.extended.ExtendedPrivateChatImpl
import com.github.insanusmokrassar.TelegramBotAPI.utils.PreviewFeature
import com.github.insanusmokrassar.TelegramBotAPI.utils.nonstrictJsonFormat
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonObjectSerializer

@Serializable(UserSerializer::class)
sealed class User : PrivateChat

@Serializable
data class CommonUser(
    override val id: ChatId,
    @SerialName(firstNameField)
    override val firstName: String,
    @SerialName(lastNameField)
    override val lastName: String = "",
    @SerialName(usernameField)
    override val username: Username? = null,
    @SerialName(languageCodeField)
    val languageCode: String? = null
) : User()

@PreviewFeature
typealias ExtendedUser = ExtendedPrivateChatImpl

@Serializable(UserSerializer::class)
sealed class Bot : User() {
    abstract override val username: Username
}

@Serializable
data class CommonBot(
    override val id: ChatId,
    @SerialName(usernameField)
    override val username: Username,
    @SerialName(firstNameField)
    override val firstName: String,
    @SerialName(lastNameField)
    override val lastName: String = ""
) : Bot() {
    @SerialName(isBotField)
    private val isBot = true
}

@Serializable
data class ExtendedBot(
    override val id: ChatId,
    @SerialName(usernameField)
    override val username: Username,
    @SerialName(firstNameField)
    override val firstName: String,
    @SerialName(lastNameField)
    override val lastName: String = "",
    @SerialName(canJoinGroupsField)
    val canJoinGroups: Boolean = false,
    @SerialName(canReadAllGroupMessagesField)
    val canReadAllGroupMessages: Boolean = false,
    @SerialName(supportInlineQueriesField)
    val supportsInlineQueries: Boolean = false
) : Bot() {
    @SerialName(isBotField)
    private val isBot = true
}


@Serializer(User::class)
internal object UserSerializer : KSerializer<User> {
    override fun deserialize(decoder: Decoder): User {
        val asJson = JsonObjectSerializer.deserialize(decoder)

        return when {
            asJson.getPrimitiveOrNull(isBotField) ?.booleanOrNull != true -> nonstrictJsonFormat.fromJson(
                CommonUser.serializer(),
                asJson
            )
            else -> {
                if ((asJson.get(canJoinGroupsField)
                    ?: asJson.get(canReadAllGroupMessagesField)
                    ?: asJson.get(supportInlineQueriesField)) != null
                ) {
                    nonstrictJsonFormat.fromJson(
                        ExtendedBot.serializer(),
                        asJson
                    )
                } else {
                    nonstrictJsonFormat.fromJson(
                        CommonBot.serializer(),
                        asJson
                    )
                }
            }
        }
    }

    override fun serialize(encoder: Encoder, value: User) {
        when (value) {
            is CommonUser -> CommonUser.serializer().serialize(encoder, value)
            is CommonBot -> CommonBot.serializer().serialize(encoder, value)
            is ExtendedBot -> ExtendedBot.serializer().serialize(encoder, value)
        }
    }
}
