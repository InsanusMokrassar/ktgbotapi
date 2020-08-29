package com.github.insanusmokrassar.TelegramBotAPI.requests.stickers.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.UserId
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.serializer

interface StickerSetAction : SimpleRequest<Boolean> {
    val userId: UserId
    val name: String

    override val resultDeserializer: KSerializer<Boolean>
        get() = Boolean.serializer()
}