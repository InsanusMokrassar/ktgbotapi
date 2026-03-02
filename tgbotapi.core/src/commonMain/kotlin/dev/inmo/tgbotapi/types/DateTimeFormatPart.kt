package dev.inmo.tgbotapi.types

import kotlinx.serialization.Serializable

/**
 * Common interface for parts of date time format. Used for [dev.inmo.tgbotapi.types.message.textsources.DateTimeTextSource]
 *
 * @see TgDateTimeFormatBuilder
 * @see buildDateTimeFormat
 */
@Serializable
sealed interface DateTimeFormatPart {
    /**
     * Character that represents this part in the format string. Used by [TgDateTimeFormatBuilder.build]
     */
    val controlCharacter: String
    /**
     * Represents relative time format (e.g. "2 hours ago"). Control character: "r"
     */
    @Serializable
    data object Relative : DateTimeFormatPart { override val controlCharacter: String get() = "r" }
    /**
     * Represents day of the week format (e.g. "Monday"). Control character: "w"
     */
    @Serializable
    data object WeekDay : DateTimeFormatPart { override val controlCharacter: String get() = "w" }
    /**
     * Group for date-related format parts
     */
    @Serializable
    sealed interface Date : DateTimeFormatPart {
        /**
         * Represents short date format (e.g. "01.01.2023"). Control character: "d"
         */
        @Serializable
        data object Short : Date { override val controlCharacter: String get() = "d" }
        /**
         * Represents long date format (e.g. "January 1, 2023"). Control character: "D"
         */
        @Serializable
        data object Long : Date { override val controlCharacter: String get() = "D" }
    }
    /**
     * Group for time-related format parts
     */
    @Serializable
    sealed interface Time : DateTimeFormatPart {
        /**
         * Represents short time format (e.g. "12:00"). Control character: "t"
         */
        @Serializable
        data object Short : Time { override val controlCharacter: String get() = "t" }
        /**
         * Represents long time format (e.g. "12:00:00"). Control character: "T"
         */
        @Serializable
        data object Long : Time { override val controlCharacter: String get() = "T" }
    }
}

/**
 * Builder for date time format string. Use [buildDateTimeFormat] for convenience
 */
class TgDateTimeFormatBuilder {
    private val parts = mutableSetOf<DateTimeFormatPart>()
    
    /**
     * Adds [DateTimeFormatPart.Relative] to the format
     */
    fun relative() = apply { parts.add(DateTimeFormatPart.Relative) }
    /**
     * Adds [DateTimeFormatPart.WeekDay] to the format
     */
    fun weekDay() = apply { parts.add(DateTimeFormatPart.WeekDay) }
    /**
     * Adds [DateTimeFormatPart.Date.Short] to the format. Removes any other [DateTimeFormatPart.Date] parts
     */
    fun dateShort() = apply {
        parts.removeAll { it is DateTimeFormatPart.Date }
        parts.add(DateTimeFormatPart.Date.Short)
    }
    /**
     * Adds [DateTimeFormatPart.Time.Short] to the format. Removes any other [DateTimeFormatPart.Time] parts
     */
    fun timeShort() = apply {
        parts.removeAll { it is DateTimeFormatPart.Time }
        parts.add(DateTimeFormatPart.Time.Short)
    }
    /**
     * Adds [DateTimeFormatPart.Date.Long] to the format. Removes any other [DateTimeFormatPart.Date] parts
     */
    fun dateLong() = apply {
        parts.removeAll { it is DateTimeFormatPart.Date }
        parts.add(DateTimeFormatPart.Date.Long)
    }
    /**
     * Adds [DateTimeFormatPart.Time.Long] to the format. Removes any other [DateTimeFormatPart.Time] parts
     */
    fun timeLong() = apply {
        parts.removeAll { it is DateTimeFormatPart.Time }
        parts.add(DateTimeFormatPart.Time.Long)
    }
    
    /**
     * Joins all added parts into a single format string
     */
    fun build() = parts.joinToString("")
}

/**
 * Convenient way to build date time format string
 */
fun buildDateTimeFormat(block: TgDateTimeFormatBuilder.() -> Unit): String = TgDateTimeFormatBuilder().apply(block).build()
