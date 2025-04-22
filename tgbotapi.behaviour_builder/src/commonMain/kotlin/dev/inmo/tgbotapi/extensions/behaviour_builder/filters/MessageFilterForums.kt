package dev.inmo.tgbotapi.extensions.behaviour_builder.filters

import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.types.message.abstracts.*
import dev.inmo.tgbotapi.utils.extensions.threadIdOrNull

/**
 * Allow only messages which are not in some forum
 */
val MessageFilterForums =
    SimpleFilter<AccessibleMessage> {
        it.threadIdOrNull == null
    }
