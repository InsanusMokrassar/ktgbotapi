package dev.inmo.tgbotapi.requests.stories

import dev.inmo.tgbotapi.abstracts.TextedOutput
import dev.inmo.tgbotapi.requests.abstracts.BusinessRequest
import dev.inmo.tgbotapi.requests.abstracts.MultipartFile
import dev.inmo.tgbotapi.requests.stories.PostStory
import dev.inmo.tgbotapi.types.Seconds
import dev.inmo.tgbotapi.types.StoryId
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
import dev.inmo.tgbotapi.types.storyIdField
import dev.inmo.tgbotapi.utils.extensions.makeString
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy

@Serializable
data class EditStory private constructor(
    @SerialName(businessConnectionIdField)
    override val businessConnectionId: BusinessConnectionId,
    @SerialName(storyIdField)
    val storyId: StoryId,
    @SerialName(contentField)
    val content: InputStoryContent,
    @SerialName(captionField)
    override val text: String? = null,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    @SerialName(captionEntitiesField)
    private val textEntities: List<RawMessageEntity>? = null,
    @SerialName(areasField)
    val areas: List<StoryArea> = emptyList(),
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
        storyId: StoryId,
        content: InputStoryContent,
        textSources: List<TextSource>,
        areas: List<StoryArea> = emptyList(),
    ) : this(
        businessConnectionId = businessConnectionId,
        storyId = storyId,
        content = content,
        text = textSources.makeString(),
        parseMode = null,
        textEntities = textSources.toRawMessageEntities(),
        areas = areas,
    )
    constructor(
        businessConnectionId: BusinessConnectionId,
        storyId: StoryId,
        content: InputStoryContent,
        text: String,
        parseMode: ParseMode? = null,
        areas: List<StoryArea> = emptyList(),
    ) : this(
        businessConnectionId = businessConnectionId,
        storyId = storyId,
        content = content,
        text = text,
        parseMode = parseMode,
        textEntities = null,
        areas = areas,
    )
    constructor(
        businessConnectionId: BusinessConnectionId,
        storyId: StoryId,
        content: InputStoryContent,
        areas: List<StoryArea> = emptyList(),
    ) : this(
        businessConnectionId = businessConnectionId,
        storyId = storyId,
        content = content,
        text = null,
        parseMode = null,
        textEntities = null,
        areas = areas,
    )
    override fun method(): String = "editStory"
}