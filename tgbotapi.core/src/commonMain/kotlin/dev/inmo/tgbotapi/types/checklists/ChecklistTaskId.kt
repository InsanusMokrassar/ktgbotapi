package dev.inmo.tgbotapi.types.checklists

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class ChecklistTaskId(
    val int: UInt
)
