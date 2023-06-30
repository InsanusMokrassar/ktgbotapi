package dev.inmo.tgbotapi.requests

import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.ALL_UPDATES_LIST
import dev.inmo.tgbotapi.types.Seconds
import dev.inmo.tgbotapi.types.UpdateIdentifier
import dev.inmo.tgbotapi.types.getUpdatesLimit

interface GetUpdatesRequest<T : Any> : SimpleRequest<T> {
    val offset: UpdateIdentifier?
    val limit: Int
    val timeout: Seconds?
    val allowed_updates: List<String>?

    override fun method(): String = "getUpdates"
}