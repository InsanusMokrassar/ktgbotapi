package com.github.insanusmokrassar.TelegramBotAPI.types

import com.github.insanusmokrassar.TelegramBotAPI.NonstrictTestsJsonFormat
import com.soywiz.klock.DateTime
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.Serializable
import kotlin.test.Test
import kotlin.test.assertEquals

private val dateTimeUnix = DateTime.nowUnixLong()
private val dateTimeMillis = dateTimeUnix * 1000
private val dateTime = DateTime(dateTimeMillis)

@ImplicitReflectionSerializer
class TelegramDateTests {
    @Serializable
    data class Example(
        val dateTime: TelegramDate
    )
    @Test
    fun `Serializtion of TelegramDate is working correctly`() {
        val example = Example(TelegramDate(dateTimeUnix))

        val stringified = NonstrictTestsJsonFormat.stringify(Example.serializer(), example)
        assertEquals("{\"dateTime\":$dateTimeUnix}", stringified)

        val deserialized = NonstrictTestsJsonFormat.parse(Example.serializer(), stringified)
        assertEquals(example, deserialized)

        assertEquals(dateTime, deserialized.dateTime.asDate)
    }
}
