package dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories

import dev.inmo.tgbotapi.types.InlineQueries.query.InlineQuery

object ByUserInlineQueryMarkerFactory : MarkerFactory<InlineQuery, Any> {
    override suspend fun invoke(data: InlineQuery) = data.from
}

object ByIdInlineQueryMarkerFactory : MarkerFactory<InlineQuery, Any> {
    override suspend fun invoke(data: InlineQuery) = data.id
}
