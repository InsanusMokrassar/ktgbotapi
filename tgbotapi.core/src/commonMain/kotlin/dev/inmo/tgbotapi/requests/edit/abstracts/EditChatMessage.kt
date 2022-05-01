package dev.inmo.tgbotapi.requests.edit.abstracts

import dev.inmo.tgbotapi.CommonAbstracts.types.MessageAction
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.MessageContent

interface EditChatMessage<MT: MessageContent> : SimpleRequest<ContentMessage<MT>>, MessageAction
