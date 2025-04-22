package dev.inmo.tgbotapi.extensions.utils

import dev.inmo.tgbotapi.types.queries.callback.CallbackQuery
import dev.inmo.tgbotapi.types.queries.callback.InlineMessageIdDataCallbackQuery
import dev.inmo.tgbotapi.types.queries.callback.MessageDataCallbackQuery
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull

fun <T : CallbackQuery> Flow<T>.onlyMessageDataCallbackQueries() =
    mapNotNull {
        it as? MessageDataCallbackQuery
    }

fun <T : CallbackQuery> Flow<T>.onlyInlineMessageIdDataCallbackQueries() =
    mapNotNull {
        it as? InlineMessageIdDataCallbackQuery
    }
