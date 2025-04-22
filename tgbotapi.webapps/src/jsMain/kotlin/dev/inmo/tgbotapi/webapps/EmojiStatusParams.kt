package dev.inmo.tgbotapi.webapps

import dev.inmo.tgbotapi.types.Seconds
import kotlin.js.json

external interface EmojiStatusParams {
    val duration: Seconds?
}

fun EmojiStatusParams(duration: Seconds): EmojiStatusParams =
    json(
        "duration" to duration,
    ).unsafeCast<EmojiStatusParams>()
