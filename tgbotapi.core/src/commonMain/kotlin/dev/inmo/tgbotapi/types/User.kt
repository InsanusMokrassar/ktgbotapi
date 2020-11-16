package dev.inmo.tgbotapi.types

import dev.inmo.tgbotapi.types.chat.abstracts.PrivateChat
import dev.inmo.tgbotapi.types.chat.extended.ExtendedPrivateChatImpl
import dev.inmo.tgbotapi.utils.PreviewFeature
import dev.inmo.tgbotapi.utils.nonstrictJsonFormat
import kotlinx.serialization.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

@Serializable(UserSerializer::class)
sealed class User : PrivateChat

@Serializable
data class CommonUser(
    override val id: UserId,
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
    override val id: UserId,
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
    override val id: UserId,
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
        val asJson = JsonObject.serializer().deserialize(decoder)

        return when {
            asJson[isBotField] ?.jsonPrimitive ?.booleanOrNull != true -> nonstrictJsonFormat.decodeFromJsonElement(
                CommonUser.serializer(),
                asJson
            )
            else -> {
                if ((asJson[canJoinGroupsField]
                    ?: asJson[canReadAllGroupMessagesField]
                    ?: asJson[supportInlineQueriesField]) != null
                ) {
                    nonstrictJsonFormat.decodeFromJsonElement(
                        ExtendedBot.serializer(),
                        asJson
                    )
                } else {
                    nonstrictJsonFormat.decodeFromJsonElement(
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
