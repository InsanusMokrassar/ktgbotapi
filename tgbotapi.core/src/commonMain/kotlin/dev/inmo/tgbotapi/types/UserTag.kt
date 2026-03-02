package dev.inmo.tgbotapi.types

import dev.inmo.kslog.common.w
import dev.inmo.tgbotapi.utils.DefaultKTgBotAPIKSLog
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class UserTag(val string: String) {
    init {
        if (string.length !in memberTagLength) {
            DefaultKTgBotAPIKSLog.w("UserTag", "Tag length must be in range $memberTagLength, but was ${string.length} ($string)")
        }
    }
}
