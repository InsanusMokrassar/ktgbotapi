package dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories

import dev.inmo.tgbotapi.types.payments.BotSubscriptionUpdated

object ByUserBotSubscriptionUpdatedMarkerFactory : MarkerFactory<BotSubscriptionUpdated, Any> {
    override suspend fun invoke(data: BotSubscriptionUpdated) = data.user
}
