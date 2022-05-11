package dev.inmo.tgbotapi.extensions.behaviour_builder.utils.handlers_registrar

import dev.inmo.tgbotapi.types.queries.callback.DataCallbackQuery

class TriggersHolder {
    val handleableCommandsHolder = HandleableRegexesHolder()
    val handleableCallbackQueriesDataHolder = HandleableCallbackBasedHolder<DataCallbackQuery>()
}
