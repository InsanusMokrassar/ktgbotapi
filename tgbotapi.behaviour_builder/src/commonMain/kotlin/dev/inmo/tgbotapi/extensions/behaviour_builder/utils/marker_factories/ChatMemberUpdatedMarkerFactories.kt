package dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories

import dev.inmo.tgbotapi.types.ChatMemberUpdated

object ByChatChatMemberUpdatedMarkerFactory : MarkerFactory<ChatMemberUpdated, Any> {
    override suspend fun invoke(data: ChatMemberUpdated) = data.chat
}
