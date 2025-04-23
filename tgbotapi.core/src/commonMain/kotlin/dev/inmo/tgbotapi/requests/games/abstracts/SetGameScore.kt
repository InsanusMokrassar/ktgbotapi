package dev.inmo.tgbotapi.requests.games.abstracts

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.UserId
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.builtins.serializer

interface SetGameScore : SimpleRequest<Boolean> {
    val userId: UserId
    val score: Long
    val force: Boolean
    val disableEditMessage: Boolean

    override fun method(): String = "setGameScore"

    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()
}
