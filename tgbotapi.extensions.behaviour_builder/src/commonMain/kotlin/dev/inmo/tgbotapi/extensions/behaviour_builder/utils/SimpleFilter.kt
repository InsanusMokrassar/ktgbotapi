package dev.inmo.tgbotapi.extensions.behaviour_builder.utils

typealias SimpleFilter<T> = suspend (T) -> Boolean

inline fun <T> SimpleFilter(noinline block: SimpleFilter<T>) = block

/**
 * Represent && operation between [this] and [other] on each invocation
 */
operator fun <T> SimpleFilter<T>.times(other: SimpleFilter<T>): SimpleFilter<T> = {
    this(it) && other(it)
}

/**
 * Represent || operation between [this] and [other] on each invocation
 */
operator fun <T> SimpleFilter<T>.plus(other: SimpleFilter<T>): SimpleFilter<T> = {
    this(it) || other(it)
}

/**
 * Represent reversing of [this] invocation
 */
operator fun <T> SimpleFilter<T>.not(): SimpleFilter<T> = {
    !this(it)
}
