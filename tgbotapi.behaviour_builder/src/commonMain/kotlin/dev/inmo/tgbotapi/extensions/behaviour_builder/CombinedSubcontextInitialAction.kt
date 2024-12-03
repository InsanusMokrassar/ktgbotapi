package dev.inmo.tgbotapi.extensions.behaviour_builder

import dev.inmo.kslog.common.KSLog
import dev.inmo.kslog.common.error
import dev.inmo.tgbotapi.types.update.abstracts.Update

/**
 * Contains [SubAction]s which will be used in [subcontextInitialAction] in order they has been passed in [subactions].
 *
 * Its [subcontextInitialAction] will iterate over incoming [subactions] until all will be completed successfully OR
 * none will be successful during round of running:
 *
 * * Run all
 * * Exclude from current running successful items
 * * Do again if there are some items to run
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
        val leftSubActions = subactions.toMutableSet()
        val successSubActions = mutableSetOf<SubAction>()
        while (leftSubActions.isNotEmpty()) {
            leftSubActions.forEach { subaction ->
                with(subaction) {
                    runCatching {
                        invoke(update)
                    }.onFailure {
                        logger.error(it) {
                            "Unable to execute $subaction for update $update. Will try on next round"
                        }
                    }.onSuccess {
                        successSubActions.add(subaction)
                    }
                }
            }
            leftSubActions.removeAll(successSubActions)
            if (successSubActions.isEmpty()) {
                logger.error {
                    "Some SubActions have been unable to complete successfully:${leftSubActions.joinToString("\n") { it.toString() }}"
                }
                break
            }
            successSubActions.clear()
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
