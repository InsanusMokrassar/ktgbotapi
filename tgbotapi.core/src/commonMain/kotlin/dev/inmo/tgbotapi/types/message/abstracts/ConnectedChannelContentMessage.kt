package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.message.content.abstracts.MessageContent

interface ConnectedChannelContentMessage<T: MessageContent> : ChannelContentMessage<T>
