package dev.inmo.tgbotapi.types.queries.callback

/**
 * [CallbackQuery] with [data] field
 *
 * @see dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.CallbackDataInlineKeyboardButton
 */
sealed interface DataCallbackQuery : CallbackQuery {
    val data: String
}
