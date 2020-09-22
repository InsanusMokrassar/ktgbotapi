package com.github.insanusmokrassar.TelegramBotAPI.extensions.utils.internal_utils

import com.github.insanusmokrassar.TelegramBotAPI.types.UpdateIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.Update
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull

internal inline fun <reified T : Any, UT : Update> Flow<UT>.onlySpecifiedTypeOfDataWithUpdates(): Flow<Pair<UpdateIdentifier, T>> {
    return mapNotNull {
        it.updateId to (it.data as? T ?: return@mapNotNull null)
    }
}

internal inline fun <reified T : Any, UT : Update> Flow<UT>.onlySpecifiedTypeOfData(): Flow<T> {
    return mapNotNull { it as? T }
}
