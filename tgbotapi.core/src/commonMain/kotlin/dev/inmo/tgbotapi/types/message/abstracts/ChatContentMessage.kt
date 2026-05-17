package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.message.content.MessageContent

sealed interface ChatContentMessage<out T: MessageContent> : CommonContentMessage<T>, ChatMessage
