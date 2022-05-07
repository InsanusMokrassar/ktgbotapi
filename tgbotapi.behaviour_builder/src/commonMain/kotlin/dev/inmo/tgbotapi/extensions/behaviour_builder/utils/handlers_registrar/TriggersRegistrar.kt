package dev.inmo.tgbotapi.extensions.behaviour_builder.utils.handlers_registrar

import dev.inmo.tgbotapi.types.CallbackQuery.DataCallbackQuery
import dev.inmo.tgbotapi.types.CallbackQuery.MessageDataCallbackQuery

class TriggersHolder {
    val handleableCommandsHolder = HandleableRegexesHolder()
    val handleableCallbackQueriesDataHolder = HandleableCallbackBasedHolder<DataCallbackQuery>()
}
