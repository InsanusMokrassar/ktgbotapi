package dev.inmo.tgbotapi.types

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * Common interface for safe [DateTimeEntityFormatting] parts. Used for
 * [dev.inmo.tgbotapi.types.message.textsources.DateTimeTextSource].
 *
 * @see TgDateTimeFormatBuilder
 * @see buildDateTimeFormat
 */
@Serializable(DateTimeFormatPart.Serializer::class)
sealed interface DateTimeFormatPart {
    /**
     * Character that represents the part in [DateTimeEntityFormatting].
     */
    val controlCharacter: String
    /**
     * Represents relative time format (e.g. "2 hours ago"). Control character: "r"
     */
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(DateTimeFormatPart.Serializer::class)
    data object Relative : DateTimeFormatPart {
        override val controlCharacter: String
            get() = DateTimeEntityFormatting.LOCAL_CONTROL_KEY
    }
    /**
     * Represents day of the week format (e.g. "Monday"). Control character: "w"
     */
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(DateTimeFormatPart.Serializer::class)
    data object WeekDay : DateTimeFormatPart {
        override val controlCharacter: String
            get() = DateTimeEntityFormatting.WEEK_CONTROL_KEY
    }
    /**
     * Group for date-related format parts
     */
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(DateTimeFormatPart.Serializer::class)
    sealed interface Date : DateTimeFormatPart {
        /**
         * Represents short date format (e.g. "01.01.2023"). Control character: "d"
         */
        @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
        @Serializable(DateTimeFormatPart.Serializer::class)
        data object Short : Date {
            override val controlCharacter: String
                get() = DateTimeEntityFormatting.DATE_SHORT_CONTROL_KEY
        }
        /**
         * Represents long date format (e.g. "January 1, 2023"). Control character: "D"
         */
        @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
        @Serializable(DateTimeFormatPart.Serializer::class)
        data object Long : Date {
            override val controlCharacter: String
                get() = DateTimeEntityFormatting.DATE_LONG_CONTROL_KEY
        }
    }
    /**
     * Group for time-related format parts
     */
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(DateTimeFormatPart.Serializer::class)
    sealed interface Time : DateTimeFormatPart {
        /**
         * Represents short time format (e.g. "12:00"). Control character: "t"
         */
        @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
        @Serializable(DateTimeFormatPart.Serializer::class)
        data object Short : Time {
            override val controlCharacter: String
                get() = DateTimeEntityFormatting.TIME_SHORT_CONTROL_KEY
        }
        /**
         * Represents long time format (e.g. "12:00:00"). Control character: "T"
         */
        @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
        @Serializable(DateTimeFormatPart.Serializer::class)
        data object Long : Time {
            override val controlCharacter: String
                get() = DateTimeEntityFormatting.TIME_LONG_CONTROL_KEY
        }
    }

    object Serializer : KSerializer<DateTimeFormatPart> {
        override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
            "DateTimeFormatPart",
            PrimitiveKind.STRING
        )

        override fun serialize(encoder: Encoder, value: DateTimeFormatPart) {
            encoder.encodeString(value.controlCharacter)
        }

        override fun deserialize(decoder: Decoder): DateTimeFormatPart = when (val controlCharacter = decoder.decodeString()) {
            DateTimeEntityFormatting.LOCAL_CONTROL_KEY -> Relative
            DateTimeEntityFormatting.WEEK_CONTROL_KEY -> WeekDay
            DateTimeEntityFormatting.DATE_SHORT_CONTROL_KEY -> Date.Short
            DateTimeEntityFormatting.DATE_LONG_CONTROL_KEY -> Date.Long
            DateTimeEntityFormatting.TIME_SHORT_CONTROL_KEY -> Time.Short
            DateTimeEntityFormatting.TIME_LONG_CONTROL_KEY -> Time.Long
            else -> throw SerializationException("Unknown date time format part: $controlCharacter")
        }
    }
}

/**
 * Builder for [DateTimeEntityFormatting]. Use [buildDateTimeFormat] for convenience.
 * [DateTimeFormatPart.Relative] is mutually exclusive with all component parts. Component output uses Telegram's
 * required week-date-time order regardless of the order of builder calls.
 */
class TgDateTimeFormatBuilder {
    private val parts = mutableSetOf<DateTimeFormatPart>()
    
    /**
     * Selects [DateTimeFormatPart.Relative] and removes all component parts.
     */
    fun relative() = apply {
        parts.clear()
        parts.add(DateTimeFormatPart.Relative)
    }
    /**
     * Adds [DateTimeFormatPart.WeekDay] and removes [DateTimeFormatPart.Relative].
     */
    fun weekDay() = apply {
        parts.remove(DateTimeFormatPart.Relative)
        parts.add(DateTimeFormatPart.WeekDay)
    }
    /**
     * Adds [DateTimeFormatPart.Date.Short] and removes [DateTimeFormatPart.Relative] plus any other
     * [DateTimeFormatPart.Date] part.
     */
    fun dateShort() = apply {
        parts.remove(DateTimeFormatPart.Relative)
        parts.removeAll { it is DateTimeFormatPart.Date }
        parts.add(DateTimeFormatPart.Date.Short)
    }
    /**
     * Adds [DateTimeFormatPart.Time.Short] and removes [DateTimeFormatPart.Relative] plus any other
     * [DateTimeFormatPart.Time] part.
     */
    fun timeShort() = apply {
        parts.remove(DateTimeFormatPart.Relative)
        parts.removeAll { it is DateTimeFormatPart.Time }
        parts.add(DateTimeFormatPart.Time.Short)
    }
    /**
     * Adds [DateTimeFormatPart.Date.Long] and removes [DateTimeFormatPart.Relative] plus any other
     * [DateTimeFormatPart.Date] part.
     */
    fun dateLong() = apply {
        parts.remove(DateTimeFormatPart.Relative)
        parts.removeAll { it is DateTimeFormatPart.Date }
        parts.add(DateTimeFormatPart.Date.Long)
    }
    /**
     * Adds [DateTimeFormatPart.Time.Long] and removes [DateTimeFormatPart.Relative] plus any other
     * [DateTimeFormatPart.Time] part.
     */
    fun timeLong() = apply {
        parts.remove(DateTimeFormatPart.Relative)
        parts.removeAll { it is DateTimeFormatPart.Time }
        parts.add(DateTimeFormatPart.Time.Long)
    }
    
    /**
     * Builds a safe [DateTimeEntityFormatting] value in Telegram's required canonical order.
     */
    fun build(): DateTimeEntityFormatting = if (DateTimeFormatPart.Relative in parts) {
        DateTimeEntityFormatting.local
    } else {
        DateTimeEntityFormatting(
            useWeek = DateTimeFormatPart.WeekDay in parts,
            useDateLong = when {
                DateTimeFormatPart.Date.Long in parts -> true
                DateTimeFormatPart.Date.Short in parts -> false
                else -> null
            },
            useTimeLong = when {
                DateTimeFormatPart.Time.Long in parts -> true
                DateTimeFormatPart.Time.Short in parts -> false
                else -> null
            }
        )
    }
}

/**
 * Builds [DateTimeEntityFormatting] with the safe [TgDateTimeFormatBuilder] contract.
 */
fun buildDateTimeFormat(block: TgDateTimeFormatBuilder.() -> Unit): DateTimeEntityFormatting =
    TgDateTimeFormatBuilder().apply(block).build()
