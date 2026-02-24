package dev.inmo.tgbotapi.utils

import dev.inmo.tgbotapi.types.DraftId
import kotlinx.coroutines.NonCancellable.isActive
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.random.Random

class DraftIdAllocator {
    val allocated = mutableSetOf<DraftId>()
    val mutex = Mutex()

    suspend fun allocate(): DraftId = mutex.withLock {
        while (isActive) {
            val draftId = DraftId(Random.nextLong())
            if (allocated.add(draftId)) {
                return draftId
            }
        }
        error("Unable to allocate a unique draft ID")
    }
    suspend fun free(draftId: DraftId) = mutex.withLock {
        allocated.remove(draftId)
    }
}