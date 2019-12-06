package com.github.insanusmokrassar.TelegramBotAPI.requests.send.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.types.ReplyMarkup

interface ReplyingMarkupSendMessageRequest<T: Any>: SendMessageRequest<T>, ReplyMarkup