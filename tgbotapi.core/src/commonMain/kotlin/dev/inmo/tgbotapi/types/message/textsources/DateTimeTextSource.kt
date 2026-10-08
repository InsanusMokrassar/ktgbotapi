package dev.inmo.tgbotapi.types.message.textsources

import dev.inmo.tgbotapi.types.DateTimeEntityFormatting
import dev.inmo.tgbotapi.types.UnixTimeStamp
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.internal.*
import kotlinx.serialization.Serializable

/**
 * A text source containing a date-time entity with optional [DateTimeEntityFormatting].
 *
 * @property dateTimeFormat Controls the rendered date-time format; `null` leaves the format unset.
 * @see linkTextSource
 */
@Serializable
data class DateTimeTextSource @RiskFeature(DirectInvocationOfTextSourceConstructor) constructor (
    override val source: String,
    val unixTimeStamp: UnixTimeStamp,
    val dateTimeFormat: DateTimeEntityFormatting?
) : TextSource {
    override val markdown: String by lazy { source.dateTimeMarkdown(unixTimeStamp, dateTimeFormat) }
    override val markdownV2: String by lazy { source.dateTimeMarkdownV2(unixTimeStamp, dateTimeFormat) }
    override val html: String by lazy { source.dateTimeHTML(unixTimeStamp, dateTimeFormat) }
}

/**
 * Creates a [DateTimeTextSource] with optional [DateTimeEntityFormatting].
 */
fun dateTimeTextSource(
    text: String,
    unixTimeStamp: UnixTimeStamp,
    dateTimeFormat: DateTimeEntityFormatting?
) = DateTimeTextSource(text, unixTimeStamp, dateTimeFormat)
