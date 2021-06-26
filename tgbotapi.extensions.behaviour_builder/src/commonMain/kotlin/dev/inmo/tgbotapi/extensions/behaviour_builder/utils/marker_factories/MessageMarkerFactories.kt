package dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories

import dev.inmo.tgbotapi.types.message.abstracts.Message

object ByChatMessageMarkerFactory : MarkerFactory<Message, Any> {
    override suspend fun invoke(data: Message) = data.chat
}
