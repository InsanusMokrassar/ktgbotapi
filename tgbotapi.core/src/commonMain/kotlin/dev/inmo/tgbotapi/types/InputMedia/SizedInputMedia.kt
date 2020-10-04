package dev.inmo.tgbotapi.types.InputMedia

interface SizedInputMedia : InputMedia {
    val width: Int?
    val height: Int?
}