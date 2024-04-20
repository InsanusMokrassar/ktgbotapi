package dev.inmo.tgbotapi.webapps.biometric

import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import kotlin.js.json

external interface BiometricAuthenticateParams {
    val reason: String?
}

fun BiometricAuthenticateParams(
    reason: String? = null
) = buildJsonObject {
    reason ?.let { put("reason", JsonPrimitive(it)) }
}.unsafeCast<BiometricAuthenticateParams>()
