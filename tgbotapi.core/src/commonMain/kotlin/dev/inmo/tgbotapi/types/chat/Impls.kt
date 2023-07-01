package dev.inmo.tgbotapi.types.chat

import dev.inmo.micro_utils.language_codes.IetfLanguageCode
import dev.inmo.micro_utils.language_codes.IetfLanguageCodeSerializer
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.abstracts.WithOptionalLanguageCode
import dev.inmo.tgbotapi.utils.RiskFeature
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@RiskFeature("This class is a subject of changes. It is better to use GroupChat due")
data class GroupChatImpl(
    @SerialName(idField)
    override val id: ChatId,
    @SerialName(titleField)
    override val title: String
) : GroupChat

@Serializable
@RiskFeature("This class is a subject of changes. It is better to use PrivateChat due")
data class PrivateChatImpl(
    @SerialName(idField)
    override val id: UserId,
    @SerialName(usernameField)
    override val username: Username? = null,
    @SerialName(firstNameField)
    override val firstName: String = "",
    @SerialName(lastNameField)
    override val lastName: String = ""
) : PrivateChat

@Serializable
@RiskFeature("This class is a subject of changes. It is better to use SupergroupChat due")
data class SupergroupChatImpl(
    @SerialName(idField)
    override val id: ChatId,
    @SerialName(titleField)
    override val title: String,
    @SerialName(usernameField)
    override val username: Username? = null
) : SupergroupChat

@Serializable
@RiskFeature("This class is a subject of changes. It is better to use ForumChat due")
data class ForumChatImpl(
    @SerialName(idField)
    override val id: IdChatIdentifier,
    @SerialName(titleField)
    override val title: String,
    @SerialName(usernameField)
    override val username: Username? = null
) : ForumChat

@Serializable
@RiskFeature("This class is a subject of changes. It is better to use ChannelChat due")
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
sealed class Bot : User()

@Serializable
data class CommonBot(
    override val id: UserId,
    @SerialName(firstNameField)
    override val firstName: String,
    @SerialName(lastNameField)
    override val lastName: String = "",
    @SerialName(usernameField)
    override val username: Username? = null,
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
    override val isPremium: Boolean = false,
    @SerialName(addedToAttachmentMenuField)
    override val addedToAttachmentMenu: Boolean = false
) : User(), WithOptionalLanguageCode, PossiblyPremiumChat, AbleToAddInAttachmentMenuChat {
    constructor(
        id: UserId,
        firstName: String,
        lastName: String = "",
        username: Username? = null,
        languageCode: String
    ) : this(id, firstName, lastName, username, IetfLanguageCode(languageCode))
}
