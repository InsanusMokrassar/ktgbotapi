package dev.inmo.tgbotapi.extensions.utils.extensions.raw

import dev.inmo.tgbotapi.extensions.utils.asLocationChosenInlineResult
import dev.inmo.tgbotapi.types.InlineQueries.ChosenInlineResult.ChosenInlineResult
import dev.inmo.tgbotapi.types.location.Location
import dev.inmo.tgbotapi.utils.RiskFeature

@RiskFeature(RawFieldsUsageWarning)
val ChosenInlineResult.location: Location?
    get() = asLocationChosenInlineResult() ?.location
