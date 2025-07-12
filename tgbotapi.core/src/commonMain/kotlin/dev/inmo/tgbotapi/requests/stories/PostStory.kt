package dev.inmo.tgbotapi.requests.stories

import dev.inmo.tgbotapi.abstracts.TextedOutput
import dev.inmo.tgbotapi.requests.abstracts.BusinessRequest
import dev.inmo.tgbotapi.requests.abstracts.MultipartFile
import dev.inmo.tgbotapi.types.Seconds
import dev.inmo.tgbotapi.types.activePeriodField
import dev.inmo.tgbotapi.types.areasField
import dev.inmo.tgbotapi.types.businessConnectionIdField
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.captionEntitiesField
import dev.inmo.tgbotapi.types.captionField
import dev.inmo.tgbotapi.types.contentField
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.RawMessageEntity
import dev.inmo.tgbotapi.types.message.asTextSources
import dev.inmo.tgbotapi.types.message.parseModeField
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.types.postToChatPageField
import dev.inmo.tgbotapi.types.protectContentField
import dev.inmo.tgbotapi.types.stories.InputStoryContent
import dev.inmo.tgbotapi.types.stories.Story
import dev.inmo.tgbotapi.types.stories.StoryArea
import dev.inmo.tgbotapi.utils.extensions.makeString
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy

@ConsistentCopyVisibility
@Serializable
data class PostStory private constructor(
    @SerialName(businessConnectionIdField)
    override val businessConnectionId: BusinessConnectionId,
    @SerialName(contentField)
    val content: InputStoryContent,
    @SerialName(activePeriodField)
    val activePeriod: Seconds,
    @SerialName(captionField)
    override val text: String? = null,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    @SerialName(captionEntitiesField)
    private val textEntities: List<RawMessageEntity>? = null,
    @SerialName(areasField)
    val areas: List<StoryArea> = emptyList(),
    @SerialName(postToChatPageField)
    val postToChatPage: Boolean = false,
    @SerialName(protectContentField)
    val protectContent: Boolean = false,
) : BusinessRequest.Multipart<Story>, TextedOutput {
    override val textSources: List<TextSource>? by lazy {
        textEntities ?.asTextSources(text ?: return@lazy null)
    }

    override val resultDeserializer: DeserializationStrategy<Story>
        get() = Story.serializer()
    override val mediaMap: Map<String, MultipartFile>
        get() = mapOf(content.media)
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    constructor(
        businessConnectionId: BusinessConnectionId,
        content: InputStoryContent,
        activePeriod: Seconds,
        textSources: List<TextSource>,
        areas: List<StoryArea> = emptyList(),
        postToChatPage: Boolean = false,
        protectContent: Boolean = false,
    ) : this(
        businessConnectionId = businessConnectionId,
        content = content,
        activePeriod = activePeriod,
        text = textSources.makeString(),
        parseMode = null,
        textEntities = textSources.toRawMessageEntities(),
        areas = areas,
        postToChatPage = postToChatPage,
        protectContent = protectContent
    )
    constructor(
        businessConnectionId: BusinessConnectionId,
        content: InputStoryContent,
        activePeriod: Seconds,
        text: String,
        parseMode: ParseMode? = null,
        areas: List<StoryArea> = emptyList(),
        postToChatPage: Boolean = false,
        protectContent: Boolean = false,
    ) : this(
        businessConnectionId = businessConnectionId,
        content = content,
        activePeriod = activePeriod,
        text = text,
        parseMode = parseMode,
        textEntities = null,
        areas = areas,
        postToChatPage = postToChatPage,
        protectContent = protectContent
    )
    constructor(
        businessConnectionId: BusinessConnectionId,
        content: InputStoryContent,
        activePeriod: Seconds,
        areas: List<StoryArea> = emptyList(),
        postToChatPage: Boolean = false,
        protectContent: Boolean = false,
    ) : this(
        businessConnectionId = businessConnectionId,
        content = content,
        activePeriod = activePeriod,
        text = null,
        parseMode = null,
        textEntities = null,
        areas = areas,
        postToChatPage = postToChatPage,
        protectContent = protectContent
    )

    override fun method(): String = "postStory"

    @Suppress("unused")
    companion object {
        const val ACTIVE_PERIOD_6_HOURS: Seconds = 6 * 3600
        const val ACTIVE_PERIOD_12_HOURS: Seconds = 12 * 3600
        const val ACTIVE_PERIOD_24_HOURS: Seconds = 24 * 3600 // 86400
        const val ACTIVE_PERIOD_48_HOURS: Seconds = 48 * 3600 // 2 * 86400
    }
}