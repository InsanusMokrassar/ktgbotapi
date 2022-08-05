package dev.inmo.tgbotapi.requests.send.abstracts

import dev.inmo.tgbotapi.abstracts.types.WithReplyMarkup

interface ReplyingMarkupSendMessageRequest<T: Any>: SendMessageRequest<T>, WithReplyMarkup
