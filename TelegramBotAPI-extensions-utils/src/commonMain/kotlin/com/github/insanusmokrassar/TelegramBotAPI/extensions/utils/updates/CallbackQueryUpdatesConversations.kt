package com.github.insanusmokrassar.TelegramBotAPI.extensions.utils.updates

import com.github.insanusmokrassar.TelegramBotAPI.types.CallbackQuery.*
import com.github.insanusmokrassar.TelegramBotAPI.types.update.CallbackQueryUpdate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull

fun Flow<CallbackQueryUpdate>.asDataCallbackQueryFlow() = mapNotNull {
    it.data as? DataCallbackQuery
}
fun Flow<CallbackQueryUpdate>.asGameShortNameCallbackQueryFlow() = mapNotNull {
    it.data as? GameShortNameCallbackQuery
}
fun Flow<CallbackQueryUpdate>.asUnknownCallbackQueryFlow() = mapNotNull {
    it.data as? UnknownCallbackQueryType
}
