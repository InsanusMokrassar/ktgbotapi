package dev.inmo.tgbotapi.types.request

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline
import kotlin.random.Random

@Serializable
@JvmInline
value class RequestId(
    val float: Float
) {
    companion object {
        fun random() = RequestId(Random.nextFloat())
    }
}
