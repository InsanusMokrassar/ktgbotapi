package com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts

import kotlinx.serialization.json.JsonObject

interface MultipartRequest<T: Any> : Request<T> {
    val paramsJson: JsonObject
    val mediaMap: Map<String, MultipartFile>
}
