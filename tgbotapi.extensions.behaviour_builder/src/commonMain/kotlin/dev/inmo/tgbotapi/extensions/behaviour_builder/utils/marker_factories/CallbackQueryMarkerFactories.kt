package dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories

import dev.inmo.tgbotapi.types.CallbackQuery.CallbackQuery

object ByUserCallbackQueryMarkerFactory : MarkerFactory<CallbackQuery, Any> {
    override suspend fun invoke(data: CallbackQuery) = data.user
}

object ByIdCallbackQueryMarkerFactory : MarkerFactory<CallbackQuery, Any> {
    override suspend fun invoke(data: CallbackQuery) = data.id
}

object ByChatInstanceCallbackQueryMarkerFactory : MarkerFactory<CallbackQuery, Any> {
    override suspend fun invoke(data: CallbackQuery) = data.chatInstance
}


