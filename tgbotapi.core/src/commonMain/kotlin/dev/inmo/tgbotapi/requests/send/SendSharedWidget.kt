package dev.inmo.tgbotapi.requests.send

import dev.inmo.tgbotapi.abstracts.TextedOutput
import dev.inmo.tgbotapi.abstracts.WithCustomizableCaption
import dev.inmo.tgbotapi.abstracts.types.*
import dev.inmo.tgbotapi.requests.send.abstracts.SendChatMessageRequest
import dev.inmo.tgbotapi.requests.send.abstracts.OptionallyMessageThreadRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent.InputSharedWidgetMessageContent
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.message.*
import dev.inmo.tgbotapi.types.message.abstracts.ChatContentMessage
import dev.inmo.tgbotapi.types.message.content.WidgetContent
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.widgets.*
import kotlinx.serialization.*

/** Resends a shared widget created by the bot. */
@ConsistentCopyVisibility
@Serializable
data class SendSharedWidget private constructor(
    @SerialName(chatIdField) override val chatId: ChatIdentifier,
    @SerialName(widgetIdField) val widgetId: WidgetId,
    @SerialName(chatDataField) val chatData: String? = null,
    @SerialName(captionField) override val text: String? = null,
    @SerialName(parseModeField) override val parseMode: ParseMode? = null,
    @SerialName(captionEntitiesField) private val rawEntities: List<RawMessageEntity>? = null,
    @SerialName(showCaptionAboveMediaField) override val showCaptionAboveMedia: Boolean = false,
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
        widget: InputSharedWidgetMessageContent,
        threadId: MessageThreadId? = chatId.threadId,
        disableNotification: Boolean = false,
        protectContent: Boolean = false,
        allowPaidBroadcast: Boolean = false,
        effectId: EffectId? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: KeyboardMarkup? = null
    ) : this(
        chatId = chatId,
        widgetId = widget.widgetId,
        chatData = widget.chatData,
        text = widget.text,
        parseMode = widget.parseMode,
        rawEntities = widget.rawEntities,
        showCaptionAboveMedia = widget.showCaptionAboveMedia,
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

    override fun method(): String = "sendSharedWidget"
    override val requestSerializer: SerializationStrategy<*> get() = serializer()
    override val resultDeserializer: DeserializationStrategy<ChatContentMessage<WidgetContent>>
        get() = widgetMessageDeserializer
}


/** Sends a widget with an optional caption and parse mode. */
fun SendSharedWidget(
    chatId: ChatIdentifier,
    widgetId: WidgetId,
    chatData: String? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    threadId: MessageThreadId? = chatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): SendSharedWidget = SendSharedWidget(
    chatId = chatId,
    widget = InputSharedWidgetMessageContent(
        widgetId = widgetId,
        chatData = chatData,
        text = text,
        parseMode = parseMode,
        showCaptionAboveMedia = showCaptionAboveMedia
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
fun SendSharedWidget(
    chatId: ChatIdentifier,
    widgetId: WidgetId,
    entities: TextSourcesList,
    chatData: String? = null,
    showCaptionAboveMedia: Boolean = false,
    threadId: MessageThreadId? = chatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): SendSharedWidget = SendSharedWidget(
    chatId = chatId,
    widget = InputSharedWidgetMessageContent(
        widgetId = widgetId,
        entities = entities,
        chatData = chatData,
        showCaptionAboveMedia = showCaptionAboveMedia
    ),
    threadId = threadId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)
