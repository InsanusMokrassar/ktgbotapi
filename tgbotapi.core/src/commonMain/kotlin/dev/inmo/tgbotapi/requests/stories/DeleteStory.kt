package dev.inmo.tgbotapi.requests.stories

import dev.inmo.tgbotapi.abstracts.types.WithBusinessConnectionId
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.StoryId
import dev.inmo.tgbotapi.types.businessConnectionIdField
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.messageIdField
import dev.inmo.tgbotapi.types.storyIdField
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.builtins.serializer

@Serializable
data class DeleteStory(
    @SerialName(businessConnectionIdField)
    override val businessConnectionId: BusinessConnectionId,
    @SerialName(storyIdField)
    val storyId: StoryId,
) : SimpleRequest<Boolean>, WithBusinessConnectionId {
    override fun method(): String = "deleteStory"

    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()
}
