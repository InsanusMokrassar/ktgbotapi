package dev.inmo.tgbotapi.extensions.api.InternalUtils

import dev.inmo.tgbotapi.types.MediaGroupIdentifier
import dev.inmo.tgbotapi.types.UpdateIdentifier
import dev.inmo.tgbotapi.types.message.AnonymousForumContentMessageImpl
import dev.inmo.tgbotapi.types.message.AnonymousGroupContentMessageImpl
import dev.inmo.tgbotapi.types.message.ChannelContentMessageImpl
import dev.inmo.tgbotapi.types.message.CommonForumContentMessageImpl
import dev.inmo.tgbotapi.types.message.CommonGroupContentMessageImpl
import dev.inmo.tgbotapi.types.message.ConnectedFromChannelGroupContentMessageImpl
import dev.inmo.tgbotapi.types.message.FromChannelForumContentMessageImpl
import dev.inmo.tgbotapi.types.message.PrivateContentMessageImpl
import dev.inmo.tgbotapi.types.message.UnconnectedFromChannelGroupContentMessageImpl
import dev.inmo.tgbotapi.types.message.abstracts.AnonymousForumContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.AnonymousGroupContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.ChannelContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.CommonForumContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.CommonGroupContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage
import dev.inmo.tgbotapi.types.message.abstracts.ConnectedFromChannelGroupContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.FromChannelForumContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.MediaGroupMessage
import dev.inmo.tgbotapi.types.message.abstracts.PossiblySentViaBotCommonMessage
import dev.inmo.tgbotapi.types.message.abstracts.PrivateContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.UnconnectedFromChannelGroupContentMessage
import dev.inmo.tgbotapi.types.message.content.MediaGroupCollectionContent
import dev.inmo.tgbotapi.types.message.content.MediaGroupContent
import dev.inmo.tgbotapi.types.message.content.MediaGroupPartContent
import dev.inmo.tgbotapi.types.update.*
import dev.inmo.tgbotapi.types.update.abstracts.*
import dev.inmo.tgbotapi.types.update.media_group.*

internal fun Update.lastUpdateIdentifier(): UpdateIdentifier {
    return if (this is SentMediaGroupUpdate) {
        origins.last().updateId
    } else {
        updateId
    }
}

internal fun List<Update>.lastUpdateIdentifier(): UpdateIdentifier? {
    return maxByOrNull { it.updateId } ?.lastUpdateIdentifier()
}

internal fun List<Update>.convertWithMediaGroupUpdates(): List<Update> {
    val resultUpdates = mutableListOf<Update>()
    val mediaGroups = mutableMapOf<MediaGroupIdentifier, MutableList<BaseSentMessageUpdate>>()
    for (update in this) {
        val data = (update.data as? MediaGroupMessage<*>)
        if (data == null) {
            resultUpdates.add(update)
            continue
        }
        when (update) {
            is BaseEditMessageUpdate -> resultUpdates.add(
                update.toEditMediaGroupUpdate()
            )
            is BaseSentMessageUpdate -> {
                mediaGroups.getOrPut(data.mediaGroupId) {
                    mutableListOf()
                }.add(update)
            }
            else -> resultUpdates.add(update)
        }
    }
    mediaGroups.values.map {
        it.toSentMediaGroupUpdate() ?.let { mediaGroupUpdate ->
            resultUpdates.add(mediaGroupUpdate)
        }
    }
    resultUpdates.sortBy { it.updateId }
    return resultUpdates
}

internal fun List<BaseSentMessageUpdate>.toSentMediaGroupUpdate(): SentMediaGroupUpdate? = (this as? SentMediaGroupUpdate) ?: let {
    if (isEmpty()) {
        return@let null
    }
    val resultList = sortedBy { it.updateId }
    when (first()) {
        is MessageUpdate -> MessageMediaGroupUpdate(resultList)
        is ChannelPostUpdate -> ChannelPostMediaGroupUpdate(resultList)
        else -> null
    }
}

internal fun BaseEditMessageUpdate.toEditMediaGroupUpdate(): EditMediaGroupUpdate = (this as? EditMediaGroupUpdate) ?: let {
    when (this) {
        is EditMessageUpdate -> EditMessageMediaGroupUpdate(this)
        is EditChannelPostUpdate -> EditChannelPostMediaGroupUpdate(this)
        else -> error("Unsupported type of ${BaseEditMessageUpdate::class.simpleName}")
    }
}
