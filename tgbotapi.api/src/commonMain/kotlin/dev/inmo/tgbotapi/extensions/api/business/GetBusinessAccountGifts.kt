package dev.inmo.tgbotapi.extensions.api.business

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.business_connection.GetBusinessAccountGifts
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.OwnedGifts
import dev.inmo.tgbotapi.types.gifts.GiftSentOrReceivedEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.runCatching

public suspend fun TelegramBot.getBusinessAccountGifts(
    businessConnectionId: BusinessConnectionId,
    excludeUnsaved: Boolean = false,
    excludeSaved: Boolean = false,
    excludeUnlimited: Boolean = false,
    excludeLimitedUpgradable: Boolean = false,
    excludeLimitedNonUpgradable: Boolean = false,
    excludeUnique: Boolean = false,
    excludeFromBlockchain: Boolean = false,
    sortByPrice: Boolean = false,
    offset: String? = null,
    limit: Int? = null
): OwnedGifts<GiftSentOrReceivedEvent.ReceivedInBusinessAccount> = execute(
    GetBusinessAccountGifts(
        businessConnectionId,
        excludeUnsaved,
        excludeSaved,
        excludeUnlimited,
        excludeLimitedUpgradable,
        excludeLimitedNonUpgradable,
        excludeUnique,
        excludeFromBlockchain,
        sortByPrice,
        offset,
        limit
    )
)

/**
 * Creates a flow that emits business account gifts in a paginated manner.
 *
 * This function will automatically handle pagination by using the `nextOffset` from each response
 * to fetch the next page of gifts until there are no more gifts to fetch.
 *
 * @param businessConnectionId The ID of the business connection
 * @param excludeUnsaved Whether to exclude unsaved gifts
 * @param excludeSaved Whether to exclude saved gifts
 * @param excludeUnlimited Whether to exclude unlimited gifts
 * @param excludeLimitedUpgradable Whether to exclude limited upgradable gifts
 * @param excludeLimitedNonUpgradable Whether to exclude limited non-upgradable gifts
 * @param excludeUnique Whether to exclude unique gifts
 * @param excludeFromBlockchain Whether to exclude gifts from blockchain
 * @param sortByPrice Whether to sort gifts by price
 * @param initialOffset The initial offset to start fetching from. If null, starts from the beginning
 * @param limit The maximum number of gifts to fetch per request
 * @param onErrorContinueChecker A function that determines whether to continue fetching on error.
 *                              Returns true to continue, false to stop. Default is to stop on any error.
 * @return A flow that emits [OwnedGifts] containing the fetched gifts
 */
public fun TelegramBot.getBusinessAccountGiftsFlow(
    businessConnectionId: BusinessConnectionId,
    excludeUnsaved: Boolean = false,
    excludeSaved: Boolean = false,
    excludeUnlimited: Boolean = false,
    excludeLimitedUpgradable: Boolean = false,
    excludeLimitedNonUpgradable: Boolean = false,
    excludeUnique: Boolean = false,
    excludeFromBlockchain: Boolean = false,
    sortByPrice: Boolean = false,
    initialOffset: String? = null,
    limit: Int? = null,
    onErrorContinueChecker: suspend (Throwable?) -> Boolean = { false }
): Flow<OwnedGifts<GiftSentOrReceivedEvent.ReceivedInBusinessAccount>> = flow {
    var currentOffset = initialOffset
    do {
        val response = runCatching {
            getBusinessAccountGifts(
                businessConnectionId,
                excludeUnsaved,
                excludeSaved,
                excludeUnlimited,
                excludeLimitedUpgradable,
                excludeLimitedNonUpgradable,
                excludeUnique,
                excludeFromBlockchain,
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