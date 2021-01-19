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

inline fun <T> Deferred<T>.asAction() = DeferredAction(this) { it }

suspend fun <O> BehaviourContext.oneOfActions(
    deferredActions: Iterable<DeferredAction<*, O>>
) = deferredActions.invokeFirstOf(scope)

suspend fun <O> BehaviourContext.oneOfActions(
    vararg deferredActions: DeferredAction<*, O>
) = this@oneOfActions.oneOfActions(deferredActions.toList())

suspend fun <O> BehaviourContext.oneOf(
    deferredActions: Iterable<Deferred<O>>
) = oneOfActions(deferredActions.map { it.asAction() })

suspend fun <O> BehaviourContext.oneOf(
    vararg deferredActions: Deferred<O>
) = oneOf(deferredActions.toList())
