package dev.inmo.tgbotapi.utils.extensions

import dev.inmo.tgbotapi.types.message.abstracts.Message
import dev.inmo.tgbotapi.types.message.abstracts.PossiblyTopicMessage

val Message.threadIdOrNull
    get() = (this as? PossiblyTopicMessage) ?.threadId
