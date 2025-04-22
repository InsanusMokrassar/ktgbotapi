package dev.inmo.tgbotapi.webapps.biometric

import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject

external interface BiometricRequestAccessParams {
    val reason: String?
}

fun BiometricRequestAccessParams(reason: String? = null) =
    buildJsonObject {
        reason ?.let { put("reason", JsonPrimitive(it)) }
    }.unsafeCast<BiometricRequestAccessParams>()
