package com.github.insanusmokrassar.TelegramBotAPI.extensions.utils.updates

import com.github.insanusmokrassar.TelegramBotAPI.types.update.MediaGroupUpdates.*
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.*
import kotlinx.coroutines.flow.*

fun Flow<Update>.onlyBaseMessageUpdates(): Flow<BaseMessageUpdate> = filterIsInstance()

/**
 * Converts flow to [Flow] of [BaseSentMessageUpdate]
 */
fun Flow<BaseMessageUpdate>.onlySentMessageUpdates(): Flow<BaseSentMessageUpdate> = filterIsInstance()

/**
 * Converts flow to [Flow] of [BaseSentMessageUpdate]
 */
fun Flow<BaseMessageUpdate>.onlyEditMessageUpdates(): Flow<BaseEditMessageUpdate> = filterIsInstance()

/**
 * Converts flow to [Flow] of [MediaGroupUpdate]. Please, remember that it could be either [EditMediaGroupUpdate]
 * or [SentMediaGroupUpdate]
 *
 * @see onlySentMediaGroupUpdates
 * @see onlyEditMediaGroupUpdates
 */
fun Flow<BaseMessageUpdate>.onlyMediaGroupsUpdates(): Flow<MediaGroupUpdate> = filterIsInstance()

/**
 * Converts flow to [Flow] of [SentMediaGroupUpdate]
 */
fun Flow<MediaGroupUpdate>.onlySentMediaGroupUpdates(): Flow<SentMediaGroupUpdate> = filterIsInstance()

/**
 * Converts flow to [Flow] of [EditMediaGroupUpdate]
 */
fun Flow<MediaGroupUpdate>.onlyEditMediaGroupUpdates(): Flow<EditMediaGroupUpdate> = filterIsInstance()
