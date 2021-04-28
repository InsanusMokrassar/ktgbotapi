package dev.inmo.tgbotapi.types

import dev.inmo.tgbotapi.CommonAbstracts.TextSource
import dev.inmo.tgbotapi.CommonAbstracts.makeString
import dev.inmo.tgbotapi.TestsJsonFormat
import dev.inmo.tgbotapi.extensions.utils.formatting.*
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
        val serialized = TestsJsonFormat.encodeToString(ListSerializer(TextSource.serializer()), testList)
        val deserialized = TestsJsonFormat.decodeFromString(
            ListSerializer(TextSource.serializer()),
            serialized
        )
        assertEquals(testList, deserialized)
        assertEquals(testList.makeString(), deserialized.makeString())
    }
}