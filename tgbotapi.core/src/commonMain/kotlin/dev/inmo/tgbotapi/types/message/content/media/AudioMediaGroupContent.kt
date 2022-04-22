package dev.inmo.tgbotapi.types.message.content.media

import dev.inmo.tgbotapi.types.media.AudioMediaGroupMemberTelegramMedia
import dev.inmo.tgbotapi.types.files.AudioFile

interface AudioMediaGroupContent : MediaGroupContent {
    override val media: AudioFile

    override fun toMediaGroupMemberTelegramMedia(): AudioMediaGroupMemberTelegramMedia
}
