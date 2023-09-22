package dev.inmo.tgbotapi.webapps.cloud

external interface CloudStorage {
    fun setItem(
        key: CloudStorageKey,
        value: CloudStorageValue,
        callback: (e: Error?, success: Boolean?) -> Unit = definedExternally
    ): CloudStorage
    fun getItem(
        key: CloudStorageKey,
        callback: (e: Error?, value: CloudStorageValue?) -> Unit
    ): CloudStorage
    fun getItems(
        key: Array<CloudStorageKey>,
        callback: (e: Error?, values: Array<CloudStorageValue>?) -> Unit
    ): CloudStorage
    fun removeItem(
        key: CloudStorageKey,
        callback: (e: Error?, success: Boolean?) -> Unit
    ): CloudStorage
    fun removeItems(
        key: Array<CloudStorageKey>,
        callback: (e: Error?, success: Boolean?) -> Unit
    ): CloudStorage
    fun getKeys(
        callback: (e: Error?, success: Array<CloudStorageKey>?) -> Unit
    ): CloudStorage
}

private fun <T> resultsToResult(e: Error?, v: T?): Result<T> = when {
    e != null -> Result.failure(e)
    v != null -> Result.success(v)
    else -> Result.failure(IllegalStateException("Both value and e"))
}

fun CloudStorage.set(
    key: String,
    value: String,
    callback: (result: Result<Boolean>) -> Unit = {}
) = setItem(CloudStorageKey(key), CloudStorageValue(value)) { e, v -> callback(resultsToResult(e, v)) }

fun CloudStorage.get(
    key: String,
    callback: (result: Result<CloudStorageValue>) -> Unit
) = getItem(CloudStorageKey(key)) { e, v -> callback(resultsToResult(e, v)) }

fun CloudStorage.get(
    key: String,
    key2: String,
    vararg otherKeys: String,
    callback: (result: Result<Array<CloudStorageValue>>) -> Unit
) = getItems(
    Array(2 + otherKeys.size) {
        when (it) {
            0 -> CloudStorageKey(key)
            1 -> CloudStorageKey(key2)
            else -> CloudStorageKey(otherKeys[it - 2])
        }
    }
) { e, v -> callback(resultsToResult(e, v)) }

fun CloudStorage.remove(
    key: String,
    callback: (result: Result<Boolean>) -> Unit
) = removeItem(CloudStorageKey(key)) { e, v -> callback(resultsToResult(e, v)) }

fun CloudStorage.remove(
    key: String,
    key2: String,
    vararg otherKeys: String,
    callback: (result: Result<Boolean>) -> Unit
) = removeItems(
    Array(2 + otherKeys.size) {
        when (it) {
            0 -> CloudStorageKey(key)
            1 -> CloudStorageKey(key2)
            else -> CloudStorageKey(otherKeys[it - 2])
        }
    }
) { e, v -> callback(resultsToResult(e, v)) }

fun CloudStorage.keys(
    callback: (result: Result<Array<CloudStorageKey>>) -> Unit
) = getKeys { e, v -> callback(resultsToResult(e, v)) }

