package dev.inmo.tgbotapi.extensions.utils.updates

import dev.inmo.tgbotapi.extensions.utils.internal_utils.onlySpecifiedTypeOfData
import dev.inmo.tgbotapi.extensions.utils.internal_utils.onlySpecifiedTypeOfDataWithUpdates
import dev.inmo.tgbotapi.types.InlineQueries.ChosenInlineResult.BaseChosenInlineResult
import dev.inmo.tgbotapi.types.InlineQueries.ChosenInlineResult.LocationChosenInlineResult
import dev.inmo.tgbotapi.types.UpdateId
import dev.inmo.tgbotapi.types.update.ChosenInlineResultUpdate
import dev.inmo.tgbotapi.types.update.InlineQueryUpdate
import kotlinx.coroutines.flow.Flow

/**
 * @return Mapped [Flow] with [Pair]s. [Pair.first] in this pair will be [UpdateId]. It could be useful in
 * cases you are using [InlineQueryUpdate.updateId] for some reasons. [Pair.second] will always be [BaseChosenInlineResult].
 */
fun Flow<ChosenInlineResultUpdate>.onlyBaseChosenInlineResultsWithUpdates(): Flow<Pair<UpdateId, BaseChosenInlineResult>> =
    onlySpecifiedTypeOfDataWithUpdates()

/**
 * @return Filter updates only with [BaseChosenInlineResult] and map it to a [Flow] with values [BaseChosenInlineResult]
 *
 * @see onlyBaseChosenInlineResultsWithUpdates
 */
fun Flow<ChosenInlineResultUpdate>.onlyBaseChosenInlineResults(): Flow<BaseChosenInlineResult> = onlySpecifiedTypeOfData()

/**
 * @return Mapped [Flow] with [Pair]s. [Pair.first] in this pair will be [UpdateId]. It could be useful in
 * cases you are using [InlineQueryUpdate.updateId] for some reasons. [Pair.second] will always be [LocationChosenInlineResult].
 */
fun Flow<ChosenInlineResultUpdate>.onlyLocationChosenInlineResultsWithUpdates(): Flow<Pair<UpdateId, LocationChosenInlineResult>> =
    onlySpecifiedTypeOfDataWithUpdates()

/**
 * @return Filter updates only with [LocationChosenInlineResult] and map it to a [Flow] with values [LocationChosenInlineResult]
 *
 * @see onlyLocationChosenInlineResultsWithUpdates
 */
fun Flow<ChosenInlineResultUpdate>.onlyLocationChosenInlineResults(): Flow<LocationChosenInlineResult> = onlySpecifiedTypeOfData()
