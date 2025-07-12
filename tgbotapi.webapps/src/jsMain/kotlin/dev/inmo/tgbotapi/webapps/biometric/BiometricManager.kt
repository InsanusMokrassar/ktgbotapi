@file:Suppress("unused")

package dev.inmo.tgbotapi.webapps.biometric

import kotlinx.coroutines.CompletableDeferred

@Suppress("INLINE_CLASS_IN_EXTERNAL_DECLARATION_WARNING")
external interface BiometricManager {
    val isInited: Boolean
    val isBiometricAvailable: Boolean
    val isAccessRequested: Boolean
    val isAccessGranted: Boolean
    val isBiometricTokenSaved: Boolean
    val deviceId: DeviceId?
    val biometricType: BiometricType

    fun init(callback: (() -> Unit) = definedExternally): BiometricManager
    fun requestAccess(params: BiometricRequestAccessParams, callback: ((Boolean) -> Unit) = definedExternally): BiometricManager
    fun authenticate(params: BiometricAuthenticateParams, callback: ((Boolean, String?) -> Unit) = definedExternally): BiometricManager
    fun updateBiometricToken(token: String, callback: ((Boolean) -> Unit) = definedExternally): BiometricManager
    fun openSettings(): BiometricManager
}

private suspend inline fun <T> doWithAsyncJob(
    action: (CompletableDeferred<T>) -> BiometricManager
): T {
    val async = CompletableDeferred<T>()
    action(async)
    return async.await()
}

suspend fun BiometricManager.initSuspend() = doWithAsyncJob {
    init {
        it.complete(Unit)
    }
}

suspend fun BiometricManager.requestAccessSuspend(params: BiometricRequestAccessParams) = doWithAsyncJob {
    requestAccess(params) { success ->
        it.complete(success)
    }
}

suspend fun BiometricManager.authenticateSuspend(params: BiometricAuthenticateParams) = doWithAsyncJob {
    authenticate(params) { _, token ->
        it.complete(token)
    }
}

suspend fun BiometricManager.updateBiometricTokenSuspend(token: String) = doWithAsyncJob {
    updateBiometricToken(token) { success ->
        it.complete(success)
    }
}
