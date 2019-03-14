package com.github.insanusmokrassar.TelegramBotAPI.utils

import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.MediaGroupMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.update.*
import com.github.insanusmokrassar.TelegramBotAPI.types.update.MediaGroupUpdates.*
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.BaseMessageUpdate

fun BaseMessageUpdate.toMediaGroupUpdate(): MediaGroupUpdate? = (this as? MediaGroupUpdate) ?: ((data as? MediaGroupMessage) ?.let {
    when (this) {
        is MessageUpdate -> MessageMediaGroupUpdate(this)
        is EditMessageUpdate -> EditMessageMediaGroupUpdate(this)
        is ChannelPostUpdate -> ChannelPostMediaGroupUpdate(this)
        is EditChannelPostUpdate -> EditChannelPostMediaGroupUpdate(this)
        else -> null
    }
})
