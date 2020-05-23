package com.github.insanusmokrassar.TelegramBotAPI.extensions.utils.updates

import com.github.insanusmokrassar.TelegramBotAPI.types.update.MediaGroupUpdates.*
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull

fun Flow<Update>.onlyBaseMessageUpdates(): Flow<BaseMessageUpdate> = mapNotNull {
    it as? BaseMessageUpdate
}

/**
 * Converts flow to [Flow] of [BaseSentMessageUpdate]
 */
fun Flow<BaseMessageUpdate>.onlySentMessageUpdates(): Flow<BaseSentMessageUpdate> = mapNotNull {
    it as? BaseSentMessageUpdate
}

/**
 * Converts flow to [Flow] of [BaseSentMessageUpdate]
 */
fun Flow<BaseMessageUpdate>.onlyEditMessageUpdates(): Flow<BaseEditMessageUpdate> = mapNotNull {
    it as? BaseEditMessageUpdate
}

/**
 * Converts flow to [Flow] of [MediaGroupUpdate]. Please, remember that it could be either [EditMediaGroupUpdate]
 * or [SentMediaGroupUpdate]
 *
 * @see onlySentMediaGroupUpdates
 * @see onlyEditMediaGroupUpdates
 */
fun Flow<BaseMessageUpdate>.onlyMediaGroupsUpdates(): Flow<MediaGroupUpdate> = mapNotNull {
    it as? MediaGroupUpdate
}

/**
 * Converts flow to [Flow] of [SentMediaGroupUpdate]
 */
fun Flow<MediaGroupUpdate>.onlySentMediaGroupUpdates(): Flow<SentMediaGroupUpdate> = mapNotNull {
    it as? SentMediaGroupUpdate
}

/**
 * Converts flow to [Flow] of [EditMediaGroupUpdate]
 */
fun Flow<MediaGroupUpdate>.onlyEditMediaGroupUpdates(): Flow<EditMediaGroupUpdate> = mapNotNull {
    it as? EditMediaGroupUpdate
}
