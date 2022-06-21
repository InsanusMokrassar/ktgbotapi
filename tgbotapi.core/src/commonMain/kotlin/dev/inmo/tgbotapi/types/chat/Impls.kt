package dev.inmo.tgbotapi.types.chat

import dev.inmo.micro_utils.language_codes.IetfLanguageCode
import dev.inmo.micro_utils.language_codes.IetfLanguageCodeSerializer
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.abstracts.WithOptionalLanguageCode
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GroupChatImpl(
    @SerialName(idField)
    override val id: ChatId,
    @SerialName(titleField)
    override val title: String
) : GroupChat

@Serializable
data class PrivateChatImpl(
    @SerialName(idField)
    override val id: ChatId,
    @SerialName(usernameField)
    override val username: Username? = null,
    @SerialName(firstNameField)
    override val firstName: String = "",
    @SerialName(lastNameField)
    override val lastName: String = ""
) : PrivateChat

@Serializable
data class SupergroupChatImpl(
    @SerialName(idField)
    override val id: ChatId,
    @SerialName(titleField)
    override val title: String,
    @SerialName(usernameField)
    override val username: Username? = null
) : SupergroupChat

@Serializable
data class ChannelChatImpl(
    @SerialName(idField)
    override val id: ChatId,
    @SerialName(titleField)
    override val title: String,
    @SerialName(usernameField)
    override val username: Username? = null
) : ChannelChat

@Serializable(UserSerializer::class)
sealed class User : PrivateChat

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
data class CommonUser(
    override val id: UserId,
    @SerialName(firstNameField)
    override val firstName: String,
    @SerialName(lastNameField)
    override val lastName: String = "",
    @SerialName(usernameField)
    override val username: Username? = null,
    @SerialName(languageCodeField)
    @Serializable(IetfLanguageCodeSerializer::class)
    override val ietfLanguageCode: IetfLanguageCode? = null,
    @SerialName(isPremiumField)
    override val isPremium: Boolean = false
) : User(), WithOptionalLanguageCode, PremiumChat {
    constructor(
        id: UserId,
        firstName: String,
        lastName: String = "",
        username: Username? = null,
        languageCode: String
    ) : this(id, firstName, lastName, username, IetfLanguageCode(languageCode))
}
