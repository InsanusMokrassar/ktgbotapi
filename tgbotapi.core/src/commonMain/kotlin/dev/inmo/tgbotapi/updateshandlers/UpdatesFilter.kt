package dev.inmo.tgbotapi.updateshandlers

import dev.inmo.tgbotapi.types.update.abstracts.Update

typealias UpdateReceiver<T> = suspend (T) -> Unit

interface UpdatesFilter {
    val asUpdateReceiver: UpdateReceiver<Update>
    val allowedUpdates: List<String>
}
