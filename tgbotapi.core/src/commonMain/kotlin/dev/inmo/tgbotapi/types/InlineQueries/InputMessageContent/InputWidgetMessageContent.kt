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

/** HTML widget to create for an inline result or a send request. */
@ConsistentCopyVisibility
@Serializable
data class InputWidgetMessageContent internal constructor(
    @SerialName(htmlField) val html: String,
    @SerialName(widthField) val width: Int? = null,
    @SerialName(heightField) val height: Int? = null,
    @SerialName(publicDataField) val publicData: String? = null,
    @SerialName(chatDataField) val chatData: String? = null,
    @SerialName(captionField) override val text: String? = null,
    @SerialName(parseModeField) override val parseMode: ParseMode? = null,
    @SerialName(captionEntitiesField) internal val rawEntities: List<RawMessageEntity>? = null,
    @SerialName(showCaptionAboveMediaField) override val showCaptionAboveMedia: Boolean = false,
    @SerialName(allowQueriesField) val allowQueries: Boolean = false,
    @SerialName(isSharedField) val isShared: Boolean = false
) : InputMessageContent, TextedOutput, WithCustomizableCaption {
    override val textSources: TextSourcesList?
        get() = text?.let { rawEntities?.asTextSources(it) }

    /** Creates widget content with an optional caption and parse mode. */
    constructor(
        html: String,
        width: Int? = null,
        height: Int? = null,
        publicData: String? = null,
        chatData: String? = null,
        text: String? = null,
        parseMode: ParseMode? = null,
        showCaptionAboveMedia: Boolean = false,
        allowQueries: Boolean = false,
        isShared: Boolean = false
    ) : this(
        html = html,
        width = width,
        height = height,
        publicData = publicData,
        chatData = chatData,
        text = text,
        parseMode = parseMode,
        rawEntities = null,
        showCaptionAboveMedia = showCaptionAboveMedia,
        allowQueries = allowQueries,
        isShared = isShared
    )

    /** Creates widget content with a caption composed from Telegram text entities. */
    constructor(
        html: String,
        entities: TextSourcesList,
        width: Int? = null,
        height: Int? = null,
        publicData: String? = null,
        chatData: String? = null,
        showCaptionAboveMedia: Boolean = false,
        allowQueries: Boolean = false,
        isShared: Boolean = false
    ) : this(
        html = html,
        width = width,
        height = height,
        publicData = publicData,
        chatData = chatData,
        text = entities.makeString(),
        parseMode = null,
        rawEntities = entities.toRawMessageEntities(),
        showCaptionAboveMedia = showCaptionAboveMedia,
        allowQueries = allowQueries,
        isShared = isShared
    )
}
