package dev.inmo.tgbotapi.requests.games.abstracts

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.games.GameHighScore
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer

interface GetGameHighScores : SimpleRequest<List<GameHighScore>> {
    val userId: UserId

    override fun method(): String = "getGameHighScores"
    override val resultDeserializer: DeserializationStrategy<List<GameHighScore>>
        get() = GameHighScoresSerializer
}

internal object GameHighScoresSerializer : KSerializer<List<GameHighScore>> by ListSerializer(GameHighScore.serializer())
