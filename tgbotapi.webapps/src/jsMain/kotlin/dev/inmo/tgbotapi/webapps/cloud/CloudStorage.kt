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
    key: CloudStorageKey,
    callback: (result: Result<CloudStorageValue>) -> Unit
) = getItem(key) { e, v -> callback(resultsToResult(e, v)) }

fun CloudStorage.get(
    key: String,
    callback: (result: Result<CloudStorageValue>) -> Unit
) = get(CloudStorageKey(key), callback)

fun CloudStorage.get(
    keys: Array<CloudStorageKey>,
    callback: (result: Result<Array<CloudStorageValue>>) -> Unit
) = getItems(
    keys
) { e, v -> callback(resultsToResult(e, v)) }

fun CloudStorage.get(
    keys: Array<String>,
    callback: (result: Result<Array<CloudStorageValue>>) -> Unit
) = get(
    Array(keys.size) {
        CloudStorageKey(keys[it])
    },
    callback
)

fun CloudStorage.get(
    key: String,
    key2: String,
    vararg otherKeys: String,
    callback: (result: Result<Array<CloudStorageValue>>) -> Unit
) = get(
    arrayOf(key, key2) + otherKeys,
    callback
)

fun CloudStorage.remove(
    key: CloudStorageKey,
    callback: (result: Result<Boolean>) -> Unit
) = removeItem(key) { e, v -> callback(resultsToResult(e, v)) }

fun CloudStorage.remove(
    key: String,
    callback: (result: Result<Boolean>) -> Unit
) = remove(CloudStorageKey(key), callback)

fun CloudStorage.remove(
    keys: Array<CloudStorageKey>,
    callback: (result: Result<Boolean>) -> Unit
) = removeItems(
    keys
) { e, v -> callback(resultsToResult(e, v)) }

fun CloudStorage.remove(
    keys: Array<String>,
    callback: (result: Result<Boolean>) -> Unit
) = remove(
    Array(keys.size) {
        CloudStorageKey(keys[it])
    },
    callback
)

fun CloudStorage.remove(
    key: String,
    key2: String,
    vararg otherKeys: String,
    callback: (result: Result<Boolean>) -> Unit
) = remove(
    arrayOf(key, key2) + otherKeys,
    callback
)

fun CloudStorage.keys(
    callback: (result: Result<Array<CloudStorageKey>>) -> Unit
) = getKeys { e, v -> callback(resultsToResult(e, v)) }

fun CloudStorage.getAll(callback: (result: Result<Map<CloudStorageKey, CloudStorageValue>>) -> Unit) = keys {
    it.onSuccess { keys ->
        get(keys) {
            it.onSuccess { values ->
                val resultMap = keys.withIndex().mapNotNull { (i, it) ->
                    it to (values.getOrNull(i) ?: return@mapNotNull null)
                }.toMap()
                callback(Result.success(resultMap))
            }.onFailure {
                callback(Result.failure(it))
            }
        }
    }.onFailure {
        callback(Result.failure(it))
    }
}
