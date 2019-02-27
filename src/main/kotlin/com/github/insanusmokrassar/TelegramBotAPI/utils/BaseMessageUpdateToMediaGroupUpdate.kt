package com.github.insanusmokrassar.TelegramBotAPI.utils

import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.MediaGroupMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.update.MediaGroupUpdate
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.BaseMessageUpdate

fun BaseMessageUpdate.toMediaGroupUpdate(): MediaGroupUpdate? = (data as? MediaGroupMessage) ?.let {
    MediaGroupUpdate(updateId, it)
}
