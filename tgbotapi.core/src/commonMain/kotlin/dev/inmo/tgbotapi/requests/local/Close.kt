package dev.inmo.tgbotapi.requests.local

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.utils.serializers.UnitFromBooleanSerializer
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

/**
 * Use this method to close the bot instance before moving it from one local server to another. You need to delete the
 * webhook before calling this method to ensure that the bot isn't launched again after server restart. The method will
 * return error 429 in the first 10 minutes after the bot is launched.
 *
 * @see io.ktor.client.features.ClientRequestException
 */
@Serializable
object Close : SimpleRequest<Unit> {
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    override fun method(): String = "close"

    override val resultDeserializer: DeserializationStrategy<Unit>
        get() = UnitFromBooleanSerializer
}