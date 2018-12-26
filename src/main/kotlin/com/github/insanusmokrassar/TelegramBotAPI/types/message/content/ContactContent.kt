package com.github.insanusmokrassar.TelegramBotAPI.types.message.content

import com.github.insanusmokrassar.TelegramBotAPI.types.Contact
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts.MessageContent

data class ContactContent(
    val contact: Contact
) : MessageContent