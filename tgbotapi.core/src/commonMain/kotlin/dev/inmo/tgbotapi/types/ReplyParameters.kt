package dev.inmo.tgbotapi.types

import dev.inmo.tgbotapi.abstracts.TextedInput
import dev.inmo.tgbotapi.abstracts.WithMessageId
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.RawMessageEntity
import dev.inmo.tgbotapi.types.message.abstracts.Message
import dev.inmo.tgbotapi.types.message.asTextSources
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.utils.extensions.makeSourceString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@ConsistentCopyVisibility
@Serializable
data class ReplyParameters internal constructor(
    @SerialName(chatIdField)
    val chatIdentifier: ChatIdentifier,
    @SerialName(messageIdField)
    override val messageId: MessageId,
    @SerialName(allowSendingWithoutReplyField)
    val allowSendingWithoutReply: Boolean? = null,
    @SerialName(quoteField)
    val quote: String? = null,
    @SerialName(quoteParseModeField)
    val quoteParseMode: ParseMode? = null,
    @SerialName(quoteEntitiesField)
    private val quoteEntities: List<RawMessageEntity>? = null,
    @SerialName(quotePositionField)
    val quotePosition: Int?
) : WithMessageId, TextedInput {
    override val text: String?
        get() = quote
    override val textSources: List<TextSource> by lazy {
        quoteEntities ?.asTextSources(quote ?: return@lazy emptyList()) ?: emptyList()
    }

    constructor(
        chatIdentifier: ChatIdentifier,
        messageId: MessageId,
        entities: TextSourcesList,
        allowSendingWithoutReply: Boolean? = null,
        quotePosition: Int? = null
    ) : this(
        chatIdentifier,
        messageId,
        allowSendingWithoutReply,
        entities.makeSourceString(),
        null,
        entities.toRawMessageEntities(),
        quotePosition
    )
    constructor(
        metaInfo: Message.MetaInfo,
        entities: TextSourcesList,
        allowSendingWithoutReply: Boolean? = null,
        quotePosition: Int? = null
    ) : this(
        metaInfo.chatId,
        metaInfo.messageId,
        entities,
        allowSendingWithoutReply,
        quotePosition
    )
    constructor(
        message: Message,
        entities: TextSourcesList,
        allowSendingWithoutReply: Boolean? = null,
        quotePosition: Int? = null
    ) : this(
        message.metaInfo,
        entities,
        allowSendingWithoutReply,
        quotePosition
    )
    constructor(
        chatIdentifier: ChatIdentifier,
        messageId: MessageId,
        quote: String,
        quoteParseMode: ParseMode,
        allowSendingWithoutReply: Boolean? = null,
        quotePosition: Int? = null
    ) : this(
        chatIdentifier,
        messageId,
        allowSendingWithoutReply,
        quote,
        quoteParseMode,
        null,
        quotePosition
    )
    constructor(
        metaInfo: Message.MetaInfo,
        quote: String,
        quoteParseMode: ParseMode,
        allowSendingWithoutReply: Boolean? = null,
        quotePosition: Int? = null
    ) : this(
        metaInfo.chatId,
        metaInfo.messageId,
        quote,
        quoteParseMode,
        allowSendingWithoutReply,
        quotePosition
    )
    constructor(
        message: Message,
        quote: String,
        quoteParseMode: ParseMode,
        allowSendingWithoutReply: Boolean? = null,
        quotePosition: Int? = null
    ) : this(
        message.metaInfo,
        quote,
        quoteParseMode,
        allowSendingWithoutReply,
        quotePosition
    )
    constructor(
        chatIdentifier: ChatIdentifier,
        messageId: MessageId,
        allowSendingWithoutReply: Boolean? = null,
        quotePosition: Int? = null
    ) : this(
        chatIdentifier,
        messageId,
        allowSendingWithoutReply,
        null,
        null,
        null,
        quotePosition
    )
    constructor(
        metaInfo: Message.MetaInfo,
        allowSendingWithoutReply: Boolean? = null,
        quotePosition: Int? = null
    ) : this(
        metaInfo.chatId,
        metaInfo.messageId,
        allowSendingWithoutReply,
        quotePosition
    )
    constructor(
        message: Message,
        allowSendingWithoutReply: Boolean? = null,
        quotePosition: Int? = null
    ) : this(
        message.metaInfo,
        allowSendingWithoutReply,
        quotePosition
    )
}
