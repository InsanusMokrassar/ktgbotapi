package com.github.insanusmokrassar.TelegramBotAPI.requests

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.User
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable
class GetMe : SimpleRequest<User> {
    override fun method(): String = "getMe"
    override fun resultSerializer(): KSerializer<User> = User.serializer()
}