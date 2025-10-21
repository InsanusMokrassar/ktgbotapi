package dev.inmo.tgbotapi.utils

import kotlin.reflect.KClass

fun <T : Throwable> Throwable.causedBy(kclass: KClass<T>, additionalFilterOnHappened: (T) -> T? = { it }): T? {
    var current = this
    while (kclass.isInstance(current) == false) {
        when {
            kclass.isInstance(current) -> return additionalFilterOnHappened(current as T)
            else -> current = current.cause ?: return null
        }
    }

    return current as T
}
