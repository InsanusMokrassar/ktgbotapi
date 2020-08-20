package com.github.insanusmokrassar.TelegramBotAPI.types.files.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.files.PhotoSize

interface ThumbedMediaFile : TelegramMediaFile {
    val thumb: PhotoSize?
}