package com.github.insanusmokrassar.TelegramBotAPI.types.message.content

import com.github.insanusmokrassar.TelegramBotAPI.types.Venue
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts.MessageContent

data class VenueContent(
    val venue: Venue
) : MessageContent