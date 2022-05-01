package dev.inmo.tgbotapi.requests.send.abstracts

import dev.inmo.tgbotapi.abstracts.types.ReplyMarkup

interface ReplyingMarkupSendMessageRequest<T: Any>: SendMessageRequest<T>, ReplyMarkup
