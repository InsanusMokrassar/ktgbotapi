package dev.inmo.tgbotapi.utils

import dev.inmo.micro_utils.coroutines.suspendPoint
import dev.inmo.tgbotapi.types.DraftId
import kotlinx.coroutines.NonCancellable.isActive
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.math.absoluteValue
import kotlin.random.Random

class DraftIdAllocator {
    val allocated = mutableSetOf<DraftId>()
    val mutex = Mutex()

    suspend fun allocate(): DraftId {
        mutex.withLock {
            while (true) {
                suspendPoint() // required to be able for current code to stop
                val id = Random.nextLong()
                if (id == 0L) continue
                val draftId = DraftId(id)
                if (allocated.add(draftId)) {
                    return draftId
                }
            }
        }
        error("Unable to allocate a unique draft ID")
    }
    suspend fun free(draftId: DraftId) = mutex.withLock {
        allocated.remove(draftId)
    }
}