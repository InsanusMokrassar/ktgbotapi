package dev.inmo.tgbotapi.extensions.utils.updates

import dev.inmo.tgbotapi.types.queries.callback.*
import dev.inmo.tgbotapi.types.update.CallbackQueryUpdate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull

/**
 * @return New [Flow] with [DataCallbackQuery] type, got from [CallbackQueryUpdate.data] field
 */
fun Flow<CallbackQueryUpdate>.asDataCallbackQueryFlow() = mapNotNull {
    it.data as? DataCallbackQuery
}
/**
 * @return New [Flow] with [GameShortNameCallbackQuery] type, got from [CallbackQueryUpdate.data] field
 */
fun Flow<CallbackQueryUpdate>.asGameShortNameCallbackQueryFlow() = mapNotNull {
    it.data as? GameShortNameCallbackQuery
}
/**
 * @return New [Flow] with [UnknownCallbackQueryType] type, got from [CallbackQueryUpdate.data] field
 */
fun Flow<CallbackQueryUpdate>.asUnknownCallbackQueryFlow() = mapNotNull {
    it.data as? UnknownCallbackQueryType
}
