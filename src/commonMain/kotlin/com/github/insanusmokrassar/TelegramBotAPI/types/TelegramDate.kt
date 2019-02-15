package com.github.insanusmokrassar.TelegramBotAPI.types

import com.soywiz.klock.DateTime
import kotlinx.serialization.*

@Serializable(TelegramDateSerializer::class)
class TelegramDate(
    private val date: Long
) {
    @Transient
    val asDate: DateTime = DateTime.fromUnix(date)
}

@Serializer(TelegramDate::class)
internal expect class TelegramDateSerializer: KSerializer<TelegramDate>
