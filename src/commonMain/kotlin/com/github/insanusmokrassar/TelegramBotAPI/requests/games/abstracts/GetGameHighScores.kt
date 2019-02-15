package com.github.insanusmokrassar.TelegramBotAPI.requests.games.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.UserId
import com.github.insanusmokrassar.TelegramBotAPI.types.games.GameHighScore
import kotlinx.serialization.KSerializer
import kotlinx.serialization.internal.ArrayListSerializer

interface GetGameHighScores : SimpleRequest<List<GameHighScore>> {
    val userId: UserId

    override fun method(): String = "getGameHighScores"
    override fun resultSerializer(): KSerializer<List<GameHighScore>> = GameHighScoresSerializer
}

object GameHighScoresSerializer : KSerializer<List<GameHighScore>> by ArrayListSerializer(GameHighScore.serializer())
