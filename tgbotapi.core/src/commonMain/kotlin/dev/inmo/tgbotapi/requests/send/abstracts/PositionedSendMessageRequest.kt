package dev.inmo.tgbotapi.requests.send.abstracts

import dev.inmo.tgbotapi.CommonAbstracts.Locationed

interface PositionedSendMessageRequest<T: Any>: SendMessageRequest<T>, Locationed