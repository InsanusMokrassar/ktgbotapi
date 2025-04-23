package dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories

import dev.inmo.tgbotapi.types.message.payments.PaidMediaPurchased

object ByUserPaidMediaPurchasedMarkerFactory : MarkerFactory<PaidMediaPurchased, Any> {
    override suspend fun invoke(data: PaidMediaPurchased) = data.user
}
