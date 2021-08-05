package dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories

import dev.inmo.tgbotapi.types.message.abstracts.*

object ByChatMessageMarkerFactory : MarkerFactory<Message, Any> {
    override suspend fun invoke(data: Message) = data.chat
}

object ByUserMessageMarkerFactory : MarkerFactory<Message, Any> {
    override suspend fun invoke(data: Message) = when (data) {
        is FromUserMessage -> data.user
        is FromChannelGroupContentMessage<*> -> data.channel
        else -> data.chat // including anonymous
    }
}
