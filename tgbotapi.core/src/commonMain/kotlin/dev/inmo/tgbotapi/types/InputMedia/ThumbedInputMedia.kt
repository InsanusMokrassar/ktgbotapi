package dev.inmo.tgbotapi.types.InputMedia

import dev.inmo.tgbotapi.requests.abstracts.InputFile

interface ThumbedInputMedia : InputMedia {
    val thumb: InputFile?
}
