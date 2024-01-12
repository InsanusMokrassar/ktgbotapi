package dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories

import dev.inmo.tgbotapi.types.boosts.ChatBoostUpdated
import dev.inmo.tgbotapi.types.polls.PollAnswer

object ByIdChatBoostUpdatedMarkerFactory : MarkerFactory<ChatBoostUpdated, Any> {
    override suspend fun invoke(data: ChatBoostUpdated) = data.chat.id
}
