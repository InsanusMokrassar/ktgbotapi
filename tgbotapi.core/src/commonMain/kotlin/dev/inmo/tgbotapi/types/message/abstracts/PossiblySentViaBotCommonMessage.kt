package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.message.content.MessageContent

sealed interface PossiblySentViaBotCommonMessage<out T : MessageContent> : CommonMessage<T>, PossiblySentViaBot
