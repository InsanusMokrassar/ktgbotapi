package dev.inmo.tgbotapi.types.communities

import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.CommonEvent
import kotlinx.serialization.Serializable

/**
 * Service message about a chat being removed from a community. Currently holds no information
 */
@Serializable
object CommunityChatRemoved : CommonEvent
