package dev.inmo.tgbotapi.extensions.behaviour_builder.filters

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.types.message.abstracts.*
import dev.inmo.tgbotapi.types.message.content.MediaGroupContent
import dev.inmo.tgbotapi.types.message.content.MediaGroupMessage
import dev.inmo.tgbotapi.types.update.abstracts.Update

/**
 * Allow only messages which are not [MediaGroupMessage]
 */
val CommonMessageFilterExcludeMediaGroups = SimpleFilter<Message> {
    it !is CommonMessage<*> || it.content !is MediaGroupContent
}
