package dev.inmo.tgbotapi.requests.send

import dev.inmo.tgbotapi.abstracts.TextedOutput
import dev.inmo.tgbotapi.abstracts.WithCustomizableCaption
import dev.inmo.tgbotapi.abstracts.types.*
import dev.inmo.tgbotapi.requests.send.abstracts.SendChatMessageRequest
import dev.inmo.tgbotapi.requests.send.abstracts.OptionallyMessageThreadRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent.InputWidgetMessageContent
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.message.*
import dev.inmo.tgbotapi.types.message.abstracts.ChatContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import dev.inmo.tgbotapi.types.message.content.WidgetContent
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.widgets.*
import kotlinx.serialization.*

/** Sends a message containing an HTML widget. */
@ConsistentCopyVisibility
@Serializable
data class SendWidget private constructor(
    @SerialName(chatIdField) override val chatId: ChatIdentifier,
    @SerialName(htmlField) val html: String,
    @SerialName(widthField) val width: Int? = null,
    @SerialName(heightField) val height: Int? = null,
    @SerialName(publicDataField) val publicData: String? = null,
    @SerialName(chatDataField) val chatData: String? = null,
    @SerialName(captionField) override val text: String? = null,
    @SerialName(parseModeField) override val parseMode: ParseMode? = null,
    @SerialName(captionEntitiesField) private val rawEntities: List<RawMessageEntity>? = null,
    @SerialName(showCaptionAboveMediaField) override val showCaptionAboveMedia: Boolean = false,
    @SerialName(allowQueriesField) val allowQueries: Boolean = false,
    @SerialName(isSharedField) val isShared: Boolean = false,
    @OptIn(ExperimentalSerializationApi::class)
    @EncodeDefault
    @SerialName(messageThreadIdField) override val threadId: MessageThreadId? = chatId.threadId,
    @SerialName(disableNotificationField) override val disableNotification: Boolean = false,
    @SerialName(protectContentField) override val protectContent: Boolean = false,
    @SerialName(allowPaidBroadcastField) override val allowPaidBroadcast: Boolean = false,
    @SerialName(messageEffectIdField) override val effectId: EffectId? = null,
    @SerialName(replyParametersField) override val replyParameters: ReplyParameters? = null,
    @SerialName(replyMarkupField) override val replyMarkup: KeyboardMarkup? = null
) : SendChatMessageRequest<ChatContentMessage<WidgetContent>>, ChatRequest, TextedOutput, WithCustomizableCaption,
    OptionallyMessageThreadRequest, DisableNotification, ProtectContent, AllowPaidBroadcast, OptionallyWithEffectId,
    WithReplyParameters, WithReplyMarkup {
    constructor(
        chatId: ChatIdentifier,
        widget: InputWidgetMessageContent,
        threadId: MessageThreadId? = chatId.threadId,
        disableNotification: Boolean = false,
        protectContent: Boolean = false,
        allowPaidBroadcast: Boolean = false,
        effectId: EffectId? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: KeyboardMarkup? = null
    ) : this(
        chatId = chatId,
        html = widget.html,
        width = widget.width,
        height = widget.height,
        publicData = widget.publicData,
        chatData = widget.chatData,
        text = widget.text,
        parseMode = widget.parseMode,
        rawEntities = widget.rawEntities,
        showCaptionAboveMedia = widget.showCaptionAboveMedia,
        allowQueries = widget.allowQueries,
        isShared = widget.isShared,
        threadId = threadId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    override val textSources: TextSourcesList?
        get() = text?.let { rawEntities?.asTextSources(it) }

    override fun method(): String = "sendWidget"
    override val requestSerializer: SerializationStrategy<*> get() = serializer()
    override val resultDeserializer: DeserializationStrategy<ChatContentMessage<WidgetContent>>
        get() = widgetMessageDeserializer
}


/** Sends a widget with an optional caption and parse mode. */
fun SendWidget(
    chatId: ChatIdentifier,
    html: String,
    width: Int? = null,
    height: Int? = null,
    publicData: String? = null,
    chatData: String? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    allowQueries: Boolean = false,
    isShared: Boolean = false,
    threadId: MessageThreadId? = chatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): SendWidget = SendWidget(
    chatId = chatId,
    widget = InputWidgetMessageContent(
        html = html,
        width = width,
        height = height,
        publicData = publicData,
        chatData = chatData,
        text = text,
        parseMode = parseMode,
        showCaptionAboveMedia = showCaptionAboveMedia,
        allowQueries = allowQueries,
        isShared = isShared
    ),
    threadId = threadId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)

/** Sends a widget with a caption assembled from text entities. */
fun SendWidget(
    chatId: ChatIdentifier,
    html: String,
    entities: TextSourcesList,
    width: Int? = null,
    height: Int? = null,
    publicData: String? = null,
    chatData: String? = null,
    showCaptionAboveMedia: Boolean = false,
    allowQueries: Boolean = false,
    isShared: Boolean = false,
    threadId: MessageThreadId? = chatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): SendWidget = SendWidget(
    chatId = chatId,
    widget = InputWidgetMessageContent(
        html = html,
        entities = entities,
        width = width,
        height = height,
        publicData = publicData,
        chatData = chatData,
        showCaptionAboveMedia = showCaptionAboveMedia,
        allowQueries = allowQueries,
        isShared = isShared
    ),
    threadId = threadId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)

internal val widgetMessageDeserializer: DeserializationStrategy<ChatContentMessage<WidgetContent>> =
    TelegramBotAPIMessageDeserializationStrategyClass()
