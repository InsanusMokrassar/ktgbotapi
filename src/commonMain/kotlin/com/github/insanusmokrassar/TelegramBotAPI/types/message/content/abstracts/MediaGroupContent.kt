package com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.CaptionedInput
import com.github.insanusmokrassar.TelegramBotAPI.types.InputMedia.MediaGroupMemberInputMedia

interface MediaGroupContent : MediaContent, CaptionedInput {
    fun toMediaGroupMemberInputMedia(): MediaGroupMemberInputMedia
}