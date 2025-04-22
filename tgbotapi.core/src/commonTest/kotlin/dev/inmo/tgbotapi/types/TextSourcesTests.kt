package dev.inmo.tgbotapi.types

import dev.inmo.tgbotapi.TestsJsonFormat
import dev.inmo.tgbotapi.types.message.textsources.TextSourceSerializer
import dev.inmo.tgbotapi.utils.*
import dev.inmo.tgbotapi.utils.extensions.makeString
import kotlinx.serialization.builtins.ListSerializer
import kotlin.test.Test
import kotlin.test.assertEquals

class TextSourcesTests {
    @Test
    fun testThatTextSourcesSerializedCorrectly() {
        val testList =
            buildEntities(" ") {
                bold {
                    italic("It")
                    link("is example", "https://is.example")
                }
                spoiler {
                    regular("and")
                    italic("that")
                    link("is spoiler", "https://is.example")
                }
                underline("of")
                italic(
                    buildEntities {
                        strikethrough("comp")
                        underline("lex")
                    },
                )
                pre("text", "kotlin")
            }
        val serialized = TestsJsonFormat.encodeToString(ListSerializer(TextSourceSerializer), testList)
        val deserialized =
            TestsJsonFormat.decodeFromString(
                ListSerializer(TextSourceSerializer),
                serialized,
            )
        assertEquals(testList, deserialized)
        assertEquals(testList.makeString(), deserialized.makeString())
        assertEquals("It is example and that is spoiler of complex text", testList.makeString())
    }
}
