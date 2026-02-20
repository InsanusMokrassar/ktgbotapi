package dev.inmo.tgbotapi.extensions.api.gifts

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.gifts.GetUserGifts
import dev.inmo.tgbotapi.types.OwnedGifts
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.gifts.GiftSentOrReceivedEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

public suspend fun TelegramBot.getUserGifts(
    userId: UserId,
    excludeUnlimited: Boolean = false,
    excludeLimitedUpgradable: Boolean = false,
    excludeLimitedNonUpgradable: Boolean = false,
    excludeFromBlockchain: Boolean = false,
    excludeUnique: Boolean = false,
    sortByPrice: Boolean = false,
    offset: String? = null,
    limit: Int? = null
): OwnedGifts<GiftSentOrReceivedEvent> = execute(
    GetUserGifts(
        userId,
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

public fun TelegramBot.getUserGiftsFlow(
    userId: UserId,
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
            getUserGifts(
                userId,
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
