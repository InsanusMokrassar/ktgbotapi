package com.github.insanusmokrassar.TelegramBotAPI.requests.games.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.UserId
import com.github.insanusmokrassar.TelegramBotAPI.types.games.GameHighScore
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.KSerializer
import kotlinx.serialization.internal.ArrayListSerializer

interface GetGameHighScores : SimpleRequest<List<GameHighScore>> {
    val userId: UserId

    override fun method(): String = "getGameHighScores"
    override val resultDeserializer: DeserializationStrategy<List<GameHighScore>>
        get() = GameHighScoresSerializer
}

internal object GameHighScoresSerializer : KSerializer<List<GameHighScore>> by ArrayListSerializer(GameHighScore.serializer())
