package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity

import com.github.insanusmokrassar.TelegramBotAPI.types.User
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.PrivateChat
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.ArrayListSerializer

@Serializable
internal data class RawMessageEntity(
    val type: String,
    val offset: Int,
    val length: Int,
    val url: String? = null,
    val user: User? = null
) {
    fun asMessageEntity(source: String): MessageEntity {
        val sourceSubstring = source.substring(offset, offset + length)
        return when (type) {
            "mention" -> MentionMessageEntity(offset, length, sourceSubstring)
            "hashtag" -> HashTagMessageEntity(offset, length, sourceSubstring)
            "cashtag" -> TODO()
            "bot_command" -> BotCommandMessageEntity(offset, length, sourceSubstring)
            "url" -> URLMessageEntity(offset, length, sourceSubstring)
            "email" -> EMailMessageEntity(offset, length, sourceSubstring)
            "phone_number" -> PhoneNumberMessageEntity(offset, length, sourceSubstring)
            "bold" -> BoldTextMessageEntity(offset, length, sourceSubstring)
            "italic" -> ItalicTextMessageEntity(offset, length, sourceSubstring)
            "code" -> CodeTextMessageEntity(offset, length, sourceSubstring)
            "pre" -> PreTextMessageEntity(offset, length, sourceSubstring)
            "text_link" -> TextLinkMessageEntity(offset, length, sourceSubstring, url ?: throw IllegalStateException("URL must not be null for text link"))
            "text_mention" -> TextMentionMessageEntity(offset, length, sourceSubstring, user ?: throw IllegalStateException("User must not be null for text mention"))
            "underline" -> UnderlineMessageEntity(offset, length, sourceSubstring)
            "strikethrough" -> StrikethroughMessageEntity(offset, length, sourceSubstring)
            else -> throw IllegalArgumentException("Unknown type of message entity")
        }
    }
}

internal typealias RawMessageEntities = List<RawMessageEntity>

internal object RawMessageEntitiesSerializer : KSerializer<List<RawMessageEntity>> by ArrayListSerializer(
    RawMessageEntity.serializer()
)
