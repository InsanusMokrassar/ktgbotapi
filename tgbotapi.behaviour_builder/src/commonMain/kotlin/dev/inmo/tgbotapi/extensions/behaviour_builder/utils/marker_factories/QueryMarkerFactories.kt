package dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories

import dev.inmo.tgbotapi.types.chat.ChatJoinRequest
import dev.inmo.tgbotapi.types.payments.PreCheckoutQuery
import dev.inmo.tgbotapi.types.payments.ShippingQuery
import dev.inmo.tgbotapi.types.queries.callback.CallbackQuery

object ByUserCallbackQueryMarkerFactory : MarkerFactory<CallbackQuery, Any> {
    override suspend fun invoke(data: CallbackQuery) = data.user
}

object ByChatChatJoinRequestMarkerFactory : MarkerFactory<ChatJoinRequest, Any> {
    override suspend fun invoke(data: ChatJoinRequest) = data.chat
}

object ByUserShippingQueryMarkerFactory : MarkerFactory<ShippingQuery, Any> {
    override suspend fun invoke(data: ShippingQuery) = data.user
}

object ByUserPreCheckoutQueryMarkerFactory : MarkerFactory<PreCheckoutQuery, Any> {
    override suspend fun invoke(data: PreCheckoutQuery) = data.user
}

object ByIdCallbackQueryMarkerFactory : MarkerFactory<CallbackQuery, Any> {
    override suspend fun invoke(data: CallbackQuery) = data.id
}

object ByChatInstanceCallbackQueryMarkerFactory : MarkerFactory<CallbackQuery, Any> {
    override suspend fun invoke(data: CallbackQuery) = data.chatInstance
}
