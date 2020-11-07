package dev.inmo.tgbotapi.requests.send.abstracts

import dev.inmo.tgbotapi.CommonAbstracts.TextedOutput

interface TextableSendMessageRequest<T: Any>: SendMessageRequest<T>, TextedOutput
