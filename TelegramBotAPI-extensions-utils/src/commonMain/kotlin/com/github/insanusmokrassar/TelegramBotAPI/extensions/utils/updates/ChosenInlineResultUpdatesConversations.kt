package com.github.insanusmokrassar.TelegramBotAPI.extensions.utils.updates

import com.github.insanusmokrassar.TelegramBotAPI.extensions.utils.internal_utils.onlySpecifiedTypeOfData
import com.github.insanusmokrassar.TelegramBotAPI.extensions.utils.internal_utils.onlySpecifiedTypeOfDataWithUpdates
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.ChosenInlineResult.BaseChosenInlineResult
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineQueries.ChosenInlineResult.LocationChosenInlineResult
import com.github.insanusmokrassar.TelegramBotAPI.types.UpdateIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.update.ChosenInlineResultUpdate
import com.github.insanusmokrassar.TelegramBotAPI.types.update.InlineQueryUpdate
import kotlinx.coroutines.flow.Flow

/**
 * @return Mapped [Flow] with [Pair]s. [Pair.first] in this pair will be [UpdateIdentifier]. It could be useful in
 * cases you are using [InlineQueryUpdate.updateId] for some reasons. [Pair.second] will always be [BaseChosenInlineResult].
 */
fun Flow<ChosenInlineResultUpdate>.onlyBaseChosenInlineResultsWithUpdates(): Flow<Pair<UpdateIdentifier, BaseChosenInlineResult>> = onlySpecifiedTypeOfDataWithUpdates()

/**
 * @return Filter updates only with [BaseChosenInlineResult] and map it to a [Flow] with values [BaseChosenInlineResult]
 *
 * @see onlyBaseChosenInlineResultsWithUpdates
 */
fun Flow<ChosenInlineResultUpdate>.onlyBaseChosenInlineResults(): Flow<BaseChosenInlineResult> = onlySpecifiedTypeOfData()

/**
 * @return Mapped [Flow] with [Pair]s. [Pair.first] in this pair will be [UpdateIdentifier]. It could be useful in
 * cases you are using [InlineQueryUpdate.updateId] for some reasons. [Pair.second] will always be [LocationChosenInlineResult].
 */
fun Flow<ChosenInlineResultUpdate>.onlyLocationChosenInlineResultsWithUpdates(): Flow<Pair<UpdateIdentifier, LocationChosenInlineResult>> = onlySpecifiedTypeOfDataWithUpdates()

/**
 * @return Filter updates only with [LocationChosenInlineResult] and map it to a [Flow] with values [LocationChosenInlineResult]
 *
 * @see onlyLocationChosenInlineResultsWithUpdates
 */
fun Flow<ChosenInlineResultUpdate>.onlyLocationChosenInlineResults(): Flow<LocationChosenInlineResult> = onlySpecifiedTypeOfData()
