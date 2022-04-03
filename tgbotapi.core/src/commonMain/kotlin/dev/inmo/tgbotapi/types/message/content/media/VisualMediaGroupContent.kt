package dev.inmo.tgbotapi.types.message.content.media

import dev.inmo.tgbotapi.types.InputMedia.VisualMediaGroupMemberInputMedia

interface VisualMediaGroupContent : TextedMediaGroupMediaInput {
    override fun toMediaGroupMemberInputMedia(): VisualMediaGroupMemberInputMedia
}
