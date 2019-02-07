package com.github.insanusmokrassar.TelegramBotAPI.requests.edit.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.ParseMode

interface EditTextChatMessage {
    val text: String
    val parseMode: ParseMode?
}