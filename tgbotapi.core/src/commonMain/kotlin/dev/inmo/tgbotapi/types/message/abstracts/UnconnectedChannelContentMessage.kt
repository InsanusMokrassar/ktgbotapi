package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.message.content.abstracts.MessageContent

interface UnconnectedChannelContentMessage<T: MessageContent> : ChannelContentMessage<T>
