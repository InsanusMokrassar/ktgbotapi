package dev.inmo.tgbotapi.utils

import dev.inmo.micro_utils.crypto.SourceBytes
import dev.inmo.micro_utils.crypto.SourceString
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

val HEX_ARRAY = "0123456789ABCDEF".toCharArray()

private fun SourceBytes.hex(): String {
    val hexChars = CharArray(size * 2)
    for (j in indices) {
        val v: Int = this[j].toInt() and 0xFF
        hexChars[j * 2] = HEX_ARRAY[v ushr 4]
        hexChars[j * 2 + 1] = HEX_ARRAY[v and 0x0F]
    }
    return String(hexChars)
}

actual fun SourceString.hmacSha256(key: String): String {
    val mac = Mac.getInstance("HmacSHA256")

    val secretKey = SecretKeySpec(key.toByteArray(), "HmacSHA256")
    mac.init(secretKey)

    return mac.doFinal(toByteArray()).hex()
}

actual fun SourceString.hex(): String = toByteArray().hex()
