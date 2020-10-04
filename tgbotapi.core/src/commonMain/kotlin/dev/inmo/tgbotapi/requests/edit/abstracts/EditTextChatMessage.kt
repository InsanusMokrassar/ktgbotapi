package dev.inmo.tgbotapi.requests.edit.abstracts

import dev.inmo.tgbotapi.types.ParseMode.ParseMode

interface EditTextChatMessage {
    val text: String
    val parseMode: ParseMode?
}