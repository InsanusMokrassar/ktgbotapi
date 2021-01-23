package dev.inmo.tgbotapi.utils.passport

import dev.inmo.micro_utils.common.toArrayBuffer
import dev.inmo.micro_utils.common.toByteArray
import dev.inmo.micro_utils.crypto.encodeBase64
import dev.inmo.micro_utils.crypto.encodeBase64String
import org.khronos.webgl.ArrayBuffer
import kotlin.js.Json
import kotlin.js.json

private external interface InternalBuffer {
    val buffer: ArrayBuffer
}

private external object Buffer {
    fun from(data: String): InternalBuffer
}

private external interface CryptoConstants {
    val RSA_PKCS1_OAEP_PADDING: Any
}
private external interface Crypto {
    val constants: CryptoConstants
    fun privateDecrypt(
        key: Json,
        data: InternalBuffer
    ): InternalBuffer
    fun privateEncrypt(
        key: Json,
        data: InternalBuffer
    ) : InternalBuffer

}

@JsModule("crypto")
@JsNonModule
private external val crypto: Crypto

actual class DecryptionContext actual constructor(key: String) {
    private val key = json(
        "key" to key,
        "padding" to crypto.constants.RSA_PKCS1_OAEP_PADDING
    )
    actual fun ByteArray.decrypt(): ByteArray {
        return crypto.privateDecrypt(
            key,
            Buffer.from(encodeBase64String())
        ).buffer.toByteArray()
    }
}
