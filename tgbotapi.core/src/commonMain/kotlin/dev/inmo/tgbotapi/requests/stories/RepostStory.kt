package dev.inmo.tgbotapi.requests.stories

import dev.inmo.tgbotapi.abstracts.types.WithBusinessConnectionId
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.StoryId
import dev.inmo.tgbotapi.types.businessConnectionIdField
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.fromBusinessConnectionIdField
import dev.inmo.tgbotapi.types.storyIdField
import dev.inmo.tgbotapi.types.stories.Story
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy

@Serializable
data class RepostStory(
    @SerialName(businessConnectionIdField)
    override val businessConnectionId: BusinessConnectionId,
    @SerialName(fromBusinessConnectionIdField)
    val fromBusinessConnectionId: BusinessConnectionId,
    @SerialName(storyIdField)
    val storyId: StoryId,
) : SimpleRequest<Story>, WithBusinessConnectionId {
    override fun method(): String = "repostStory"

    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
    override val resultDeserializer: DeserializationStrategy<Story>
        get() = Story.serializer()
}
