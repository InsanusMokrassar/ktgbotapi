package dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent

import dev.inmo.tgbotapi.abstracts.TextedOutput
import dev.inmo.tgbotapi.abstracts.WithCustomizableCaption
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.message.*
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.widgets.*
import dev.inmo.tgbotapi.utils.extensions.makeString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** Shared widget to resend by identifier in an inline result or a send request. */
@ConsistentCopyVisibility
@Serializable
data class InputSharedWidgetMessageContent internal constructor(
    @SerialName(widgetIdField) val widgetId: WidgetId,
    @SerialName(chatDataField) val chatData: String? = null,
    @SerialName(captionField) override val text: String? = null,
    @SerialName(parseModeField) override val parseMode: ParseMode? = null,
    @SerialName(captionEntitiesField) internal val rawEntities: List<RawMessageEntity>? = null,
    @SerialName(showCaptionAboveMediaField) override val showCaptionAboveMedia: Boolean = false
) : InputMessageContent, TextedOutput, WithCustomizableCaption {
    override val textSources: TextSourcesList?
        get() = text?.let { rawEntities?.asTextSources(it) }

    /** Creates widget content with an optional caption and parse mode. */
    constructor(
        widgetId: WidgetId,
        chatData: String? = null,
        text: String? = null,
        parseMode: ParseMode? = null,
        showCaptionAboveMedia: Boolean = false
    ) : this(
        widgetId = widgetId,
        chatData = chatData,
        text = text,
        parseMode = parseMode,
        rawEntities = null,
        showCaptionAboveMedia = showCaptionAboveMedia
    )

    /** Creates widget content with a caption composed from Telegram text entities. */
    constructor(
        widgetId: WidgetId,
        entities: TextSourcesList,
        chatData: String? = null,
        showCaptionAboveMedia: Boolean = false
    ) : this(
        widgetId = widgetId,
        chatData = chatData,
        text = entities.makeString(),
        parseMode = null,
        rawEntities = entities.toRawMessageEntities(),
        showCaptionAboveMedia = showCaptionAboveMedia
    )
}
