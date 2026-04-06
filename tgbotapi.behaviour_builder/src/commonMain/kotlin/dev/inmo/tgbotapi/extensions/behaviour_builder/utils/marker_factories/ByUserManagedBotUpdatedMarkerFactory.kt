package dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories

import dev.inmo.tgbotapi.types.managed_bots.ManagedBotUpdated

object ByUserManagedBotUpdatedMarkerFactory : MarkerFactory<ManagedBotUpdated, Any> {
    override suspend fun invoke(data: ManagedBotUpdated) = data.user
}
