package dev.inmo.tgbotapi.extensions.behaviour_builder

typealias StateHandlingErrorHandler<T> = suspend (T, Throwable) -> T?
val DefaultStateHandlingErrorHandler: StateHandlingErrorHandler<*> = { _, _ -> null }
inline fun <T> defaultStateHandlingErrorHandler(): StateHandlingErrorHandler<T> = DefaultStateHandlingErrorHandler as StateHandlingErrorHandler<T>
