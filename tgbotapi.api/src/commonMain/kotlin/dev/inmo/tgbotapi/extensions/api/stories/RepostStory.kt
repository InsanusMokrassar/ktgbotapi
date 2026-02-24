package dev.inmo.tgbotapi.extensions.api.stories

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.stories.RepostStory
import dev.inmo.tgbotapi.types.BusinessChatId
import dev.inmo.tgbotapi.types.IdChatIdentifier
import dev.inmo.tgbotapi.types.Seconds
import dev.inmo.tgbotapi.types.StoryId
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.stories.Story

public suspend fun TelegramBot.repostStory(
    businessConnectionId: BusinessConnectionId,
    fromChatId: IdChatIdentifier,
    storyId: StoryId,
    activePeriod: Seconds,
    postToChatPage: Boolean = false,
    protectContent: Boolean = false,
): Story = execute(
    RepostStory(
        businessConnectionId = businessConnectionId,
        fromChatId = fromChatId,
        storyId = storyId,
        activePeriod = activePeriod,
        postToChatPage = postToChatPage,
        protectContent = protectContent
    )
)

public suspend fun TelegramBot.repostStory(
    fromChatId: BusinessChatId,
    storyId: StoryId,
    activePeriod: Seconds,
    postToChatPage: Boolean = false,
    protectContent: Boolean = false,
): Story = execute(
    RepostStory(
        businessConnectionId = fromChatId.businessConnectionId,
        fromChatId = fromChatId,
        storyId = storyId,
        activePeriod = activePeriod,
        postToChatPage = postToChatPage,
        protectContent = protectContent
    )
)
