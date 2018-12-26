package com.github.insanusmokrassar.TelegramBotAPI.types.message.content.media

import com.github.insanusmokrassar.TelegramBotAPI.types.files.AnimationFile
import com.github.insanusmokrassar.TelegramBotAPI.types.files.DocumentFile
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts.MediaContent

data class AnimationContent(
    override val media: AnimationFile,
    val includedDocument: DocumentFile?
) : MediaContent<AnimationFile>