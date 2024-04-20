package dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories

import dev.inmo.tgbotapi.types.boosts.ChatBoostUpdated
import dev.inmo.tgbotapi.types.business_connection.BusinessConnection


object ByUserBusinessConnectionUpdatedMarkerFactory : MarkerFactory<BusinessConnection, Any> {
    override suspend fun invoke(data: BusinessConnection) = data.user
}
