package dev.inmo.tgbotapi.extensions.api.stories

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.stories.EditStory
import dev.inmo.tgbotapi.types.Seconds
import dev.inmo.tgbotapi.types.StoryId
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.types.stories.InputStoryContent
import dev.inmo.tgbotapi.types.stories.Story
import dev.inmo.tgbotapi.types.stories.StoryArea

public suspend fun TelegramBot.editStory(
    businessConnectionId: BusinessConnectionId,
    storyId: StoryId,
    content: InputStoryContent,
    textSources: List<TextSource>,
    areas: List<StoryArea> = emptyList(),
): Story = execute(
    EditStory(
        businessConnectionId = businessConnectionId,
        storyId = storyId,
        content = content,
        textSources = textSources,
        areas = areas,
    )
)

public suspend fun TelegramBot.editStory(
    businessConnectionId: BusinessConnectionId,
    storyId: StoryId,
    content: InputStoryContent,
    text: String,
    parseMode: ParseMode? = null,
    areas: List<StoryArea> = emptyList(),
): Story = execute(
    EditStory(
        businessConnectionId = businessConnectionId,
        storyId = storyId,
        content = content,
        text = text,
        parseMode = parseMode,
        areas = areas,
    )
)

public suspend fun TelegramBot.editStory(
    businessConnectionId: BusinessConnectionId,
    storyId: StoryId,
    content: InputStoryContent,
    areas: List<StoryArea> = emptyList(),
): Story = execute(
    EditStory(
        businessConnectionId = businessConnectionId,
        storyId = storyId,
        content = content,
        areas = areas,
    )
)
