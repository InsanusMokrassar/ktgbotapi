package dev.inmo.tgbotapi.types

import dev.inmo.micro_utils.common.Warning
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * A format string sent as the `date_time_format` wire value for a Telegram date-time entity.
 *
 * Telegram requires the value to match `r|w?[dD]?[tT]?`; the complete format specification is
 * available in the [Telegram Bot API documentation](https://core.telegram.org/bots/api#date-time-entity-formatting).
 * An empty value preserves the entity text as supplied while still allowing clients to expose the
 * associated date in the user's local format. [LOCAL_CONTROL_KEY] requests a relative-time display
 * and cannot be combined with another control key. The remaining keys optionally add a localized
 * weekday, a short or long date, and a short or long time.
 *
 * Prefer [local] or [invoke] when constructing a supported value. The public constructor accepts a
 * custom [string] for forward compatibility, but unsupported combinations can violate Telegram's
 * strict format and can consequently be rejected or rendered unexpectedly.
 *
 * @property string Exact string serialized as `date_time_format`.
 */
@Serializable
@JvmInline
value class DateTimeEntityFormatting @Warning(
    "Custom formatting may violate Telegram date-time format restrictions"
) constructor(
    val string: String
) {
    /** Whether [string] contains [LOCAL_CONTROL_KEY], which requests a relative-time display. */
    val useLocal: Boolean
        get() = LOCAL_CONTROL_KEY in string

    /** Whether [string] contains [WEEK_CONTROL_KEY], which requests the localized weekday. */
    val useWeek: Boolean
        get() = WEEK_CONTROL_KEY in string

    /**
     * `true` when [string] contains [DATE_LONG_CONTROL_KEY], `false` when [string] contains only
     * [DATE_SHORT_CONTROL_KEY], or `null` when neither date key is present.
     *
     * A custom invalid value containing both date keys reports `true`; the long key has precedence.
     */
    val useDateLong: Boolean?
        get() = when {
            DATE_LONG_CONTROL_KEY in string -> true
            DATE_SHORT_CONTROL_KEY in string -> false
            else -> null
        }

    /**
     * `true` when [string] contains [TIME_LONG_CONTROL_KEY], `false` when [string] contains only
     * [TIME_SHORT_CONTROL_KEY], or `null` when neither time key is present.
     *
     * A custom invalid value containing both time keys reports `true`; the long key has precedence.
     */
    val useTimeLong: Boolean?
        get() = when {
            TIME_LONG_CONTROL_KEY in string -> true
            TIME_SHORT_CONTROL_KEY in string -> false
            else -> null
        }

    companion object {
        /** Control key for relative-time display; a valid format cannot combine the key with another key. */
        const val LOCAL_CONTROL_KEY: String = "r"

        /** Control key for the day of the week in the user's localized language. */
        const val WEEK_CONTROL_KEY: String = "w"

        /** Control key for a short date, for example `17.03.22`. */
        const val DATE_SHORT_CONTROL_KEY: String = "d"

        /** Control key for a long date, for example `March 17, 2022`. */
        const val DATE_LONG_CONTROL_KEY: String = "D"

        /** Control key for a short time, for example `22:45`. */
        const val TIME_SHORT_CONTROL_KEY: String = "t"

        /** Control key for a long time, for example `22:45:00`. */
        const val TIME_LONG_CONTROL_KEY: String = "T"

        /** A valid relative-time format containing only [LOCAL_CONTROL_KEY]. */
        val local: DateTimeEntityFormatting = DateTimeEntityFormatting(LOCAL_CONTROL_KEY)

        /**
         * Creates a Telegram-compatible non-relative format in weekday-date-time key order.
         *
         * @param useWeek `true` adds [WEEK_CONTROL_KEY]; `false` leaves the weekday unset.
         * @param useDateLong `true` adds [DATE_LONG_CONTROL_KEY], `false` adds
         * [DATE_SHORT_CONTROL_KEY], and `null` leaves the date unset.
         * @param useTimeLong `true` adds [TIME_LONG_CONTROL_KEY], `false` adds
         * [TIME_SHORT_CONTROL_KEY], and `null` leaves the time unset.
         * @return A value matching `w?[dD]?[tT]?`; all unset parameters produce an empty format.
         */
        operator fun invoke(
            useWeek: Boolean = false,
            useDateLong: Boolean? = null,
            useTimeLong: Boolean? = null
        ): DateTimeEntityFormatting = DateTimeEntityFormatting(
            buildString {
                if (useWeek) {
                    append(WEEK_CONTROL_KEY)
                }
                when (useDateLong) {
                    true -> append(DATE_LONG_CONTROL_KEY)
                    false -> append(DATE_SHORT_CONTROL_KEY)
                    null -> Unit
                }
                when (useTimeLong) {
                    true -> append(TIME_LONG_CONTROL_KEY)
                    false -> append(TIME_SHORT_CONTROL_KEY)
                    null -> Unit
                }
            }
        )
    }

    override fun toString(): String = string
}
