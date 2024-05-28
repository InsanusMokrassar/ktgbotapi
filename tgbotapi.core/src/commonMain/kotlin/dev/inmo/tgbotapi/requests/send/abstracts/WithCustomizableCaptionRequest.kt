package dev.inmo.tgbotapi.requests.send.abstracts

import dev.inmo.tgbotapi.abstracts.WithCustomizableCaption
import dev.inmo.tgbotapi.requests.abstracts.Request

interface WithCustomizableCaptionRequest<T : Any> : Request<T>, WithCustomizableCaption
