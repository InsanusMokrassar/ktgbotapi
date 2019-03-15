package com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts

import kotlinx.serialization.*

@Serializable(SimpleRequestSerializer::class)
interface SimpleRequest<T: Any> : Request<T>

object SimpleRequestSerializer : KSerializer<SimpleRequest<*>> by ContextSerializer(SimpleRequest::class)
