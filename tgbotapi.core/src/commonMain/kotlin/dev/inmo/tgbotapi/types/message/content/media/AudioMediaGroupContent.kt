package dev.inmo.tgbotapi.types.message.content.media

import dev.inmo.tgbotapi.types.InputMedia.AudioMediaGroupMemberInputMedia

interface AudioMediaGroupContent : MediaGroupContent {
    override fun toMediaGroupMemberInputMedia(): AudioMediaGroupMemberInputMedia
}
