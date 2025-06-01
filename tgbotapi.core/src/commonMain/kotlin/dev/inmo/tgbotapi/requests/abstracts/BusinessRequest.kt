package dev.inmo.tgbotapi.requests.abstracts

import dev.inmo.tgbotapi.abstracts.types.WithBusinessConnectionId
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import kotlinx.serialization.json.JsonObject

interface BusinessRequest<T: Any> : Request<T>, WithBusinessConnectionId {
    interface Simple<T : Any> : BusinessRequest<T>, SimpleRequest<T>
    interface Multipart<T : Any> : BusinessRequest<T>, MultipartRequest.Common<T>, SimpleRequest<T> {
        override val data: SimpleRequest<T>
            get() = this
    }
}
