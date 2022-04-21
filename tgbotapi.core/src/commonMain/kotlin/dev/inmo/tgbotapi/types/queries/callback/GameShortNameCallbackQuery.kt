package dev.inmo.tgbotapi.types.queries.callback

sealed interface GameShortNameCallbackQuery : CallbackQuery {
    val gameShortName: String
}
