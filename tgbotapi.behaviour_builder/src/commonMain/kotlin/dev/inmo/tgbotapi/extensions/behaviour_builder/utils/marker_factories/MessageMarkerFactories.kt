package dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories

import dev.inmo.tgbotapi.types.message.abstracts.*

object ByChatMessageMarkerFactory : MarkerFactory<AccessibleMessage, Any> {
    override suspend fun invoke(data: AccessibleMessage) = data.chat
}

object ByUserMessageMarkerFactory : MarkerFactory<AccessibleMessage, Any> {
    override suspend fun invoke(data: AccessibleMessage) = when (data) {
        is FromUserMessage -> data.user
        is FromChannelGroupContentMessage<*> -> data.channel
        else -> data.chat // including anonymous
    }
}
