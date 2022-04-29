package dev.inmo.tgbotapi.utils

import dev.inmo.micro_utils.crypto.SourceString
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

actual fun SourceString.hmacSha256(key: String): String {
    val mac = Mac.getInstance("HmacSHA256")

    val secretKey = SecretKeySpec(key.toByteArray(), "HmacSHA256")
    mac.init(secretKey)

    return mac.doFinal(toByteArray()).hex()
}
