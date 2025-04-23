package dev.inmo.tgbotapi.types.request

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline
import kotlin.random.Random

@Serializable
@JvmInline
value class RequestId(
    val uShort: UShort,
) {
    constructor(int: Int) : this(int.toUShort())

    companion object {
        fun random() = RequestId(Random.nextInt())
    }
}
