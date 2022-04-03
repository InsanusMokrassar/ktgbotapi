package dev.inmo.tgbotapi.types.message.content.media

import dev.inmo.tgbotapi.CommonAbstracts.TextedInput
import dev.inmo.tgbotapi.types.message.content.abstracts.MediaContent

sealed interface TextedMediaContent : MediaContent, TextedInput
