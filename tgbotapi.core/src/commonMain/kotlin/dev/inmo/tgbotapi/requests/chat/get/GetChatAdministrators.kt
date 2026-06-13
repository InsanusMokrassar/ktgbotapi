package dev.inmo.tgbotapi.requests.chat.get

import dev.inmo.tgbotapi.abstracts.types.ChatRequest
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.chat.member.AdministratorChatMember
import dev.inmo.tgbotapi.types.chat.member.AdministratorChatMemberSerializer
import dev.inmo.tgbotapi.types.chatIdField
import dev.inmo.tgbotapi.types.returnBotsField
import kotlinx.serialization.*
import kotlinx.serialization.builtins.ListSerializer

private val chatMembersListSerializer = ListSerializer(
    AdministratorChatMemberSerializer
)

/**
 * Represents a request to retrieve a list of administrators in a chat.
 *
 * This class is used to get information about all administrators of a specific chat.
 * The response includes details about the administrators, such as their permissions.
 *
 * @property chatId Unique identifier for the target chat or username of the target supergroup or channel.
 * @property retrieveOtherBots (Serialized as `return_bots`) Optional flag indicating whether to include bot administrators other than the requesting bot.
 * @see ChatRequest
 * @see SimpleRequest
 */
@Serializable
data class GetChatAdministrators(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(returnBotsField)
    val retrieveOtherBots: Boolean? = null
): ChatRequest, SimpleRequest<List<AdministratorChatMember>> {
    override fun method(): String = "getChatAdministrators"
    override val resultDeserializer: DeserializationStrategy<List<AdministratorChatMember>>
        get() = chatMembersListSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
