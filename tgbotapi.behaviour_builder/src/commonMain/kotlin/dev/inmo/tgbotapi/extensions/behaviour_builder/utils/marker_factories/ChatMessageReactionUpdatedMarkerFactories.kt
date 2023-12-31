package dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories

import dev.inmo.tgbotapi.types.chat.ChatMessageReactionUpdated

object ByChatIdChatMessageReactionUpdatedMarkerFactory : MarkerFactory<ChatMessageReactionUpdated, Any> {
    override suspend fun invoke(data: ChatMessageReactionUpdated) = data.chat.id
}
