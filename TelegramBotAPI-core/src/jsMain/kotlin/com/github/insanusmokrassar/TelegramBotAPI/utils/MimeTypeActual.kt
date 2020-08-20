package com.github.insanusmokrassar.TelegramBotAPI.utils

import kotlinx.serialization.*
import org.w3c.dom.get
import kotlinx.browser.window

@Serializable(MimeTypeSerializer::class)
actual class MimeType(
    actual val raw: String
) {
    @Transient
    val jsMimeType = window.navigator.mimeTypes[raw]

    override fun toString(): String = raw
}

actual fun createMimeType(raw: String) = MimeType(raw)
