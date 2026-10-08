package dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories

import dev.inmo.tgbotapi.types.MessageGenerationStopped

object ByChatMessageGenerationStoppedMarkerFactory : MarkerFactory<MessageGenerationStopped, Any> {
    override suspend fun invoke(data: MessageGenerationStopped) = data.chat.id
}
