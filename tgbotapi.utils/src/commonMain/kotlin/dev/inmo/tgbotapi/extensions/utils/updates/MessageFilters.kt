package dev.inmo.tgbotapi.extensions.utils.updates

import dev.inmo.tgbotapi.extensions.utils.*
import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage
import dev.inmo.tgbotapi.types.message.content.TextContent
import dev.inmo.tgbotapi.types.message.textsources.BotCommandTextSource

/**
 * A predicate to test whether a message contains any commands in its body.
 * Use it as the `initialFilter` parameter in behaviour builder triggers.
 * E.g.
 *
 * ```kotlin
 * onContentMessage(
 *     initialFilter = CommonMessage<MessageContent>::hasCommands
 * ) {
 *     // the message contains at least one command here
 * }
 * ```
 *
 * @return true if this [CommonMessage] contains any commands. False otherwise.
 * @see hasNoCommands
 */
fun CommonMessage<*>.hasCommands(): Boolean = withContentOrNull<TextContent>() ?.content ?.textSources ?.any {
    it is BotCommandTextSource
} ?: false

/**
 * A predicate to test whether a message contains any commands in its body.
 * Use it as the `initialFilter` parameter in behaviour builder triggers.
 * E.g.
 *
 * ```kotlin
 * onContentMessage(
 *     initialFilter = CommonMessage<MessageContent>::hasNoCommands
 * ) {
 *     // the message contains no commands here
 * }
 * ```
 *
 * @return true if this [CommonMessage] does not contain any commands. False otherwise.
 * @see hasCommands
 */
fun CommonMessage<*>.hasNoCommands(): Boolean = !this.hasCommands()
