package dev.inmo.tgbotapi.webapps.storage

import dev.inmo.micro_utils.common.Either
import dev.inmo.micro_utils.common.either
import kotlinx.coroutines.CompletableDeferred

/**
 * This class provides access to the secure local storage, which is persistent and
 * tied to the user's Telegram account. Data stored in secure storage is accessible
 * only to the Web App that saved it.
 *
 * Use this storage to save sensitive data like access tokens or user preferences
 * that should persist across sessions. Keep in mind that storage size is limited
 * to 2 kilobytes.
 *
 * For non-sensitive data, consider using [DeviceStorage].
 *
 * @see [https://core.telegram.org/bots/webapps#securestorage](https://core.telegram.org/bots/webapps#securestorage)
 */
external class SecureStorage {
    /**
     * Stores a key-value pair in secure storage.
     *
     * @param key The key to store the value under.
     * @param value The value to store.
     * @param callback A callback function that is called when the operation is complete.
     *  The first argument is an error object, if any, and the second argument is a boolean
     *  indicating whether the operation was successful.
     */
    fun setItem(key: String, value: String, callback: (error: Throwable?, isSuccessful: Boolean) -> Unit)

    /**
     * Retrieves the value associated with a key from secure storage.
     *
     * @param key The key to retrieve the value for.
     * @param callback A callback function that is called when the operation is complete.
     *  The first argument is an error object, if any, the second argument is the value
     *  associated with the key, or null if the key is not found, and the third argument
     *  indicates whether the value can be restored.
     */
    fun getItem(key: String, callback: (error: Throwable?, value: String?, canBeRestored: Boolean?) -> Unit)

    /**
     * Restores the value associated with a key in secure storage. This is useful if the
     * value was previously removed using [removeItem] and needs to be retrieved again.
     * Note that restoring a value is only possible if it hasn't been overwritten
     * by a new value for the same key.
     *
     * @param key The key to restore the value for.
     * @param callback A callback function that is called when the operation is complete.
     *  The first argument is an error object, if any, and the second argument is the restored
     *  value, or null if the key is not found or cannot be restored.
     */
    fun restoreItem(key: String, callback: (error: Throwable?, value: String?) -> Unit)

    /**
     * Removes the key-value pair associated with a key from secure storage.
     *
     * @param key The key to remove.
     * @param callback A callback function that is called when the operation is complete.
     *  The first argument is an error object, if any, and the second argument is a boolean
     *  indicating whether the operation was successful.
     */
    fun removeItem(key: String, callback: (error: Throwable?, isSuccessful: Boolean) -> Unit)

    /**
     * Clears all key-value pairs from the secure storage.
     *
     * @param callback A callback function that is called when the operation is complete.
     *  The first argument is an error object, if any, and the second argument is a boolean
     *  indicating whether the operation was successful.
     */
    fun clear(callback: (error: Throwable?, isSuccessful: Boolean) -> Unit)
}

/**
 * Stores a key-value pair in secure storage using a [CompletableDeferred] and returns a [Result].
 *
 * @param key The key to store the value under.
 * @param value The value to store.
 * @return A [Result] object containing the result of the operation.
 */
suspend fun SecureStorage.setWithResult(key: String, value: String): Result<Boolean> {
    val deferred = CompletableDeferred<Result<Boolean>>()
    setItem(key, value) { error, isSuccessful ->
        if (error == null) {
            deferred.complete(Result.success(isSuccessful))
        } else {
            deferred.complete(Result.failure(error))
        }
    }
    return deferred.await()
}

/**
 * Retrieves the value associated with a key from secure storage using a [CompletableDeferred] and returns a [Result].
 * This suspending function encapsulates the asynchronous operation of retrieving a value
 * and provides a structured way to handle both successful retrieval and potential errors.
 * It uses a [CompletableDeferred] to manage the asynchronous result.
 *
 * @param key The key to retrieve the value for.
 * @return A [Result] object containing the retrieved value (which can be null if the key is not found)
 *  or a [Throwable] representing the error that occurred.
 */
suspend fun SecureStorage.getWithResult(key: String): Result<Either<String?, Boolean>> {
    val deferred = CompletableDeferred<Result<Either<String?, Boolean>>>()
    getItem(key) { error, value, canBeRestored ->
        when {
            error != null -> deferred.complete(Result.failure(error))
            value != null -> deferred.complete(Result.success(value.either()))
            else -> deferred.complete(Result.success((canBeRestored == true).either()))
        }
    }
    return deferred.await()
}

/**
 * Restores the value associated with a key in secure storage using a [CompletableDeferred] and returns a [Result].
 *
 * @param key The key to restore the value for.
 * @return A [Result] object containing the result of the operation.
 */
suspend fun SecureStorage.restoreWithResult(key: String): Result<String?> {
    val deferred = CompletableDeferred<Result<String?>>()
    restoreItem(key) { error, value ->
        if (error == null) {
            deferred.complete(Result.success(value))
        } else {
            deferred.complete(Result.failure(error))
        }
    }
    return deferred.await()
}

/**
 * Removes the key-value pair associated with a key from secure storage using a [CompletableDeferred] and returns a [Result].
 *
 * @param key The key to remove.
 * @return A [Result] object containing the result of the operation.
 */
suspend fun SecureStorage.removeWithResult(key: String): Result<Boolean> {
    val deferred = CompletableDeferred<Result<Boolean>>()
    removeItem(key) { error, isSuccessful ->
        if (error == null) {
            deferred.complete(Result.success(isSuccessful))
        } else {
            deferred.complete(Result.failure(error))
        }
    }
    return deferred.await()
}

/**
 * Clears all key-value pairs from the secure storage using a [CompletableDeferred] and returns a [Result].
 *
 * @return A [Result] object containing the result of the operation.
 */
suspend fun SecureStorage.clearWithResult(): Result<Boolean> {
    val deferred = CompletableDeferred<Result<Boolean>>()
    clear { error, isSuccessful ->
        if (error == null) {
            deferred.complete(Result.success(isSuccessful))
        } else {
            deferred.complete(Result.failure(error))
        }
    }
    return deferred.await()
}

/**
 * Stores a key-value pair in secure storage. This suspending function handles the result directly
 * and throws an exception if an error occurs.
 *
 * @param key The key to store the value under.
 * @param value The value to store.
 * @return True if the operation was successful.
 * @throws Throwable If an error occurs during the operation.
 */
suspend fun SecureStorage.setItem(key: String, value: String): Boolean {
    return setWithResult(key, value).getOrThrow()
}
/**
 * Retrieves the value associated with a key from secure storage. This function uses a callback-based approach
 * for handling the asynchronous result of the operation.
 *
 * @param key The key to retrieve the value for.
 * @param callback A callback function that is called when the operation is complete.
 *  The first argument is an error object (a [Throwable] if an error occurred, or null otherwise),
 *  the second argument is the retrieved value (a [String] or null if the key is not found),
 *  and the third argument is a boolean indicating whether the value can be restored
 *  (useful if the value was previously removed and might be restorable).
 */

suspend fun SecureStorage.getItem(key: String): Either<String?, Boolean> {
    return getWithResult(key).getOrThrow()
}

/**
 * Restores the value associated with a key in secure storage. This suspending function handles
 * the result directly and throws an exception if an error occurs.
 *
 * @param key The key to restore the value for.
 * @return The restored value, or null if the key is not found or cannot be restored.
 * @throws Throwable If an error occurs during the operation.
 */
suspend fun SecureStorage.restoreItem(key: String): String? {
    return restoreWithResult(key).getOrThrow()
}

/**
 * Removes the key-value pair associated with a key from secure storage.
 * This suspending function handles the result directly and throws an exception if an error occurs.
 *
 * @param key The key to remove.
 * @return True if the operation was successful.
 * @throws Throwable If an error occurs during the operation.
 */
suspend fun SecureStorage.removeItem(key: String): Boolean {
    return removeWithResult(key).getOrThrow()
}

/**
 * Clears all key-value pairs from secure storage. This suspending function handles the result
 * directly and throws an exception if an error occurs.
 *
 * @return True if the operation was successful.
 * @throws Throwable If an error occurs during the operation.
 */
suspend fun SecureStorage.clear(): Boolean {
    return clearWithResult().getOrThrow()
}