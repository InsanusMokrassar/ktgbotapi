package dev.inmo.tgbotapi.requests

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.Seconds
import dev.inmo.tgbotapi.types.UpdateId

interface GetUpdatesRequest<T : Any> : SimpleRequest<T> {
    val offset: UpdateId?
    val limit: Int
    val timeout: Seconds?
    val allowed_updates: List<String>?

    override fun method(): String = "getUpdates"
}