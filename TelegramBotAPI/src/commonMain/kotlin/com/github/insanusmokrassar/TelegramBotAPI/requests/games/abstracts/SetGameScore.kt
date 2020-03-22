package com.github.insanusmokrassar.TelegramBotAPI.requests.games.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.UserId
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