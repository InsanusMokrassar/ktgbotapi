package dev.inmo.tgbotapi.types.games

import dev.inmo.tgbotapi.abstracts.TextedInput
import dev.inmo.tgbotapi.abstracts.Titled
import dev.inmo.tgbotapi.types.ReplyInfo
import dev.inmo.tgbotapi.types.files.AnimationFile
import dev.inmo.tgbotapi.types.files.PhotoFile
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import kotlinx.serialization.Serializable

@Serializable
data class Game(
    override val title: String,
    val description: String,
    val photo: PhotoFile,
    override val text: String? = null,
    override val textSources: TextSourcesList = emptyList(),
    val animation: AnimationFile? = null,
) : Titled, TextedInput, ReplyInfo.External.ContentVariant
