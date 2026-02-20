package dev.inmo.tgbotapi.extensions.api.gifts

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.gifts.GetChatGifts
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.OwnedGifts
import dev.inmo.tgbotapi.types.gifts.GiftSentOrReceivedEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

public suspend fun TelegramBot.getChatGifts(
    chatId: ChatIdentifier,
    excludeUnsaved: Boolean = false,
    excludeSaved: Boolean = false,
    excludeUnlimited: Boolean = false,
    excludeLimitedUpgradable: Boolean = false,
    excludeLimitedNonUpgradable: Boolean = false,
    excludeFromBlockchain: Boolean = false,
    excludeUnique: Boolean = false,
    sortByPrice: Boolean = false,
    offset: String? = null,
    limit: Int? = null
): OwnedGifts<GiftSentOrReceivedEvent> = execute(
    GetChatGifts(
        chatId,
        excludeUnsaved,
        excludeSaved,
        excludeUnlimited,
        excludeLimitedUpgradable,
        excludeLimitedNonUpgradable,
        excludeFromBlockchain,
        excludeUnique,
        sortByPrice,
        offset,
        limit
    )
)

public fun TelegramBot.getChatGiftsFlow(
    chatId: ChatIdentifier,
    excludeUnsaved: Boolean = false,
    excludeSaved: Boolean = false,
    excludeUnlimited: Boolean = false,
    excludeLimitedUpgradable: Boolean = false,
    excludeLimitedNonUpgradable: Boolean = false,
    excludeFromBlockchain: Boolean = false,
    excludeUnique: Boolean = false,
    sortByPrice: Boolean = false,
    initialOffset: String? = null,
    limit: Int? = null,
    onErrorContinueChecker: suspend (Throwable?) -> Boolean = { false }
): Flow<OwnedGifts<GiftSentOrReceivedEvent>> = flow {
    var currentOffset = initialOffset
    do {
        val response = runCatching {
            getChatGifts(
                chatId,
                excludeUnsaved,
                excludeSaved,
                excludeUnlimited,
                excludeLimitedUpgradable,
                excludeLimitedNonUpgradable,
                excludeFromBlockchain,
                excludeUnique,
                sortByPrice,
                currentOffset,
                limit
            )
        }
        if (response.isSuccess) {
            val result = response.getOrThrow()
            emit(result)
            currentOffset = result.nextOffset
        } else {
            if (onErrorContinueChecker(response.exceptionOrNull())) {
                continue
            } else {
                break
            }
        }
    } while (currentOffset != null)
}
