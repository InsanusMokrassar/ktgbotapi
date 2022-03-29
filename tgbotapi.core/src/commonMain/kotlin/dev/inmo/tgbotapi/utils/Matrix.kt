package dev.inmo.tgbotapi.utils

import dev.inmo.tgbotapi.types.buttons.Matrix

/**
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.InlineKeyboardRowBuilder
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.ReplyKeyboardRowBuilder
 */
@Deprecated("This functionality will be removed soon")
fun <T> row(block: RowBuilder<T>.() -> Unit): List<T> {
    return RowBuilder<T>().also(block).row
}

/**
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.InlineKeyboardRowBuilder
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.ReplyKeyboardRowBuilder
 */
@Deprecated("This functionality will be removed soon")
fun <T> MatrixBuilder<T>.row(block: RowBuilder<T>.() -> Unit) {
    add(RowBuilder<T>().also(block).row)
}

/**
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.InlineKeyboardRowBuilder
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.ReplyKeyboardRowBuilder
 */
@Deprecated("This functionality will be removed soon")
fun <T> MatrixBuilder<T>.row(vararg elements: T) {
    add(elements.toList())
}

/**
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.InlineKeyboardBuilder
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.ReplyKeyboardBuilder
 */
@Deprecated("This functionality will be removed soon")
fun <T> matrix(block: MatrixBuilder<T>.() -> Unit): Matrix<T> {
    return MatrixBuilder<T>().also(block).matrix
}

/**
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.InlineKeyboardBuilder
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.ReplyKeyboardBuilder
 */
@Deprecated("This functionality will be removed soon")
fun <T> flatMatrix(block: RowBuilder<T>.() -> Unit): Matrix<T> {
    return MatrixBuilder<T>().apply {
        row(block)
    }.matrix
}

/**
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.InlineKeyboardBuilder
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.ReplyKeyboardBuilder
 */
@Deprecated("This functionality will be removed soon")
fun <T> flatMatrix(vararg elements: T): Matrix<T> {
    return MatrixBuilder<T>().apply {
        row { elements.forEach { +it } }
    }.matrix
}

/**
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.InlineKeyboardRowBuilder
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.ReplyKeyboardRowBuilder
 */
@Deprecated("This functionality will be removed soon")
operator fun <T> RowBuilder<T>.plus(t: T) = add(t)

open class RowBuilder<T> {
    private val mutRow: MutableList<T> = ArrayList()
    val row: List<T>
        get() = mutRow

    fun add(t: T) = mutRow.add(t)
    operator fun T.unaryPlus() = add(this)
}

open class MatrixBuilder<T> {
    private val mutMatrix: MutableList<List<T>> = ArrayList()
    val matrix: Matrix<T>
        get() = mutMatrix.toList()

    fun add(t: List<T>) = mutMatrix.add(t)
    operator fun plus(t: List<T>) = add(t)
    operator fun T.unaryPlus() = add(listOf(this))
}
