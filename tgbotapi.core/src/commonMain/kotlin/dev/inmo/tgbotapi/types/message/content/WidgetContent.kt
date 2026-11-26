package dev.inmo.tgbotapi.types.message.content

import dev.inmo.tgbotapi.abstracts.WithCustomizableCaption
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.requests.send.SendSharedWidget
import dev.inmo.tgbotapi.requests.send.SendWidget
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent.InputSharedWidgetMessageContent
import dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent.InputWidgetMessageContent
import dev.inmo.tgbotapi.types.abstracts.WithOptionalQuoteInfo
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.message.SuggestedPostParameters
import dev.inmo.tgbotapi.types.message.abstracts.ChatContentMessage
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.types.widgets.Widget
import kotlinx.serialization.Serializable

/**
 * A received widget and its message caption. A resend reuses the identifier of a shared widget.
 * For an unshared widget, [SendWidget] allows explicitly enabling queries when recreating the HTML.
 */
@Serializable
data class WidgetContent(
    val widget: Widget,
    override val text: String? = null,
    override val textSources: TextSourcesList = emptyList(),
    override val quote: TextQuote? = null,
    override val showCaptionAboveMedia: Boolean = false
) : TextedContent, WithCustomizableCaption, WithOptionalQuoteInfo {
    override fun createResend(
        chatId: ChatIdentifier,
        messageThreadId: MessageThreadId?,
        directMessageThreadId: DirectMessageThreadId?,
        businessConnectionId: BusinessConnectionId?,
        disableNotification: Boolean,
        protectContent: Boolean,
        allowPaidBroadcast: Boolean,
        effectId: EffectId?,
        suggestedPostParameters: SuggestedPostParameters?,
        replyParameters: ReplyParameters?,
        replyMarkup: KeyboardMarkup?
    ): Request<ChatContentMessage<WidgetContent>> {
        val entities = textSources.takeIf { it.isNotEmpty() }?.toRawMessageEntities()
        val id = widget.id
        return if (id != null) {
            SendSharedWidget(
                chatId = chatId,
                widget = InputSharedWidgetMessageContent(
                    widgetId = id,
                    chatData = widget.chatData,
                    text = text,
                    rawEntities = entities,
                    showCaptionAboveMedia = showCaptionAboveMedia
                ),
                threadId = messageThreadId,
                disableNotification = disableNotification,
                protectContent = protectContent,
                allowPaidBroadcast = allowPaidBroadcast,
                effectId = effectId,
                replyParameters = replyParameters,
                replyMarkup = replyMarkup
            )
        } else {
            SendWidget(
                chatId = chatId,
                widget = InputWidgetMessageContent(
                    html = widget.html,
                    width = widget.width,
                    height = widget.height,
                    publicData = widget.publicData,
                    chatData = widget.chatData,
                    text = text,
                    rawEntities = entities,
                    showCaptionAboveMedia = showCaptionAboveMedia
                ),
                threadId = messageThreadId,
                disableNotification = disableNotification,
                protectContent = protectContent,
                allowPaidBroadcast = allowPaidBroadcast,
                effectId = effectId,
                replyParameters = replyParameters,
                replyMarkup = replyMarkup
            )
        }
    }
}
