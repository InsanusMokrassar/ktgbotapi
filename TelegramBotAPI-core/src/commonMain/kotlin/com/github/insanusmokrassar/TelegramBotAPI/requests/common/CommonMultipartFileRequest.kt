package com.github.insanusmokrassar.TelegramBotAPI.requests.common

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.*
import kotlinx.serialization.json.JsonObject

internal data class CommonMultipartFileRequest<T: Any>(
    val data: SimpleRequest<T>,
    override val mediaMap: Map<String, MultipartFile>
) : MultipartRequest<T>, Request<T> by data {
    override val paramsJson: JsonObject = data.json()
}
