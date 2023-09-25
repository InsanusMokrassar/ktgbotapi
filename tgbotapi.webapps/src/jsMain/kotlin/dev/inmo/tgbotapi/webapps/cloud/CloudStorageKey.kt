package dev.inmo.tgbotapi.webapps.cloud

import dev.inmo.tgbotapi.types.cloudStorageKeyRegex
import kotlinx.serialization.Serializable

@Serializable
value class CloudStorageKey(val key: String) {
    init {
        require(key.matches(cloudStorageKeyRegex)) {
            "'$key' must pass $cloudStorageKeyRegex in case you wish to use it as key for cloud storage operations"
        }
    }
}
