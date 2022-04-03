package dev.inmo.tgbotapi.types.message.content.media

import dev.inmo.tgbotapi.types.InputMedia.*
import dev.inmo.tgbotapi.types.message.content.media.MediaGroupContent

interface DocumentMediaGroupContent : MediaGroupContent {
    override fun toMediaGroupMemberInputMedia(): DocumentMediaGroupMemberInputMedia
}
