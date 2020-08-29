package com.github.insanusmokrassar.TelegramBotAPI

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.toInputFile
import com.github.insanusmokrassar.TelegramBotAPI.types.InputMedia.MediaGroupMemberInputMediaSerializer
import com.github.insanusmokrassar.TelegramBotAPI.types.files.PhotoSize
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.media.PhotoContent
import kotlinx.serialization.json.Json
import kotlin.test.Test

private val nonstrictJsonFormat = Json {
    isLenient = true
    ignoreUnknownKeys = true
    allowSpecialFloatingPointValues = true
    useArrayPolymorphism = true
}

class SimpleInputFilesTest {
    @Test
    fun test_that_creating_of_photo_and_converting_to_input_media_working_correctly() {
        val photoContent = PhotoContent(
            listOf(
                PhotoSize("example_file_id".toInputFile(), "example_unique_file_id", 100, 100, 100)
            )
        )
        nonstrictJsonFormat.encodeToString(
            MediaGroupMemberInputMediaSerializer,
            photoContent.toMediaGroupMemberInputMedia()
        )
    }
}
