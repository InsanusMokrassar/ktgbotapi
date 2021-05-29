package dev.inmo.tgbotapi.types.InputMedia

sealed interface SizedInputMedia : InputMedia {
    val width: Int?
    val height: Int?
}
