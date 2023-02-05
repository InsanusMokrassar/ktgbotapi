package dev.inmo.tgbotapi.types.request

sealed interface RequestResponse {
    val requestId: RequestId
}
