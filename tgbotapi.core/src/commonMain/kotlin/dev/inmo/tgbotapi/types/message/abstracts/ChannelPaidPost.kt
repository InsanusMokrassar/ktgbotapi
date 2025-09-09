package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.message.content.MessageContent

interface ChannelPaidPost<T: MessageContent> : ChannelContentMessage<T> {
    override val isPaidPost: Boolean
        get() = true
}
