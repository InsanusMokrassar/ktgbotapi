package dev.inmo.tgbotapi.types.InputMedia

sealed interface TitledInputMedia : InputMedia {
    val title: String?
}
