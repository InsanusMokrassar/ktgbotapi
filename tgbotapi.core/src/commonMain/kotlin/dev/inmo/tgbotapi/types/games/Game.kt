package dev.inmo.tgbotapi.types.games

import dev.inmo.tgbotapi.CommonAbstracts.*
import dev.inmo.tgbotapi.types.files.AnimationFile
import dev.inmo.tgbotapi.types.files.Photo

data class Game(
    override val title: String,
    val description: String,
    val photo: Photo,
    override val text: String? = null,
    override val textEntities: List<TextPart> = emptyList(),
    val animation: AnimationFile? = null
) : Titled, CaptionedInput, TextedInput
