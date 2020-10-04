package dev.inmo.tgbotapi.requests.common

import dev.inmo.tgbotapi.requests.abstracts.*
import kotlinx.serialization.json.JsonObject

internal data class CommonMultipartFileRequest<T: Any>(
    val data: SimpleRequest<T>,
    override val mediaMap: Map<String, MultipartFile>
) : MultipartRequest<T>, Request<T> by data {
    override val paramsJson: JsonObject = data.json()
}
