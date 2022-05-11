package dev.inmo.tgbotapi.requests.abstracts

import java.io.File

@Deprecated("Duplacation of asMultipartFile", ReplaceWith("asMultipartFile", "dev.inmo.tgbotapi.requests.abstracts.asMultipartFile"))
fun File.toInputFile() = asMultipartFile()
