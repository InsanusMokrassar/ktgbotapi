package dev.inmo.tgbotapi.extensions.behaviour_builder.filters

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.types.message.abstracts.*
import dev.inmo.tgbotapi.types.update.abstracts.Update

/**
 * Allow only messages which are not [MediaGroupMessage]
 */
val MessageFilterExcludingMediaGroups: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<*>, Update> = { _, update ->
    update !is MediaGroupMessage<*>
}

/**
 * Allow only messages which are not [MediaGroupMessage]
 */
val CommonMessageFilterExcludeMediaGroups = SimpleFilter<Message> {
    it !is MediaGroupMessage<*>
}
