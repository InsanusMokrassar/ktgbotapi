package dev.inmo.tgbotapi.extensions.behaviour_builder

import dev.inmo.kslog.common.KSLog
import dev.inmo.kslog.common.error
import dev.inmo.tgbotapi.types.update.abstracts.Update

class CombinedSubcontextInitialAction(
    val subactions: List<SubAction>,
    private val logger: KSLog = KSLog("CombinedSubcontextInitialAction_${subactions.size}")
) {
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

inline fun buildSubcontextInitialActionWithSubActions(
    block: MutableList<CombinedSubcontextInitialAction.SubAction>.() -> Unit
): CustomBehaviourContextAndTypeReceiver<BehaviourContext, Unit, Update> {
    val list = mutableListOf<CombinedSubcontextInitialAction.SubAction>()
    list.block()
    return CombinedSubcontextInitialAction(
        list.toList()
    ).subcontextInitialAction
}

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
