package dev.inmo.tgbotapi.types

import dev.inmo.tgbotapi.TestsJsonFormat
import dev.inmo.tgbotapi.types.actions.*
import kotlinx.serialization.Serializable
import kotlin.test.Test
import kotlin.test.assertEquals

class BotActionTests {
    @Serializable
    data class Example(
        val botAction: BotAction
    )

    private fun checkBotAction(example: Example, sourceAction: BotAction) {
        assertEquals(
            sourceAction.actionName,
            when (example.botAction) {
                TypingAction -> example.botAction.actionName
                UploadPhotoAction -> example.botAction.actionName
                RecordVideoAction -> example.botAction.actionName
                UploadVideoAction -> example.botAction.actionName
                RecordVoiceAction -> example.botAction.actionName
                UploadVoiceAction -> example.botAction.actionName
                UploadDocumentAction -> example.botAction.actionName
                FindLocationAction -> example.botAction.actionName
                RecordVideoNoteAction -> example.botAction.actionName
                UploadVideoNoteAction -> example.botAction.actionName
            }
        )
    }

    private fun checkBotActionSerializeDeserialize(example: Example) {
        val stringified = TestsJsonFormat.encodeToString(Example.serializer(), example)
        assertEquals("{\"botAction\":\"${example.botAction.actionName}\"}", stringified)

        val deserialized = TestsJsonFormat.decodeFromString(Example.serializer(), stringified)

        assertEquals(example, deserialized)

        checkBotAction(deserialized, example.botAction)
    }

    @Test
    fun `BotAction_correctly_serialized_and_deserialized`() {
        fun BotAction.example() = Example(this)
        listOf(
            TypingAction.example(),
            UploadPhotoAction.example(),
            RecordVideoAction.example(),
            UploadVideoAction.example(),
            RecordVoiceAction.example(),
            UploadVoiceAction.example(),
            UploadDocumentAction.example(),
            FindLocationAction.example(),
            RecordVideoNoteAction.example(),
            UploadVideoNoteAction.example()
        ).forEach {
            checkBotActionSerializeDeserialize(it)
        }
    }
}
