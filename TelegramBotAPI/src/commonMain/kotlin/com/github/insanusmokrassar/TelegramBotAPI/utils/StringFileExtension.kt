package com.github.insanusmokrassar.TelegramBotAPI.utils

private val extensionRegex = Regex("[^.]*$")

val String.fileExtension
    get() = extensionRegex.find(this) ?.value ?: ""
