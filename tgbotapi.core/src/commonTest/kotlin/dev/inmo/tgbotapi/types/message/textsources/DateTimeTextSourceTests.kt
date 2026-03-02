package dev.inmo.tgbotapi.types.message.textsources

import dev.inmo.tgbotapi.types.message.RawMessageEntity
import dev.inmo.tgbotapi.types.message.asTextSources
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.utils.buildEntities
import dev.inmo.tgbotapi.utils.dateTime
import kotlin.test.*

class DateTimeTextSourceTests {
    @Test
    fun testDateTimeTextSourceFormatting() {
        val unix = 1714560000L
        val format = "r"
        val text = "some date"
        val source = DateTimeTextSource(text, unix, format)

        assertEquals("![$text](tg://time?unix=$unix&format=$format)", source.markdown)
        assertEquals("![$text](tg://time?unix=$unix&format=$format)", source.markdownV2)
        assertEquals("<tg-time unix=\"$unix\" format=\"$format\">$text</tg-time>", source.html)
    }
    @Test
    fun testDateTimeTextSourceFormattingWithoutFormat() {
        val unix = 1714560000L
        val format = null
        val text = "some date"
        val source = DateTimeTextSource(text, unix, format)

        assertEquals("![$text](tg://time?unix=$unix)", source.markdown)
        assertEquals("![$text](tg://time?unix=$unix)", source.markdownV2)
        assertEquals("<tg-time unix=\"$unix\">$text</tg-time>", source.html)
    }

    @Test
    fun testDateTimeTextSourceInRawMessageEntity() {
        val sourceText = "date: 2024-05-01"
        val unix = 1714560000L
        val format = "wd"
        val entities = listOf(
            RawMessageEntity("date_time", 6, 10, unix_time = unix, date_time_format = format)
        )
        val textSources = entities.asTextSources(sourceText)
        
        assertEquals(2, textSources.size)
        assertTrue(textSources[0] is RegularTextSource)
        assertTrue(textSources[1] is DateTimeTextSource)
        
        val dateTimeSource = textSources[1] as DateTimeTextSource
        assertEquals("2024-05-01", dateTimeSource.source)
        assertEquals(unix, dateTimeSource.unixTimeStamp)
        assertEquals(format, dateTimeSource.dateTimeFormat)
        
        val backToEntities = dateTimeSource.toRawMessageEntities(6)
        assertEquals(1, backToEntities.size)
        val entity = backToEntities[0]
        assertEquals("date_time", entity.type)
        assertEquals(6, entity.offset)
        assertEquals(10, entity.length)
        assertEquals(unix, entity.unix_time)
        assertEquals(format, entity.date_time_format)
    }

    @Test
    fun testDateTimeTextSourceInRawMessageEntityWithNullFormat() {
        val sourceText = "date: 2024-05-01"
        val unix = 1714560000L
        val format = null
        val entities = listOf(
            RawMessageEntity("date_time", 6, 10, unix_time = unix, date_time_format = format)
        )
        val textSources = entities.asTextSources(sourceText)

        assertEquals(2, textSources.size)
        assertTrue(textSources[0] is RegularTextSource)
        assertTrue(textSources[1] is DateTimeTextSource)

        val dateTimeSource = textSources[1] as DateTimeTextSource
        assertEquals("2024-05-01", dateTimeSource.source)
        assertEquals(unix, dateTimeSource.unixTimeStamp)
        assertEquals(format, dateTimeSource.dateTimeFormat)

        val backToEntities = dateTimeSource.toRawMessageEntities(6)
        assertEquals(1, backToEntities.size)
        val entity = backToEntities[0]
        assertEquals("date_time", entity.type)
        assertEquals(6, entity.offset)
        assertEquals(10, entity.length)
        assertEquals(unix, entity.unix_time)
        assertEquals(format, entity.date_time_format)
    }

    @Test
    fun testDateTimeInEntitiesBuilder() {
        val unix = 1714560000L
        val format = "D"
        val sources = buildEntities {
            dateTime("today", unix, format)
        }
        assertEquals(1, sources.size)
        val source = sources[0] as DateTimeTextSource
        assertEquals("today", source.source)
        assertEquals(unix, source.unixTimeStamp)
        assertEquals(format, source.dateTimeFormat)
    }
}
