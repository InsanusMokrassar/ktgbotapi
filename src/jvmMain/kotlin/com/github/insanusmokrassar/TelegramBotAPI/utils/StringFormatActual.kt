package com.github.insanusmokrassar.TelegramBotAPI.utils

import kotlin.text.format

actual fun String.format(vararg args: Any?): String = format(*args)
