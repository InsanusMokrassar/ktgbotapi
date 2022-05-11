package dev.inmo.tgbotapi

import dev.inmo.tgbotapi.requests.abstracts.toInputFile
import dev.inmo.tgbotapi.types.media.MediaGroupMemberTelegramMediaSerializer
import dev.inmo.tgbotapi.types.files.PhotoSize
import dev.inmo.tgbotapi.types.message.content.PhotoContent
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

private val nonstrictJsonFormat = Json {
    isLenient = true
    ignoreUnknownKeys = true
    allowSpecialFloatingPointValues = true
    useArrayPolymorphism = true
    encodeDefaults = true
}

class SimpleInputFilesTest {
    @Test
    fun test_that_creating_of_photo_and_converting_to_input_media_working_correctly() {
        val photoContent = PhotoContent(
            listOf(
                PhotoSize("example_file_id".toInputFile(), "example_unique_file_id", 100, 100, 100)
            )
        )
        val inputMedia = photoContent.toMediaGroupMemberTelegramMedia()
        assertEquals(photoContent.media.fileId, inputMedia.file)
        val encoded = nonstrictJsonFormat.encodeToString(
            MediaGroupMemberTelegramMediaSerializer,
            inputMedia
        )
        assertEquals(
            inputMedia,
            nonstrictJsonFormat.decodeFromString(
                MediaGroupMemberTelegramMediaSerializer,
                encoded
            )
        )
    }
}
