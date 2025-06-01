package dev.inmo.tgbotapi.extensions.api.stories

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.stories.DeleteStory
import dev.inmo.tgbotapi.types.StoryId
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.message.abstracts.BusinessContentMessage
import dev.inmo.tgbotapi.types.stories.Story

/**
 * Deletes a story from a business account
 */
public suspend fun TelegramBot.deleteStory(
    businessConnectionId: BusinessConnectionId,
    storyId: StoryId
): Boolean = execute(
    DeleteStory(
        businessConnectionId = businessConnectionId,
        storyId = storyId
    )
)

public suspend fun TelegramBot.deleteStory(
    message: BusinessContentMessage<*>,
    storyId: StoryId
): Boolean = deleteStory(
    businessConnectionId = with(message) {message.businessConnectionId}, storyId = storyId
)

public suspend fun TelegramBot.deleteStory(
    businessConnectionId: BusinessConnectionId,
    story: Story
): Boolean = deleteStory(
    businessConnectionId = businessConnectionId, storyId = with(story) {story.id}
)

public suspend fun TelegramBot.deleteStory(
    message: BusinessContentMessage<*>,
    story: Story
): Boolean = deleteStory(
    message = message, storyId = with(story) {story.id}
)
