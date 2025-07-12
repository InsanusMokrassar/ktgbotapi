package dev.inmo.tgbotapi.utils

import kotlinx.browser.window
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import org.w3c.dom.get

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
@Serializable(MimeTypeSerializer::class)
actual class MimeType(
    actual val raw: String
) {
    @Transient
    val jsMimeType = window.navigator.mimeTypes[raw]

    override fun toString(): String = raw
}

actual fun createMimeType(raw: String) = MimeType(raw)
