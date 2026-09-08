package dev.inmo.tgbotapi.types

import dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.DisabledInlineKeyboardButton
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.InlineKeyboardButton
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.buttons.ReplyKeyboardMarkup
import dev.inmo.tgbotapi.types.buttons.SimpleKeyboardButton
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ReplyMarkupSerializationTest {
    private val json = Json { encodeDefaults = false }

    @Test
    fun disabledInlineKeyboardButtonUsesEmptyDisabledObjectAndRoundTrips() {
        val value: InlineKeyboardButton = DisabledInlineKeyboardButton("Unavailable")

        val encoded = json.encodeToString(value)
        val encodedObject = json.parseToJsonElement(encoded).jsonObject

        assertEquals("{}", encodedObject.getValue(disabledField).toString())
        assertEquals(value, json.decodeFromString<InlineKeyboardButton>(encoded))
    }

    @Test
    fun inlineKeyboardMarkupSerializesForceReplyAndPreservesItWhenCombined() {
        val value = InlineKeyboardMarkup(
            keyboard = listOf(listOf(DisabledInlineKeyboardButton("Unavailable"))),
            forceReply = true
        )

        val encodedObject = json.parseToJsonElement(json.encodeToString(value)).jsonObject

        assertEquals(true, encodedObject.getValue(forceReplyField).toString().toBoolean())
        assertEquals(value, json.decodeFromString<InlineKeyboardMarkup>(encodedObject.toString()))
        assertTrue((value + InlineKeyboardMarkup(listOf(listOf(DisabledInlineKeyboardButton("Other"))))).forceReply == true)
        assertTrue((value - InlineKeyboardMarkup(listOf(listOf(DisabledInlineKeyboardButton("Unavailable"))))).forceReply == true)
    }

    @Test
    fun replyKeyboardMarkupSerializesForceReplyAndPreservesItWhenCombined() {
        val value = ReplyKeyboardMarkup(
            keyboard = listOf(listOf(SimpleKeyboardButton("Reply"))),
            forceReply = true
        )

        val encodedObject = json.parseToJsonElement(json.encodeToString(value)).jsonObject

        assertEquals(true, encodedObject.getValue(forceReplyField).toString().toBoolean())
        assertEquals(value, json.decodeFromString<ReplyKeyboardMarkup>(encodedObject.toString()))
        assertTrue((value + ReplyKeyboardMarkup(listOf(listOf(SimpleKeyboardButton("Other"))))).forceReply == true)
        assertTrue((value - ReplyKeyboardMarkup(listOf(listOf(SimpleKeyboardButton("Reply"))))).forceReply == true)
    }
}
