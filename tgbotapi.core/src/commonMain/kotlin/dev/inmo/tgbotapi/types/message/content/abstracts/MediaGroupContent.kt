package dev.inmo.tgbotapi.types.message.content.abstracts

import dev.inmo.tgbotapi.CommonAbstracts.CaptionedInput
import dev.inmo.tgbotapi.types.InputMedia.MediaGroupMemberInputMedia

interface MediaGroupContent : MediaContent, CaptionedInput {
    fun toMediaGroupMemberInputMedia(): MediaGroupMemberInputMedia
}

interface VisualMediaGroupContent : MediaGroupContent
