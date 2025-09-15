package dev.inmo.tgbotapi.utils.extensions

import dev.inmo.tgbotapi.types.message.abstracts.ChannelDirectMessagesContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.Message
import dev.inmo.tgbotapi.types.message.abstracts.PossiblyTopicMessage

val Message.directMessageThreadIdOrNull
    get() = (this as? ChannelDirectMessagesContentMessage<*>) ?.directMessageTopic ?.threadId
