package dev.inmo.tgbotapi.types.MessageEntity

@Deprecated("Redundant and will be removed soon")
fun String.removeLeading(word: String) = if (startsWith(word)) {
    substring(word.length)
} else {
    this
}
