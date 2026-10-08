package dev.inmo.tgbotapi.types

import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonPrimitive
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class DateTimeEntityFormattingTests {
    private val json = Json { }

    @Test
    fun localUsesExclusiveRelativeControlKey() {
        val format = DateTimeEntityFormatting.local

        assertEquals(DateTimeEntityFormatting.LOCAL_CONTROL_KEY, format.string)
        assertTrue(format.useLocal)
        assertFalse(format.useWeek)
        assertNull(format.useDateLong)
        assertNull(format.useTimeLong)
    }

    @Test
    fun factoryCoversEverySupportedComponentCombination() {
        val dateOptions = listOf(
            null to "",
            false to DateTimeEntityFormatting.DATE_SHORT_CONTROL_KEY,
            true to DateTimeEntityFormatting.DATE_LONG_CONTROL_KEY
        )
        val timeOptions = listOf(
            null to "",
            false to DateTimeEntityFormatting.TIME_SHORT_CONTROL_KEY,
            true to DateTimeEntityFormatting.TIME_LONG_CONTROL_KEY
        )
        var checkedCombinations = 0

        listOf(false, true).forEach { useWeek ->
            dateOptions.forEach { (useDateLong, dateKey) ->
                timeOptions.forEach { (useTimeLong, timeKey) ->
                    val expected = buildString {
                        if (useWeek) append(DateTimeEntityFormatting.WEEK_CONTROL_KEY)
                        append(dateKey)
                        append(timeKey)
                    }
                    val format = DateTimeEntityFormatting(
                        useWeek = useWeek,
                        useDateLong = useDateLong,
                        useTimeLong = useTimeLong
                    )

                    assertEquals(expected, format.string)
                    assertFalse(format.useLocal)
                    assertEquals(useWeek, format.useWeek)
                    assertEquals(useDateLong, format.useDateLong)
                    assertEquals(useTimeLong, format.useTimeLong)
                    checkedCombinations += 1
                }
            }
        }

        assertEquals(18, checkedCombinations)
    }

    @Test
    fun customConflictingKeysReportLongVariants() {
        val format = DateTimeEntityFormatting("rwdDtT")

        assertTrue(format.useLocal)
        assertTrue(format.useWeek)
        assertEquals(true, format.useDateLong)
        assertEquals(true, format.useTimeLong)
    }

    @Test
    fun serializesAsPrimitiveString() {
        val format = DateTimeEntityFormatting(useWeek = true, useDateLong = true, useTimeLong = true)

        assertEquals("\"wDT\"", json.encodeToString(DateTimeEntityFormatting.serializer(), format))
    }

    @Test
    fun customValueRoundTripsWithoutModification() {
        val format = DateTimeEntityFormatting("future-format")

        val encoded = json.encodeToString(DateTimeEntityFormatting.serializer(), format)

        assertEquals(format, json.decodeFromString(DateTimeEntityFormatting.serializer(), encoded))
    }

    @Test
    fun dateTimeFormatPartsRoundTripAsPrimitiveControlCharacters() {
        val parts = listOf(
            DateTimeFormatPart.Relative,
            DateTimeFormatPart.WeekDay,
            DateTimeFormatPart.Date.Short,
            DateTimeFormatPart.Date.Long,
            DateTimeFormatPart.Time.Short,
            DateTimeFormatPart.Time.Long
        )

        parts.forEach { part ->
            val encoded = json.encodeToString(DateTimeFormatPart.serializer(), part)
            val encodedElement = json.parseToJsonElement(encoded)

            assertEquals("\"${part.controlCharacter}\"", encoded)
            assertTrue(encodedElement is JsonPrimitive)
            assertTrue(encodedElement.isString)
            assertEquals(part, json.decodeFromString(DateTimeFormatPart.serializer(), encoded))
        }
    }

    @Test
    fun dateTimeFormatPartRejectsUnknownControlCharacter() {
        assertFailsWith<SerializationException> {
            json.decodeFromString(DateTimeFormatPart.serializer(), "\"x\"")
        }
    }

    @Test
    fun builderUsesCanonicalWeekDateTimeOrder() {
        val format = buildDateTimeFormat {
            timeLong()
            dateLong()
            weekDay()
        }

        assertEquals("wDT", format.string)
    }

    @Test
    fun builderKeepsRelativeFormatExclusive() {
        assertEquals(
            DateTimeEntityFormatting.local,
            buildDateTimeFormat {
                weekDay()
                dateLong()
                timeLong()
                relative()
            }
        )
        assertEquals(
            DateTimeEntityFormatting(useWeek = true, useDateLong = false, useTimeLong = false),
            buildDateTimeFormat {
                relative()
                timeShort()
                weekDay()
                dateShort()
            }
        )
    }
}
