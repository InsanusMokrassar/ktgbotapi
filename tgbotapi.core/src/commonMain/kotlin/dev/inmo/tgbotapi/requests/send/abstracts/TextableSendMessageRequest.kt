package dev.inmo.tgbotapi.requests.send.abstracts

import dev.inmo.tgbotapi.abstracts.TextedOutput

interface TextableSendMessageRequest<T : Any> : SendMessageRequest<T>, TextedOutput
