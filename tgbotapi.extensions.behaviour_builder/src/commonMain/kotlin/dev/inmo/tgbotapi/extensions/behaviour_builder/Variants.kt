package dev.inmo.tgbotapi.extensions.behaviour_builder

import dev.inmo.micro_utils.coroutines.DeferredAction
import dev.inmo.micro_utils.coroutines.invokeFirstOf
import kotlinx.coroutines.*

suspend fun <T> BehaviourContext.parallel(
    action: BehaviourContextReceiver<T>
) = async {
    action()
}

inline infix fun <T, O> Deferred<T>.withAction(noinline callback: suspend (T) -> O) = DeferredAction(this, callback)

suspend fun <O> BehaviourContext.oneOf(
    deferredActions: Iterable<DeferredAction<*, O>>
) = deferredActions.invokeFirstOf(scope)

suspend fun <O> BehaviourContext.oneOf(
    vararg deferredActions: DeferredAction<*, O>
) = oneOf(deferredActions.toList())
