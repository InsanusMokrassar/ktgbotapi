package dev.inmo.tgbotapi.extensions.api.stories

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.stories.PostStory
import dev.inmo.tgbotapi.types.Seconds
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.types.stories.InputStoryContent
import dev.inmo.tgbotapi.types.stories.Story
import dev.inmo.tgbotapi.types.stories.StoryArea
import dev.inmo.tgbotapi.utils.EntitiesBuilderBody
import dev.inmo.tgbotapi.utils.buildEntities

public suspend fun TelegramBot.postStory(
    businessConnectionId: BusinessConnectionId,
    content: InputStoryContent,
    activePeriod: Seconds,
    textSources: List<TextSource>,
    areas: List<StoryArea> = emptyList(),
    postToChatPage: Boolean = false,
    protectContent: Boolean = false,
): Story = execute(
    PostStory(
        businessConnectionId = businessConnectionId,
        content = content,
        activePeriod = activePeriod,
        textSources = textSources,
        areas = areas,
        postToChatPage = postToChatPage,
        protectContent = protectContent
    )
)

public suspend fun TelegramBot.postStory(
    businessConnectionId: BusinessConnectionId,
    content: InputStoryContent,
    activePeriod: Seconds,
    areas: List<StoryArea> = emptyList(),
    postToChatPage: Boolean = false,
    protectContent: Boolean = false,
    separator: TextSource? = null,
    textBuilder: EntitiesBuilderBody
): Story = execute(
    PostStory(
        businessConnectionId = businessConnectionId,
        content = content,
        activePeriod = activePeriod,
        textSources = buildEntities(separator, textBuilder),
        areas = areas,
        postToChatPage = postToChatPage,
        protectContent = protectContent
    )
)

public suspend fun TelegramBot.postStory(
    businessConnectionId: BusinessConnectionId,
    content: InputStoryContent,
    activePeriod: Seconds,
    text: String,
    parseMode: ParseMode? = null,
    areas: List<StoryArea> = emptyList(),
    postToChatPage: Boolean = false,
    protectContent: Boolean = false,
): Story = execute(
    PostStory(
        businessConnectionId = businessConnectionId,
        content = content,
        activePeriod = activePeriod,
        text = text,
        parseMode = parseMode,
        areas = areas,
        postToChatPage = postToChatPage,
        protectContent = protectContent
    )
)

public suspend fun TelegramBot.postStory(
    businessConnectionId: BusinessConnectionId,
    content: InputStoryContent,
    activePeriod: Seconds,
    areas: List<StoryArea> = emptyList(),
    postToChatPage: Boolean = false,
    protectContent: Boolean = false,
): Story = execute(
    PostStory(
        businessConnectionId = businessConnectionId,
        content = content,
        activePeriod = activePeriod,
        areas = areas,
        postToChatPage = postToChatPage,
        protectContent = protectContent
    )
)
