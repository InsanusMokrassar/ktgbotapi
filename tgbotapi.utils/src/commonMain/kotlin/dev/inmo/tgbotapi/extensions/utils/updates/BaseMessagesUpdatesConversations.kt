package dev.inmo.tgbotapi.extensions.utils.updates

import dev.inmo.tgbotapi.types.update.abstracts.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance

fun Flow<Update>.onlyBaseMessageUpdates(): Flow<BaseMessageUpdate> = filterIsInstance()

/**
 * Converts flow to [Flow] of [BaseSentMessageUpdate]
 */
fun Flow<BaseMessageUpdate>.onlySentMessageUpdates(): Flow<BaseSentMessageUpdate> = filterIsInstance()

/**
 * Converts flow to [Flow] of [BaseSentMessageUpdate]
 */
fun Flow<BaseMessageUpdate>.onlyEditMessageUpdates(): Flow<BaseEditMessageUpdate> = filterIsInstance()
