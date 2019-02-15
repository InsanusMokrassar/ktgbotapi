package com.github.insanusmokrassar.TelegramBotAPI.requests.common

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.*
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.json.JsonObject

data class CommonMultipartFileRequest<T: Any>(
    val data: Request<T>,
    override val mediaMap: Map<String, MultipartFile>
) : MultipartRequest<T>, Request<T> by data {
    @ImplicitReflectionSerializer
    override val paramsJson: JsonObject = data.json()
}
