package com.github.insanusmokrassar.TelegramBotAPI.types

import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.PrivateChat
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
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

@Serializable
data class Bot(
    override val id: ChatId,
    @SerialName(firstNameField)
    override val firstName: String,
    @SerialName(lastNameField)
    override val lastName: String = "",
    @SerialName(usernameField)
    override val username: Username? = null,
    @SerialName(canJoinGroupsField)
    val canJoinGroups: Boolean = false,
    @SerialName(canReadAllGroupMessagesField)
    val canReadAllGroupMessages: Boolean = false,
    @SerialName(supportInlineQueriesField)
    val supportsInlineQueries: Boolean = false
) : User() {
    @SerialName(isBotField)
    private val isBot = true
}

@Serializer(User::class)
internal object UserSerializer : KSerializer<User> {
    override fun deserialize(decoder: Decoder): User {
        val asJson = JsonObjectSerializer.deserialize(decoder)

        return when {
            asJson.getPrimitiveOrNull(isBotField) ?.booleanOrNull != true -> Json.nonstrict.fromJson(
                CommonUser.serializer(),
                asJson
            )
            else -> Json.nonstrict.fromJson(
                Bot.serializer(),
                asJson
            )
        }
    }

    override fun serialize(encoder: Encoder, obj: User) {
        when (obj) {
            is CommonUser -> CommonUser.serializer().serialize(encoder, obj)
            is Bot -> Bot.serializer().serialize(encoder, obj)
        }
    }
}
