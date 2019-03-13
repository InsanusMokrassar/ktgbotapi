package com.github.insanusmokrassar.TelegramBotAPI.requests.send.media.base

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import kotlinx.serialization.ContextSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(DataRequestSerializer::class)
interface DataRequest<T: Any> : SimpleRequest<T>

object DataRequestSerializer : KSerializer<DataRequest<*>> by ContextSerializer(DataRequest::class)