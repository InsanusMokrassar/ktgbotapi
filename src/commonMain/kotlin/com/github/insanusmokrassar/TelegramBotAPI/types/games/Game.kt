package com.github.insanusmokrassar.TelegramBotAPI.types.games

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.types.files.AnimationFile
import com.github.insanusmokrassar.TelegramBotAPI.types.files.Photo

data class Game(
    override val title: String,
    val description: String,
    val photo: Photo,
    override val caption: String? = null,
    override val captionEntities: List<TextPart> = emptyList(),
    val animation: AnimationFile? = null
) : Titled, CaptionedInput
