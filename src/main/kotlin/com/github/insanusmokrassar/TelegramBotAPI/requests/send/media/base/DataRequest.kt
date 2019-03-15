package com.github.insanusmokrassar.TelegramBotAPI.requests.send.media.base

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import kotlinx.serialization.*

@Serializable(DataRequestSerializer::class)
interface DataRequest<T: Any> : SimpleRequest<T>

object DataRequestSerializer : KSerializer<DataRequest<*>> by ContextSerializer(DataRequest::class)