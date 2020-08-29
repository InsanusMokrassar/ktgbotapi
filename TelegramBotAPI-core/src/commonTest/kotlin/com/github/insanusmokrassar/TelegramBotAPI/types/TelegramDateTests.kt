package com.github.insanusmokrassar.TelegramBotAPI.types

import com.github.insanusmokrassar.TelegramBotAPI.TestsJsonFormat
import com.soywiz.klock.DateTime
import kotlinx.serialization.Serializable
import kotlin.test.Test
import kotlin.test.assertEquals

private val dateTimeUnix = DateTime.nowUnixLong()
private val dateTimeMillis = dateTimeUnix * 1000
private val dateTime = DateTime(dateTimeMillis)

class TelegramDateTests {
    @Serializable
    data class Example(
        val dateTime: TelegramDate
    )
    @Test
    fun `Serializtion_of_TelegramDate_is_working_correctly`() {
        val example = Example(TelegramDate(dateTimeUnix))

        val stringified = TestsJsonFormat.encodeToString(Example.serializer(), example)
        assertEquals("{\"dateTime\":$dateTimeUnix}", stringified)

        val deserialized = TestsJsonFormat.decodeFromString(Example.serializer(), stringified)
        assertEquals(example, deserialized)

        assertEquals(dateTime, deserialized.dateTime.asDate)
    }
}
