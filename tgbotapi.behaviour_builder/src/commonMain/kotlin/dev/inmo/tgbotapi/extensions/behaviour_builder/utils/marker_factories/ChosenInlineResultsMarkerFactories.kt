package dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories

import dev.inmo.tgbotapi.types.InlineQueries.ChosenInlineResult.ChosenInlineResult

object ByUserIdChosenInlineResultMarkerFactory : MarkerFactory<ChosenInlineResult, Any> {
    override suspend fun invoke(data: ChosenInlineResult) = data.user.id
}
