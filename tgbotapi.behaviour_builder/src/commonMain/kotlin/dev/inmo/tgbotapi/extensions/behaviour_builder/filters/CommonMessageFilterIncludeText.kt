package dev.inmo.tgbotapi.extensions.behaviour_builder.filters

import dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling.CommonMessageFilter
import dev.inmo.tgbotapi.extensions.utils.textedContentOrNull

/**
 * Includes messages only contains text with [textRegex]
 */
fun CommonMessageFilterIncludeText(textRegex: Regex): CommonMessageFilter<*> {
    return CommonMessageFilter {
        it.content.textedContentOrNull() ?.text ?.contains(textRegex) == true
    }
}
