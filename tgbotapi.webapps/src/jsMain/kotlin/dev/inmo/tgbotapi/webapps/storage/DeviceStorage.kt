package dev.inmo.tgbotapi.webapps.storage

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred

/**
 * This object is used to access the device's local storage.
 *
 * See [https://core.telegram.org/bots/webapps#devicestorage](https://core.telegram.org/bots/webapps#devicestorage) for more information.
 */
external interface DeviceStorage {
    /**
     * Stores a key-value pair.
     *
     * @param key The key to store the value under.
     * @param value The value to store.
     * @param callback A callback function that is called when the operation is complete. The first argument is an error object, if any, and the second argument is a boolean indicating whether the operation was successful.
     */
    fun setItem(key: String, value: String, callback: (DeviceStorageErrorMessage?, Boolean?) -> Unit): DeviceStorage

    /**
     * Retrieves the value associated with a key.
     *
     * @param key The key to retrieve the value for.
     * @param callback A callback function that is called when the operation is complete. The first argument is an error object, if any, and the second argument is the value associated with the key, or null if the key is not found.
     */
    fun getItem(key: String, callback: (DeviceStorageErrorMessage?, String?) -> Unit): DeviceStorage

    /**
     * Removes the key-value pair associated with a key.
     *
     * @param key The key to remove.
     * @param callback A callback function that is called when the operation is complete. The first argument is an error object, if any, and the second argument is a boolean indicating whether the operation was successful.
     */
    fun removeItem(key: String, callback: (DeviceStorageErrorMessage?, Boolean?) -> Unit): DeviceStorage

    /**
     * Clears all key-value pairs from the storage.
     *
     * @param callback A callback function that is called when the operation is complete. The first argument is an error object, if any, and the second argument is a boolean indicating whether the operation was successful.
     */
    fun clear(callback: (DeviceStorageErrorMessage?, Boolean?) -> Unit): DeviceStorage
}

/**
 * This value class represent strongly-typed errors of [DeviceStorage] operations and required to separate it with string
 * args
 */
value class DeviceStorageErrorMessage(val text: String) {
    internal fun deviceStorageError() = DeviceStorageError(text)
}

/**
 * This class will be used in [setWithResult] and other extensions for [DeviceStorage] to represent special error happen
 * in operations of [DeviceStorage]
 */
class DeviceStorageError(message: String) : IllegalStateException(message)

/**
 * Stores a key-value pair. This function uses a [CompletableDeferred] to handle the asynchronous callback and returns a [Result] object.
 *
 * @param key The key to store the value under.
 * @param value The value to store.
 * @return A [Result] object containing the result of the operation.
 */
suspend fun DeviceStorage.setWithResult(key: String, value: String): Result<Boolean> {
    val deferred = CompletableDeferred<Result<Boolean>>()

    setItem(key, value) { error, result ->
        if (error == null) {
            deferred.complete(Result.success(result ?: false))
        } else {
            deferred.complete(Result.failure(error.deviceStorageError()))
        }
    }

    return deferred.await()
}

/**
 * Retrieves the value associated with a key.  This function uses a [CompletableDeferred] to handle the asynchronous callback and returns a [Result] object.
 *
 * @param key The key to retrieve the value for.
 * @return A [Result] object containing the result of the operation.
 */
suspend fun DeviceStorage.getWithResult(key: String): Result<String?> {
    val deferred = CompletableDeferred<Result<String?>>()

    getItem(key) { error, result ->
        if (error == null) {
            deferred.complete(Result.success(result))
        } else {
            deferred.complete(Result.failure(error.deviceStorageError()))
        }
    }

    return deferred.await()
}

/**
 * Removes the key-value pair associated with a key. This function uses a [CompletableDeferred] to handle the asynchronous callback and returns a [Result] object.
 *
 * @param key The key to remove.
 * @return A [Result] object containing the result of the operation.
 */
suspend fun DeviceStorage.removeWithResult(key: String): Result<Boolean> {
    val deferred = CompletableDeferred<Result<Boolean>>()

    removeItem(key) { error, result ->
        if (error == null) {
            deferred.complete(Result.success(result ?: false))
        } else {
            deferred.complete(Result.failure(error.deviceStorageError()))
        }
    }

    return deferred.await()
}

/**
 * Clears all key-value pairs from the storage. This function uses a [CompletableDeferred] to handle the asynchronous callback and returns a [Result] object.
 *
 * @return A [Result] object containing the result of the operation.
 */
suspend fun DeviceStorage.clearWithResult(): Result<Boolean> {
    val deferred = CompletableDeferred<Result<Boolean>>()

    clear { error, result ->
        if (error == null) {
            deferred.complete(Result.success(result ?: false))
        } else {
            deferred.complete(Result.failure(error.deviceStorageError()))
        }
    }

    return deferred.await()
}

/**
 * Stores a key-value pair. This function suspends until the result is available and returns the result directly or throws an exception if an error occurred.
 *
 * @param key The key to store the value under.
 * @param value The value to store.
 * @return Boolean indicating whether the operation was successful.
 * @throws DeviceStorageError If an error occurs during the operation.
 */
suspend fun DeviceStorage.setItem(key: String, value: String): Boolean {
    return setWithResult(key, value).getOrThrow()
}

/**
 * Retrieves the value associated with a key. This function suspends until the result is available and returns the result directly or throws an exception if an error occurred.
 *
 * @param key The key to retrieve the value for.
 * @return The value associated with the key, or null if the key is not found.
 * @throws IllegalStateException If an error occurs during the operation.
 */
suspend fun DeviceStorage.getItem(key: String): String? {
    return getWithResult(key).getOrThrow()
}

/**
 * Removes the key-value pair associated with a key. This function suspends until the result is available and returns the result directly or throws an exception if an error occurred.
 *
 * @param key The key to remove.
 * @return Boolean indicating whether the operation was successful.
 * @throws DeviceStorageError If an error occurs during the operation.
 */
suspend fun DeviceStorage.removeItem(key: String): Boolean {
    return removeWithResult(key).getOrThrow()
}

/**
 * Clears all key-value pairs from the storage. This function suspends until the result is available and returns the result directly or throws an exception if an error occurred.
 *
 * @return Boolean indicating whether the operation was successful.
 * @throws DeviceStorageError If an error occurs during the operation.
 */
suspend fun DeviceStorage.clear(): Boolean {
    return clearWithResult().getOrThrow()
}