package dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories

import dev.inmo.tgbotapi.types.business_connection.BusinessMessagesDeleted

object ByBusinessConnectionIdBusinessMessagesDeletedMarkerFactory : MarkerFactory<BusinessMessagesDeleted, Any> {
    override suspend fun invoke(data: BusinessMessagesDeleted) = data.businessConnectionId
}
