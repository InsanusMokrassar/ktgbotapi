package dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories

import dev.inmo.tgbotapi.types.boosts.ChatBoostUpdated

object ByIdChatBoostUpdatedMarkerFactory : MarkerFactory<ChatBoostUpdated, Any> {
    override suspend fun invoke(data: ChatBoostUpdated) = data.chat.id
}
