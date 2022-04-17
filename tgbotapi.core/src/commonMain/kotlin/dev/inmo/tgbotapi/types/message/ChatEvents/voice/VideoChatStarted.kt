package dev.inmo.tgbotapi.types.message.ChatEvents.voice

import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.VideoChatEvent
import kotlinx.serialization.Serializable

@Serializable
object VideoChatStarted : VideoChatEvent

@Deprecated("Renamed", ReplaceWith("VideoChatStarted", "dev.inmo.tgbotapi.types.message.ChatEvents.voice.VideoChatStarted"))
typealias VoiceChatStarted = VideoChatStarted
