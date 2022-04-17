package dev.inmo.tgbotapi.types.message.ChatEvents.abstracts

import dev.inmo.tgbotapi.types.message.ChatEvents.voice.VideoChatScheduled

interface VideoChatEvent : PublicChatEvent

@Deprecated("Renamed", ReplaceWith("VideoChatEvent", "dev.inmo.tgbotapi.types.message.ChatEvents.voice.VideoChatEvent"))
typealias VoiceChatEvent = VideoChatEvent
