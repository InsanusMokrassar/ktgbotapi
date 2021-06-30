package dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories

import dev.inmo.tgbotapi.extensions.utils.shortcuts.chat
import dev.inmo.tgbotapi.types.message.abstracts.MediaGroupMessage

object ByChatMediaGroupMarkerFactory : MarkerFactory<List<MediaGroupMessage<*>>, Any> {
    override suspend fun invoke(data: List<MediaGroupMessage<*>>) = data.chat ?: error("Data must not be empty")
}
