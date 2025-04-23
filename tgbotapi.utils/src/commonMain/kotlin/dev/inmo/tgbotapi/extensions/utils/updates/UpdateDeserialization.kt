package dev.inmo.tgbotapi.extensions.utils.updates

import dev.inmo.tgbotapi.extensions.utils.nonstrictJsonFormat
import dev.inmo.tgbotapi.types.update.abstracts.UpdateDeserializationStrategy
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement

/**
 * @return Deserialize [source] as [dev.inmo.tgbotapi.types.update.abstracts.Update]
 */
fun Json.toTelegramUpdate(source: String) = decodeFromString(UpdateDeserializationStrategy, source)

/**
 * @return Deserialize [source] as [dev.inmo.tgbotapi.types.update.abstracts.Update]
 */
fun Json.toTelegramUpdate(source: JsonElement) = decodeFromJsonElement(UpdateDeserializationStrategy, source)

/**
 * @return Deserialize [this] as [dev.inmo.tgbotapi.types.update.abstracts.Update]. In fact,
 * it is must be JSON
 *
 * @see Json.toTelegramUpdate
 */
fun String.toTelegramUpdate() = nonstrictJsonFormat.toTelegramUpdate(this)

/**
 * @return Deserialize [this] as [dev.inmo.tgbotapi.types.update.abstracts.Update]
 *
 * @see Json.toTelegramUpdate
 */
fun JsonElement.toTelegramUpdate() = nonstrictJsonFormat.toTelegramUpdate(this)
