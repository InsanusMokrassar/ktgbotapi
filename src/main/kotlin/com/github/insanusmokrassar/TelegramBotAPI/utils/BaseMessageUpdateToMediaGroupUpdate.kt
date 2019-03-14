package com.github.insanusmokrassar.TelegramBotAPI.utils

import com.github.insanusmokrassar.TelegramBotAPI.types.update.*
import com.github.insanusmokrassar.TelegramBotAPI.types.update.MediaGroupUpdates.*
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.BaseMessageUpdate

fun List<BaseMessageUpdate>.toMediaGroupUpdate(): MediaGroupUpdate? = (this as? MediaGroupUpdate) ?: let {
    when (first()) {
        is MessageUpdate -> MessageMediaGroupUpdate(this)
        is EditMessageUpdate -> EditMessageMediaGroupUpdate(this)
        is ChannelPostUpdate -> ChannelPostMediaGroupUpdate(this)
        is EditChannelPostUpdate -> EditChannelPostMediaGroupUpdate(this)
        else -> null
    }
}
