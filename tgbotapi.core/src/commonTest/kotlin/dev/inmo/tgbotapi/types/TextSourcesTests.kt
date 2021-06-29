package dev.inmo.tgbotapi.types

import dev.inmo.tgbotapi.TestsJsonFormat
import dev.inmo.tgbotapi.extensions.utils.formatting.*
import dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSourceSerializer
import dev.inmo.tgbotapi.utils.extensions.makeString
import kotlinx.serialization.builtins.ListSerializer
import kotlin.test.Test
import kotlin.test.assertEquals

class TextSourcesTests {
    @Test
    fun testThatTextSourcesSerializedCorrectly() {
        val testList = buildEntities {
            bold(
                buildEntities {
                    italic("It")
                    regular(" ")
                    link("is example", "https://is.example")
                }
            )
            regular(" ")
            underline("of")
            regular(" ")
            strikethrough("complex")
            regular(" ")
            pre("text", "kotlin")
        }
        val serialized = TestsJsonFormat.encodeToString(ListSerializer(TextSourceSerializer), testList)
        val deserialized = TestsJsonFormat.decodeFromString(
            ListSerializer(TextSourceSerializer),
            serialized
        )
        assertEquals(testList, deserialized)
        assertEquals(testList.makeString(), deserialized.makeString())
    }
}
