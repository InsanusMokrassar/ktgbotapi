package dev.inmo.tgbotapi.extensions.behaviour_builder.filters

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver
import dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling.CommonMessageFilter
import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage
import dev.inmo.tgbotapi.types.message.abstracts.MediaGroupMessage
import dev.inmo.tgbotapi.types.update.abstracts.Update

/**
 * Allow only messages which are not [MediaGroupMessage]
 */
val MessageFilterExcludingMediaGroups: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<*>, Update> = { message, update ->
    update !is MediaGroupMessage<*>
}

/**
 * Allow only messages which are not [MediaGroupMessage]
 */
val CommonMessageFilterExcludeMediaGroups: CommonMessageFilter<*> = {
    it !is MediaGroupMessage<*>
}
