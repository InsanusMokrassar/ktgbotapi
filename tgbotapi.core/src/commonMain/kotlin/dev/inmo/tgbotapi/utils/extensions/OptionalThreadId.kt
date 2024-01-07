package dev.inmo.tgbotapi.utils.extensions

import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage
import dev.inmo.tgbotapi.types.message.abstracts.PossiblyTopicMessage

val AccessibleMessage.threadIdOrNull
    get() = (this as? PossiblyTopicMessage) ?.threadId
