package dev.inmo.tgbotapi.types

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class CustomEmojiId(
    val string: String,
) {
    val appLink
        get() = "${internalTgAppLinksBeginning}emoji?id=$this"
}
