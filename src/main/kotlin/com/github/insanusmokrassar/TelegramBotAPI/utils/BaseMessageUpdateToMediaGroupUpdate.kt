package com.github.insanusmokrassar.TelegramBotAPI.utils

import com.github.insanusmokrassar.TelegramBotAPI.types.update.*
import com.github.insanusmokrassar.TelegramBotAPI.types.update.MediaGroupUpdates.*
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.BaseMessageUpdate

fun List<BaseMessageUpdate>.toMediaGroupUpdate(): MediaGroupUpdate? = (this as? MediaGroupUpdate) ?: let {
    val resultList = sortedBy { it.updateId }
    when (first()) {
        is MessageUpdate -> MessageMediaGroupUpdate(resultList)
        is EditMessageUpdate -> EditMessageMediaGroupUpdate(resultList)
        is ChannelPostUpdate -> ChannelPostMediaGroupUpdate(resultList)
        is EditChannelPostUpdate -> EditChannelPostMediaGroupUpdate(resultList)
        else -> null
    }
}
