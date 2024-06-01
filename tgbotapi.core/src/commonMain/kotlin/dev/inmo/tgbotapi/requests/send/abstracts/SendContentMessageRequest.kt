package dev.inmo.tgbotapi.requests.send.abstracts

import dev.inmo.tgbotapi.abstracts.types.OptionallyBusinessConnectionRequest

interface SendContentMessageRequest<T: Any> : SendMessageRequest<T>, OptionallyBusinessConnectionRequest, OptionallyWithEffectRequest<T>
