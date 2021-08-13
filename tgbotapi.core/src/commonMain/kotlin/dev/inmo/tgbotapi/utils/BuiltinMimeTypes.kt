package dev.inmo.tgbotapi.utils

sealed interface BuiltinMimeTypes {
    object Image {
        val Jpg = buildMimeType("image/jpeg")
        val Gif = buildMimeType("image/gif")
    }
    object Video {
        val MP4 = buildMimeType("video/mp4")
    }
    object Text {
        val Html = buildMimeType("text/html")
    }
    object Application {
        val Zip = buildMimeType("application/zip")
        val Pdf = buildMimeType("application/pdf")
    }
}
