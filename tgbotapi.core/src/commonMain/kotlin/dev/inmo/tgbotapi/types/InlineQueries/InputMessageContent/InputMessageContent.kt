package dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent

import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import dev.inmo.tgbotapi.types.InlineQueries.InputMessageContentSerializer
import kotlinx.serialization.Serializable

@Serializable(InputMessageContentSerializer::class)
@ClassCastsIncluded
sealed interface InputMessageContent
