package dev.inmo.tgbotapi.utils

import dev.inmo.micro_utils.crypto.CryptoJS
import dev.inmo.micro_utils.crypto.SourceString

actual fun SourceString.hmacSha256(key: String) = CryptoJS.asDynamic().HmacSHA256(this, key).toString().unsafeCast<String>()
