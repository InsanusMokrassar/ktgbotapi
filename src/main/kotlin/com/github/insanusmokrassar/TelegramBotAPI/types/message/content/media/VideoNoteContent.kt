package com.github.insanusmokrassar.TelegramBotAPI.types.message.content.media

import com.github.insanusmokrassar.TelegramBotAPI.types.files.VideoNoteFile
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts.MediaContent

data class VideoNoteContent(
    override val media: VideoNoteFile
) : MediaContent<VideoNoteFile>
