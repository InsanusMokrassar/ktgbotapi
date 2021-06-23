package dev.inmo.tgbotapi.types.CallbackQuery

sealed interface DataCallbackQuery : CallbackQuery {
    val data: String
}
