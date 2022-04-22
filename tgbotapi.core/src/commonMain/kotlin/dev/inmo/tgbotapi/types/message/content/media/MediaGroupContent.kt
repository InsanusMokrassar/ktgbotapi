package dev.inmo.tgbotapi.types.message.content.media

import dev.inmo.tgbotapi.types.media.MediaGroupMemberTelegramMedia

interface MediaGroupContent : TextedMediaContent {
    fun toMediaGroupMemberTelegramMedia(): MediaGroupMemberTelegramMedia
    @Deprecated("Renamed", ReplaceWith("toMediaGroupMemberTelegramMedia()"))
    fun toMediaGroupMemberInputMedia(): MediaGroupMemberTelegramMedia = toMediaGroupMemberTelegramMedia()
}
