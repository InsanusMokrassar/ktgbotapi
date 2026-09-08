package dev.inmo.tgbotapi.utils

import dev.inmo.tgbotapi.types.DirectMessageThreadId
import dev.inmo.tgbotapi.types.MessageThreadId

/**
 * Converts a [MessageThreadId] instance to a [DirectMessageThreadId] by using its underlying `long` value.
 *
 * @receiver The [MessageThreadId] instance to be converted.
 * @return A new [DirectMessageThreadId] instance with the same `long` value as the receiver.
 */
fun MessageThreadId.toDirect() = DirectMessageThreadId(long)
/**
 * Converts a [DirectMessageThreadId] instance to a [MessageThreadId] instance.
 *
 * This extension function facilitates type transformation between two inline classes
 * with the same underlying value, allowing seamless usage of their respective types.
 *
 * @receiver The [DirectMessageThreadId] instance to be transformed.
 * @return The corresponding [MessageThreadId] instance containing the same underlying value.
 */
fun DirectMessageThreadId.toCommon() = MessageThreadId(long)

/**
 * Returns the current instance of [DirectMessageThreadId].
 *
 * This method provides a straightforward way to retrieve the object in its current state
 * without introducing any modifications or transformations.
 *
 * @return The current instance of [DirectMessageThreadId].
 */
fun DirectMessageThreadId.directMessageThreadId(): DirectMessageThreadId = this
/**
 * Converts this [MessageThreadId] instance to a [DirectMessageThreadId].
 *
 * @return A new [DirectMessageThreadId] instance with the same underlying `long` value as this [MessageThreadId].
 */
fun MessageThreadId.directMessageThreadId(): DirectMessageThreadId = toDirect()

/**
 * Converts a [DirectMessageThreadId] instance to a [MessageThreadId] instance.
 *
 * This method provides a straightforward way to transform a [DirectMessageThreadId]
 * object into a [MessageThreadId] object by leveraging the underlying value.
 *
 * @return The [MessageThreadId] instance containing the same underlying value as the original [DirectMessageThreadId].
 */
fun DirectMessageThreadId.messageThreadId(): MessageThreadId = toCommon()
/**
 * Returns the current instance of the MessageThreadId.
 *
 * The method is a utility function that allows for consistent handling of `MessageThreadId`
 * instances and can be used when chaining operations without altering the instance.
 *
 * @return The current `MessageThreadId` instance.
 */
fun MessageThreadId.messageThreadId(): MessageThreadId = this
