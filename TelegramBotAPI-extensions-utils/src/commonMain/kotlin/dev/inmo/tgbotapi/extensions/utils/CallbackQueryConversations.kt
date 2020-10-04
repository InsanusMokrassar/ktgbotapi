package dev.inmo.tgbotapi.extensions.utils

import com.github.insanusmokrassar.TelegramBotAPI.types.CallbackQuery.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull

fun <T : CallbackQuery> Flow<T>.onlyMessageDataCallbackQueries() = mapNotNull {
    it as? MessageDataCallbackQuery
}
fun <T : CallbackQuery> Flow<T>.onlyInlineMessageIdDataCallbackQueries() = mapNotNull {
    it as? InlineMessageIdDataCallbackQuery
}
