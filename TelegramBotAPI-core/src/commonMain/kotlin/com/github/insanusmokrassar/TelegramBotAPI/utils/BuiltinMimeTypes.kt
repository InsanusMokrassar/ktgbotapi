package com.github.insanusmokrassar.TelegramBotAPI.utils

object BuiltinMimeTypes {
    object Image {
        val Jpg = buildMimeType("image/jpeg")
        val Gif = buildMimeType("image/gif")
    }
    object Video {
        val MP4 = buildMimeType("video/mp4")
    }
}
