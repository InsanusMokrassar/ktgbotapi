package dev.inmo.tgbotapi.extensions.behaviour_builder.filters

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver
import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage
import dev.inmo.tgbotapi.types.message.abstracts.MediaGroupMessage
import dev.inmo.tgbotapi.types.update.abstracts.Update

val MessageFilterExcludingMediaGroups: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<*>, Update> = { message, update ->
    update !is MediaGroupMessage<*>
}
