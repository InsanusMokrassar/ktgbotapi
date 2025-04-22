package dev.inmo.tgbotapi.webapps.cloud

import dev.inmo.tgbotapi.types.cloudStorageValueRegex
import kotlinx.serialization.Serializable

@Serializable
value class CloudStorageValue(val value: String) {
    init {
        require(value.matches(cloudStorageValueRegex)) {
            "'$value' must pass $cloudStorageValueRegex in case you wish to use it as key for cloud storage operations"
        }
    }
}
