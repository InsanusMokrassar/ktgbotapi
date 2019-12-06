package com.github.insanusmokrassar.TelegramBotAPI.requests.send.media.base

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import kotlinx.serialization.*

interface DataRequest<T: Any> : SimpleRequest<T>
