package dev.inmo.tgbotapi.extensions.behaviour_builder.filters

import dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling.CommonMessageFilter
import dev.inmo.tgbotapi.types.message.abstracts.MediaGroupMessage

val CommonMessageFilterExcludeMediaGroups: CommonMessageFilter<*> = {
    it !is MediaGroupMessage<*>
}
