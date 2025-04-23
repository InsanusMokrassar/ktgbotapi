package dev.inmo.tgbotapi.extensions.behaviour_builder.utils

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver
import dev.inmo.tgbotapi.types.update.abstracts.Update

/**
 * Makes an OR (||) operation between [this] and [other]
 */
operator fun <T> (BehaviourContextAndTwoTypesReceiver<Boolean, T, Update>).plus(
    other: BehaviourContextAndTwoTypesReceiver<Boolean, T, Update>,
) = BehaviourContextAndTwoTypesReceiver<Boolean, T, Update> { t, update ->
    this@plus(t, update) || other(t, update)
}

/**
 * Makes an AND (&&) operation between [this] and [other]
 */
operator fun <T> (BehaviourContextAndTwoTypesReceiver<Boolean, T, Update>).times(
    other: BehaviourContextAndTwoTypesReceiver<Boolean, T, Update>,
) = BehaviourContextAndTwoTypesReceiver<Boolean, T, Update> { t, update ->
    this@times(t, update) && other(t, update)
}

/**
 * Reverse results of [this]
 */
operator fun <T> (BehaviourContextAndTwoTypesReceiver<Boolean, T, Update>).not() = BehaviourContextAndTwoTypesReceiver<Boolean, T, Update> { t, update ->
    !this@not(t, update)
}
