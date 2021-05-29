package dev.inmo.tgbotapi.types.CallbackQuery

sealed interface GameShortNameCallbackQuery : CallbackQuery {
    val gameShortName: String
}
