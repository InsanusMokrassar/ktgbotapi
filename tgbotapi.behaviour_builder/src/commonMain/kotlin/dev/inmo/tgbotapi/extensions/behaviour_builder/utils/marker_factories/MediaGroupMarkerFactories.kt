package dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories

import dev.inmo.tgbotapi.extensions.utils.shortcuts.chat

object ByChatMediaGroupMarkerFactory : MarkerFactory<List<MediaGroupMessage<*>>, Any> {
    override suspend fun invoke(data: List<MediaGroupMessage<*>>) = data.chat ?: error("Data must not be empty")
}
