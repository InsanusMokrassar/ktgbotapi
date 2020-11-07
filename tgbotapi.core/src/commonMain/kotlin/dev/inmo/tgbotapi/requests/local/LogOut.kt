package dev.inmo.tgbotapi.requests.local

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

/**
 * Use this method to log out from the cloud Bot API server before launching the bot locally. You **must** log out the bot
 * before running it locally, otherwise there is no guarantee that the bot will receive updates. After a successful
 * call, you will not be able to log in again using the same token for 10 minutes
 */
@Serializable
object LogOut : SimpleRequest<Boolean> {
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    override fun method(): String = "logOut"

    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()
}
