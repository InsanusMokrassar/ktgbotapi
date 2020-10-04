package dev.inmo.tgbotapi.utils

private val filenameRegex = Regex("[^/]*$")
private val extensionRegex = Regex("[^.]*$")

/**
 * File name like hello.jpg
 */
typealias FileName = String

val String.filenameFromUrl: FileName
    get() = filenameRegex.find(this) ?.value ?: ""

val String.fileExtension
    get() = extensionRegex.find(this) ?.value ?: ""
