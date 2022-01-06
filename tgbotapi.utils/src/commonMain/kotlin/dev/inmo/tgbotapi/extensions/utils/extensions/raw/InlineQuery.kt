package dev.inmo.tgbotapi.extensions.utils.extensions.raw

import dev.inmo.tgbotapi.extensions.utils.asLocationInlineQuery
import dev.inmo.tgbotapi.types.InlineQueries.query.InlineQuery
import dev.inmo.tgbotapi.types.location.Location
import dev.inmo.tgbotapi.utils.RiskFeature

@RiskFeature(RawFieldsUsageWarning)
val InlineQuery.location: Location?
    get() = asLocationInlineQuery() ?.location
