package dev.inmo.tgbotapi.extensions.utils.updates

import dev.inmo.tgbotapi.extensions.utils.internal_utils.onlySpecifiedTypeOfData
import dev.inmo.tgbotapi.extensions.utils.internal_utils.onlySpecifiedTypeOfDataWithUpdates
import dev.inmo.tgbotapi.types.InlineQueries.query.BaseInlineQuery
import dev.inmo.tgbotapi.types.InlineQueries.query.LocationInlineQuery
import dev.inmo.tgbotapi.types.UpdateIdentifier
import dev.inmo.tgbotapi.types.update.InlineQueryUpdate
import kotlinx.coroutines.flow.Flow

/**
 * @return Mapped [Flow] with [Pair]s. [Pair.first] in this pair will be [UpdateIdentifier]. It could be useful in
 * cases you are using [InlineQueryUpdate.updateId] for some reasons. [Pair.second] will always be [BaseInlineQuery].
 */
fun Flow<InlineQueryUpdate>.onlyBaseInlineQueriesWithUpdates(): Flow<Pair<UpdateIdentifier, BaseInlineQuery>> = onlySpecifiedTypeOfDataWithUpdates()

/**
 * @return Filter updates only with [BaseInlineQuery] and map it to a [Flow] with values [BaseInlineQuery]
 *
 * @see onlyBaseInlineQueriesWithUpdates
 */
fun Flow<InlineQueryUpdate>.onlyBaseInlineQueries(): Flow<BaseInlineQuery> = onlySpecifiedTypeOfData()

/**
 * @return Mapped [Flow] with [Pair]s. [Pair.first] in this pair will be [UpdateIdentifier]. It could be useful in
 * cases you are using [InlineQueryUpdate.updateId] for some reasons. [Pair.second] will always be [LocationInlineQuery].
 */
fun Flow<InlineQueryUpdate>.onlyLocationInlineQueriesWithUpdates(): Flow<Pair<UpdateIdentifier, LocationInlineQuery>> = onlySpecifiedTypeOfDataWithUpdates()

/**
 * @return Filter updates only with [LocationInlineQuery] and map it to a [Flow] with values [LocationInlineQuery]
 *
 * @see onlyLocationInlineQueriesWithUpdates
 */
fun Flow<InlineQueryUpdate>.onlyLocationInlineQueries(): Flow<LocationInlineQuery> = onlySpecifiedTypeOfData()
