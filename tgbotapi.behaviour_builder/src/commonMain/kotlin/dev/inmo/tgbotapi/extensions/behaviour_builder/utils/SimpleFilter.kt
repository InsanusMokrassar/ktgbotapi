package dev.inmo.tgbotapi.extensions.behaviour_builder.utils

fun interface SimpleFilter<in T> {
    suspend operator fun invoke(o: T): Boolean
}

val TrueSimpleFilter = SimpleFilter<Any?> { true }
val FalseSimpleFilter = SimpleFilter<Any?> { false }

/**
 * @return [SimpleFilter] which will return true in case when all the items in incoming data passed [this] filter
 */
fun <T> SimpleFilter<T>.listAll() =
    SimpleFilter<Iterable<T>> {
        it.all { this@listAll(it) }
    }

/**
 * @return [SimpleFilter] which will return true in case when there is any item in incoming data passed [this] filter
 */
fun <T> SimpleFilter<T>.listAny() =
    SimpleFilter<Iterable<T>> {
        it.any { this@listAny(it) }
    }

/**
 * @return [SimpleFilter] which will return true in case when there is no any item in incoming data passed [this] filter
 */
fun <T> SimpleFilter<T>.listNone() =
    SimpleFilter<Iterable<T>> {
        it.none { this@listNone(it) }
    }

/**
 * Makes an AND (&&) operation between [this] and [other]
 *
 * * When both arguments are null, [TrueSimpleFilter] will be returned
 */
infix operator fun <T> SimpleFilter<T>?.times(other: SimpleFilter<T>?): SimpleFilter<T> =
    this ?.let {
        other ?.let {
            SimpleFilter {
                this(it) && other(it)
            }
        } ?: it
    } ?: other ?: TrueSimpleFilter

/**
 * Makes an OR (||) operation between [this] and [other]
 *
 * * When both arguments are null, [TrueSimpleFilter] will be returned
 */
infix operator fun <T> SimpleFilter<T>?.plus(other: SimpleFilter<T>?): SimpleFilter<T> =
    this ?.let {
        other ?.let {
            SimpleFilter {
                this(it) || other(it)
            }
        } ?: it
    } ?: other ?: TrueSimpleFilter

/**
 * Reverse results of [this]
 */
operator fun <T> SimpleFilter<T>.not() =
    SimpleFilter<T> {
        !this(it)
    }

/**
 * Works as [not]
 */
operator fun <T> SimpleFilter<T>.unaryMinus() = not()

/**
 * Making +! operation. In fact that means that [other] will be inversed with [not] and that added to [this] via
 * [plus]
 */
operator fun <T> SimpleFilter<T>?.minus(other: SimpleFilter<T>?): SimpleFilter<T> = this + (other ?.not())
