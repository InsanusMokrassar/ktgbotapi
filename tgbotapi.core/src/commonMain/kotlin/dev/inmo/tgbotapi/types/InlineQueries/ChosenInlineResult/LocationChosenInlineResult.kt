package dev.inmo.tgbotapi.types.InlineQueries.ChosenInlineResult

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.location.StaticLocation
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationChosenInlineResult(
    override val resultId: InlineQueryIdentifier,
    @SerialName(fromField)
    override val user: User,
    val location: StaticLocation,
    override val inlineMessageId: InlineMessageIdentifier?,
    override val query: String
) : ChosenInlineResult
