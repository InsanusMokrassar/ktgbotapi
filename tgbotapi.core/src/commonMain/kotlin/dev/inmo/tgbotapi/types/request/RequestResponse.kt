package dev.inmo.tgbotapi.types.request

import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded

@ClassCastsIncluded
sealed interface RequestResponse {
    val requestId: RequestId
}
