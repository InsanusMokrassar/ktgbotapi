package dev.inmo.tgbotapi.extensions.behaviour_builder

import dev.inmo.kslog.common.KSLog
import dev.inmo.kslog.common.error
import dev.inmo.tgbotapi.types.update.abstracts.Update

/**
 * Contains [SubAction]s which will be used in [subcontextInitialAction] in order they has been passed in [subactions].
 */
class CombinedSubcontextInitialAction(
    val subactions: List<SubAction>,
    private val logger: KSLog = KSLog("CombinedSubcontextInitialAction_${subactions.size}")
) {
    /**
     * Represents interface-like variant of [CustomBehaviourContextAndTypeReceiver] useful for [BehaviourContext]
     * builders
     */
    fun interface SubAction {
        suspend operator fun BehaviourContext.invoke(update: Update)

        class Callback(private val action: CustomBehaviourContextAndTypeReceiver<BehaviourContext, Unit, Update>) : SubAction {
            override suspend fun BehaviourContext.invoke(update: Update) {
                action(update)
            }
        }
    }
    val subcontextInitialAction: CustomBehaviourContextAndTypeReceiver<BehaviourContext, Unit, Update> = { update ->
        subactions.forEach { subaction ->
            with(subaction) {
                runCatching {
                    invoke(update)
                }.onFailure {
                    logger.error("Unable to execute $subaction for update $update", it)
                }
            }
        }
    }
}

/**
 * Build [CombinedSubcontextInitialAction] with [block] and return callback, which appropriate for [BehaviourContext]
 * builders with `subcontextInitialAction` argument
 */
inline fun buildSubcontextInitialActionWithSubActions(
    block: MutableList<CombinedSubcontextInitialAction.SubAction>.() -> Unit
): CustomBehaviourContextAndTypeReceiver<BehaviourContext, Unit, Update> {
    val list = mutableListOf<CombinedSubcontextInitialAction.SubAction>()
    list.block()
    return CombinedSubcontextInitialAction(
        list.toList()
    ).subcontextInitialAction
}

/**
 * Build [CombinedSubcontextInitialAction] with [block] and return callback, which appropriate for [BehaviourContext]
 * builders with `subcontextInitialAction` argument
 */
inline fun buildSubcontextInitialAction(
    block: MutableList<CustomBehaviourContextAndTypeReceiver<BehaviourContext, Unit, Update>>.() -> Unit
): CustomBehaviourContextAndTypeReceiver<BehaviourContext, Unit, Update> {
    val list = mutableListOf<CustomBehaviourContextAndTypeReceiver<BehaviourContext, Unit, Update>>()
    list.block()
    return CombinedSubcontextInitialAction(
        list.map {
            CombinedSubcontextInitialAction.SubAction.Callback(it)
        }
    ).subcontextInitialAction
}
