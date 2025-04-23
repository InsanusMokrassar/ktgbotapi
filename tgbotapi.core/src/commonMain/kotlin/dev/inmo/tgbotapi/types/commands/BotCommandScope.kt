package dev.inmo.tgbotapi.types.commands

import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
private class SurrogateBotCommandScope(
    @SerialName(typeField)
    @Required
    val type: String,
    @SerialName(chatIdField)
    val chatId: ChatIdentifier? = null,
    @SerialName(userIdField)
    val userId: UserId? = null,
) {
    fun asBotCommandScope() = when (type) {
        BotCommandScopeDefault.type -> BotCommandScopeDefault
        BotCommandScopeAllPrivateChats.type -> BotCommandScopeAllPrivateChats
        BotCommandScopeAllGroupChats.type -> BotCommandScopeAllGroupChats
        BotCommandScopeAllChatAdministrators.type -> BotCommandScopeAllChatAdministrators
        BotCommandScopeChatAdministrators.type ->
            BotCommandScopeChatAdministrators(
                chatId ?: error("${BotCommandScopeChatAdministrators.type} type must have $chatIdField field, but have no"),
            )
        BotCommandScopeChatMember.type ->
            BotCommandScopeChatMember(
                chatId ?: error("${BotCommandScopeChatMember.type} type must have $chatIdField field, but have no"),
                userId ?: error("${BotCommandScopeChatMember.type} type must have $userIdField field, but have no"),
            )
        BotCommandScopeChat.type ->
            BotCommandScopeChat(
                chatId ?: error("${BotCommandScopeChat.type} type must have $chatIdField field, but have no"),
            )
        else -> UnknownBotCommandScope(type)
    }

    companion object {
        fun from(scope: BotCommandScope) = when (scope) {
            is UnknownBotCommandScope,
            BotCommandScopeDefault,
            BotCommandScopeAllPrivateChats,
            BotCommandScopeAllGroupChats,
            BotCommandScopeAllChatAdministrators,
            -> SurrogateBotCommandScope(scope.type)
            is BotCommandScopeChatAdministrators -> SurrogateBotCommandScope(scope.type, scope.chatId)
            is BotCommandScopeChatMember -> SurrogateBotCommandScope(scope.type, scope.chatId, scope.userId)
            is BotCommandScopeChat -> SurrogateBotCommandScope(scope.type, scope.chatId)
        }
    }
}

@Serializable(BotCommandScopeSerializer::class)
sealed interface BotCommandScope {
    val type: String

    companion object {
        val Default = BotCommandScopeDefault
        val AllPrivateChats = BotCommandScopeAllPrivateChats
        val AllGroupChats = BotCommandScopeAllGroupChats
        val AllChatAdministrators = BotCommandScopeAllChatAdministrators

        fun ChatAdministrators(chatId: ChatIdentifier) = BotCommandScopeChatAdministrators(chatId)

        fun Chat(chatId: ChatIdentifier) = BotCommandScopeChat(chatId)

        fun ChatMember(
            chatId: ChatIdentifier,
            userId: UserId,
        ) = BotCommandScopeChatMember(chatId, userId)
    }
}

@Serializable
data class UnknownBotCommandScope internal constructor(
    override val type: String,
) : BotCommandScope

@Serializable
object BotCommandScopeDefault : BotCommandScope {
    @Required
    @EncodeDefault
    override val type: String = "default"
}

@Serializable
object BotCommandScopeAllPrivateChats : BotCommandScope {
    @Required
    @EncodeDefault
    override val type: String = "all_private_chats"
}

@Serializable
object BotCommandScopeAllGroupChats : BotCommandScope {
    @Required
    @EncodeDefault
    override val type: String = "all_group_chats"
}

@Serializable
object BotCommandScopeAllChatAdministrators : BotCommandScope {
    @Required
    @EncodeDefault
    override val type: String = "all_chat_administrators"
}

sealed interface ChatBotCommandScope : BotCommandScope {
    val chatId: ChatIdentifier
}

@Serializable
data class BotCommandScopeChatAdministrators(
    override val chatId: ChatIdentifier,
) : ChatBotCommandScope {
    @Required
    @EncodeDefault
    override val type: String = BotCommandScopeChatAdministrators.type

    companion object {
        const val type = "chat_administrators"
    }
}

@Serializable
data class BotCommandScopeChat(
    override val chatId: ChatIdentifier,
) : ChatBotCommandScope {
    @Required
    @EncodeDefault
    override val type: String = BotCommandScopeChat.type

    companion object {
        const val type = "chat"
    }
}

@Serializable
data class BotCommandScopeChatMember(
    override val chatId: ChatIdentifier,
    val userId: UserId,
) : ChatBotCommandScope {
    @Required
    @EncodeDefault
    override val type: String = BotCommandScopeChatMember.type

    companion object {
        const val type = "chat_member"
    }
}

object BotCommandScopeSerializer : KSerializer<BotCommandScope> {
    override val descriptor: SerialDescriptor = SurrogateBotCommandScope.serializer().descriptor

    override fun deserialize(decoder: Decoder): BotCommandScope = SurrogateBotCommandScope.serializer().deserialize(decoder).asBotCommandScope()

    override fun serialize(
        encoder: Encoder,
        value: BotCommandScope,
    ) {
        SurrogateBotCommandScope.serializer().serialize(
            encoder,
            SurrogateBotCommandScope.from(value),
        )
    }
}
