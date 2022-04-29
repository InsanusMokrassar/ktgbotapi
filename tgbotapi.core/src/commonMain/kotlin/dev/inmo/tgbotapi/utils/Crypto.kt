package dev.inmo.tgbotapi.utils

import dev.inmo.micro_utils.crypto.SourceString

internal expect fun SourceString.hmacSha256(key: String): String

internal expect fun SourceString.hex(): String
