package com.github.insanusmokrassar.TelegramBotAPI.types.message.content

import com.github.insanusmokrassar.TelegramBotAPI.types.Location
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts.MessageContent

data class LocationContent(
    val location: Location
) : MessageContent