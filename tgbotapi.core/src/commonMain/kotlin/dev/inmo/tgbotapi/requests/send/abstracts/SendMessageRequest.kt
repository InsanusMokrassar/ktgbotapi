package dev.inmo.tgbotapi.requests.send.abstracts

import dev.inmo.tgbotapi.CommonAbstracts.types.DisableNotification
import dev.inmo.tgbotapi.CommonAbstracts.types.ReplyMessageId

interface SendMessageRequest<T: Any> : SendChatMessageRequest<T>, ReplyMessageId, DisableNotification
