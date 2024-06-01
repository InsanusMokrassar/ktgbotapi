package dev.inmo.tgbotapi.requests.send.abstracts

import dev.inmo.tgbotapi.abstracts.types.OptionallyWithEffectId
import dev.inmo.tgbotapi.requests.abstracts.Request

interface OptionallyWithEffectRequest<T : Any> : OptionallyWithEffectId, Request<T>
