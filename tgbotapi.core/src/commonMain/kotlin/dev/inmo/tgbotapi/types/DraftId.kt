package dev.inmo.tgbotapi.types

import dev.inmo.kslog.common.w
import dev.inmo.tgbotapi.utils.DefaultKTgBotAPIKSLog
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class DraftId(
    val long: Long
) {
    init {
        if (long == 0L) {
            DefaultKTgBotAPIKSLog.w("DraftId", "In DraftId has been passed 0. According to the documentation it must not be 0")
        }
    }
}
