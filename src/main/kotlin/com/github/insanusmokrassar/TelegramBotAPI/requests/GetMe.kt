package com.github.insanusmokrassar.TelegramBotAPI.requests

import com.github.insanusmokrassar.TelegramBotAPI.types.User
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import kotlinx.serialization.KSerializer

class GetMe : SimpleRequest<User> {
    override fun method(): String = "getMe"
    override fun resultSerializer(): KSerializer<User> = User.serializer()
}