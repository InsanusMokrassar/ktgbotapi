package dev.inmo.tgbotapi.types.InputMedia

sealed interface DuratedInputMedia : InputMedia {
    val duration: Long?
}
