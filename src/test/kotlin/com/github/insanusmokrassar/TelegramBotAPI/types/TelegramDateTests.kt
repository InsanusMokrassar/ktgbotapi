package com.github.insanusmokrassar.TelegramBotAPI.types

import com.soywiz.klock.DateTime
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.util.concurrent.TimeUnit
import kotlin.test.Test
import kotlin.test.assertEquals

private val dateTimeMillis = System.currentTimeMillis()
private val dateTimeUnix = TimeUnit.MILLISECONDS.toSeconds(dateTimeMillis)
private val dateTime = DateTime(TimeUnit.SECONDS.toMillis(dateTimeUnix))

@ImplicitReflectionSerializer
class TelegramDateTests {
    @Serializable
    data class Example(
        val dateTime: TelegramDate
    )
    @Test
    fun `Serializtion of TelegramDate is working correctly`() {
        val example = Example(TelegramDate(dateTimeUnix))

        val stringified = Json.plain.stringify(Example.serializer(), example)
        assertEquals("{\"dateTime\":$dateTimeUnix}", stringified)

        val deserialized = Json.plain.parse(Example.serializer(), stringified)
        assertEquals(example, deserialized)

        assertEquals(dateTime, deserialized.dateTime.asDate)
    }
}
