package dev.inmo.tgbotapi.types

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class MediaGroupId(
    val string: String
)

@Deprecated("Renamed", ReplaceWith("MediaGroupId", "dev.inmo.tgbotapi.types.MediaGroupId"))
typealias MediaGroupIdentifier = MediaGroupId