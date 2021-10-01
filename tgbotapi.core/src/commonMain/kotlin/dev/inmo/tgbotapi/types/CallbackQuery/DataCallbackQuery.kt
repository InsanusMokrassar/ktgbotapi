package dev.inmo.tgbotapi.types.CallbackQuery

/**
 * [CallbackQuery] with [data] field
 *
 * @see dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.CallbackDataInlineKeyboardButton
 */
sealed interface DataCallbackQuery : CallbackQuery {
    val data: String
}
