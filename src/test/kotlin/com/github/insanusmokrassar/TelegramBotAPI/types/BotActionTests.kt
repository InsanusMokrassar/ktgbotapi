package com.github.insanusmokrassar.TelegramBotAPI.types

import com.github.insanusmokrassar.TelegramBotAPI.types.actions.*
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

@ImplicitReflectionSerializer
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
                RecordAudioAction -> example.botAction.actionName
                UploadAudioAction -> example.botAction.actionName
                UploadDocumentAction -> example.botAction.actionName
                FindLocationAction -> example.botAction.actionName
            }
        )
    }

    private fun checkBotActionSerializeDeserialize(example: Example) {
        val stringified = Json.plain.stringify(Example.serializer(), example)
        assertEquals("{\"botAction\":\"${example.botAction.actionName}\"}", stringified)

        val deserialized = Json.plain.parse(Example.serializer(), stringified)

        assertEquals(example, deserialized)

        checkBotAction(deserialized, example.botAction)
    }

    @Test
    fun `BotAction correctly serialized and deserialized`() {
        fun BotAction.example() = Example(this)
        listOf(
            TypingAction.example(),
            UploadPhotoAction.example(),
            RecordVideoAction.example(),
            UploadVideoAction.example(),
            RecordAudioAction.example(),
            UploadAudioAction.example(),
            UploadDocumentAction.example(),
            FindLocationAction.example()
        ).forEach {
            checkBotActionSerializeDeserialize(it)
        }
    }
}
