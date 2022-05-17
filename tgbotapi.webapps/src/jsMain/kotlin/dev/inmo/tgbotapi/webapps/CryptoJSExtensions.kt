package dev.inmo.tgbotapi.webapps

import dev.inmo.micro_utils.crypto.CryptoJs

@Deprecated("Useless")
fun CryptoJs.HmacSHA256(text: String, key: String) = this.asDynamic().HmacSHA256(text, key).unsafeCast<String>()

@Deprecated("Useless")
fun CryptoJs.hex(text: String) = this.asDynamic().format.Hex(text).unsafeCast<String>()
