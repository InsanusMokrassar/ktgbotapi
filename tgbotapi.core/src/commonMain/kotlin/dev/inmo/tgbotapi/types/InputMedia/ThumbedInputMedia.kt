package dev.inmo.tgbotapi.types.InputMedia

import dev.inmo.tgbotapi.requests.abstracts.InputFile

sealed interface ThumbedInputMedia : InputMedia {
    val thumb: InputFile?
}
