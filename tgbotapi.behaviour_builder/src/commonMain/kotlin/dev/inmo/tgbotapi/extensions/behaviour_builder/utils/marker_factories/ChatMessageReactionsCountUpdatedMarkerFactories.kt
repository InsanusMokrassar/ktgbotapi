package dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories

import dev.inmo.tgbotapi.types.chat.ChatMessageReactionsCountUpdated

object ByChatIdChatMessageReactionsCountUpdatedMarkerFactory : MarkerFactory<ChatMessageReactionsCountUpdated, Any> {
    override suspend fun invoke(data: ChatMessageReactionsCountUpdated) = data.chat.id
}
