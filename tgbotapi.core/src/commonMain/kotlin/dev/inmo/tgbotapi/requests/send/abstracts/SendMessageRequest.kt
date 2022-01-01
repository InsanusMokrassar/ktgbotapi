package dev.inmo.tgbotapi.requests.send.abstracts

import dev.inmo.tgbotapi.CommonAbstracts.types.*

interface SendMessageRequest<T: Any> : SendChatMessageRequest<T>, ReplyMessageId, DisableNotification, ProtectContent
