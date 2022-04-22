package dev.inmo.tgbotapi.types.message.content.media

import dev.inmo.tgbotapi.types.media.VisualMediaGroupMemberTelegramMedia

interface VisualMediaGroupContent : MediaGroupContent {
    override fun toMediaGroupMemberTelegramMedia(): VisualMediaGroupMemberTelegramMedia
}
