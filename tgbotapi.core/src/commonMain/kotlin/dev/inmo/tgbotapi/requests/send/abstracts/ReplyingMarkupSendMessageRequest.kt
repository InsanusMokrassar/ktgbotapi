package dev.inmo.tgbotapi.requests.send.abstracts

import dev.inmo.tgbotapi.CommonAbstracts.types.ReplyMarkup

interface ReplyingMarkupSendMessageRequest<T: Any>: SendMessageRequest<T>, ReplyMarkup