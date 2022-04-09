package dev.inmo.tgbotapi.types.message.content.media

import dev.inmo.tgbotapi.types.InputMedia.MediaGroupMemberInputMedia

interface MediaGroupContent : TextedMediaContent {
    fun toMediaGroupMemberInputMedia(): MediaGroupMemberInputMedia
}
