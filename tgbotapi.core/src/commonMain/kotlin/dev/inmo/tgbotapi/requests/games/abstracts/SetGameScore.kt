package dev.inmo.tgbotapi.requests.games.abstracts

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.utils.serializers.UnitFromBooleanSerializer
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.builtins.serializer

interface SetGameScore : SimpleRequest<Unit> {
    val userId: UserId
    val score: Long
    val force: Boolean
    val disableEditMessage: Boolean

    override fun method(): String = "setGameScore"
    override val resultDeserializer: DeserializationStrategy<Unit>
        get() = UnitFromBooleanSerializer
}