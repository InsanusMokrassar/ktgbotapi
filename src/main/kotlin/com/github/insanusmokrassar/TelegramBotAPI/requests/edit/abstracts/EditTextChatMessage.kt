package com.github.insanusmokrassar.TelegramBotAPI.requests.edit.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.ParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.message.RawMessage
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest

interface EditTextChatMessage : SimpleRequest<RawMessage> {
    val text: String
    val parseMode: ParseMode?
}