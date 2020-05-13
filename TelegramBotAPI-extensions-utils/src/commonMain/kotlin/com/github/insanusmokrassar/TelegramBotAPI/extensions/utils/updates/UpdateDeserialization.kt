package com.github.insanusmokrassar.TelegramBotAPI.extensions.utils.updates

import com.github.insanusmokrassar.TelegramBotAPI.extensions.utils.nonstrictJsonFormat
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.UpdateDeserializationStrategy
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement

/**
 * @return Deserialize [source] as [com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.Update]
 */
fun Json.asTelegramUpdate(source: String) = parse(UpdateDeserializationStrategy, source)
/**
 * @return Deserialize [source] as [com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.Update]
 */
fun Json.asTelegramUpdate(source: JsonElement) = fromJson(UpdateDeserializationStrategy, source)

/**
 * @return Deserialize [this] as [com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.Update]. In fact,
 * it is must be JSON
 *
 * @see Json.asTelegramUpdate
 */
fun String.asTelegramUpdate() = nonstrictJsonFormat.asTelegramUpdate(this)
/**
 * @return Deserialize [this] as [com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.Update]
 *
 * @see Json.asTelegramUpdate
 */
fun JsonElement.asTelegramUpdate() = nonstrictJsonFormat.asTelegramUpdate(this)


