package dev.inmo.tgbotapi.requests.abstracts

import kotlinx.serialization.json.JsonObject

interface MultipartRequest<T: Any> : Request<T> {
    val paramsJson: JsonObject
    val mediaMap: Map<String, MultipartFile>

    interface Common<T : Any> : MultipartRequest<T>, SimpleRequest<T> {
        val data: SimpleRequest<T>

        override val paramsJson: JsonObject
            get() = data.json()
    }
}
