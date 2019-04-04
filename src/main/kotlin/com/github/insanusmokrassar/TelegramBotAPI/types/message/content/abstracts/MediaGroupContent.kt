package com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.InputMedia.MediaGroupMemberInputMedia
import com.github.insanusmokrassar.TelegramBotAPI.types.files.abstracts.TelegramMediaFile

interface MediaGroupContent<T : TelegramMediaFile> : MediaContent<T>, MessageContent {
    fun toMediaGroupMemberInputMedia(): MediaGroupMemberInputMedia
}