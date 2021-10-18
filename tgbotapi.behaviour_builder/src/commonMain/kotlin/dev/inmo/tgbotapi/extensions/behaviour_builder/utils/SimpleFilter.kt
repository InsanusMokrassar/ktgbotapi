package dev.inmo.tgbotapi.extensions.behaviour_builder.utils

typealias SimpleFilter<T> = suspend (T) -> Boolean

inline fun <T> SimpleFilter(noinline block: SimpleFilter<T>) = block

/**
 * Makes an AND (&&) operation between [this] and [other]
 */
operator fun <T> SimpleFilter<T>.times(other: SimpleFilter<T>): SimpleFilter<T> = {
    this(it) && other(it)
}

/**
 * Makes an OR (||) operation between [this] and [other]
 */
operator fun <T> SimpleFilter<T>.plus(other: SimpleFilter<T>): SimpleFilter<T> = {
    this(it) || other(it)
}

/**
 * Reverse results of [this]
 */
operator fun <T> SimpleFilter<T>.not(): SimpleFilter<T> = {
    !this(it)
}
