package dev.inmo.tgbotapi.extensions.behaviour_builder.utils

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTypeReceiver
import dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitEditedLocation
import dev.inmo.tgbotapi.types.location.*
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.LiveLocationContent

/**
 * Use this extension when you want to follow [LiveLocation] until it will became [StaticLocation]. This method
 * is synchronous. You may use something like [kotlinx.coroutines.launch] or [kotlinx.coroutines.async] to run it
 * asynchronously
 */
suspend fun BehaviourContext.followLocation(
    message: ContentMessage<LiveLocationContent>,
    onLocation: BehaviourContextAndTypeReceiver<Unit, Location>
) {
    var currentLocation: Location = message.content.location
    onLocation(message.content.location)

    while (currentLocation !is StaticLocation) {
        currentLocation = waitEditedLocation(
            filter = {
                it.messageId == message.messageId && it.chat.id == message.chat.id
            }
        ).first().location
        onLocation(currentLocation)
    }
}
