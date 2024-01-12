package dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories

import dev.inmo.tgbotapi.types.boosts.ChatBoostRemoved
import dev.inmo.tgbotapi.types.boosts.ChatBoostUpdated
import dev.inmo.tgbotapi.types.polls.PollAnswer

object ByIdChatBoostRemovedMarkerFactory : MarkerFactory<ChatBoostRemoved, Any> {
    override suspend fun invoke(data: ChatBoostRemoved) = data.chat.id
}
