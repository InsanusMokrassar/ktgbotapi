package dev.inmo.tgbotapi.types.files.abstracts

internal const val fileNameField = "file_name"

interface CustomNamedMediaFile {
    val fileName: String?
}