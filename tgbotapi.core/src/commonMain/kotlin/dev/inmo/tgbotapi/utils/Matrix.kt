@file:Suppress("KDocUnresolvedReference")

package dev.inmo.tgbotapi.utils

import dev.inmo.micro_utils.common.withReplaced
import dev.inmo.tgbotapi.types.buttons.Matrix

/**
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.InlineKeyboardRowBuilder
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.ReplyKeyboardRowBuilder
 */
inline fun <T> row(block: RowBuilder<T>.() -> Unit): List<T> {
    return RowBuilder<T>().apply(block).row
}

/**
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.InlineKeyboardRowBuilder
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.ReplyKeyboardRowBuilder
 */
inline fun <T> MatrixBuilder<T>.row(block: RowBuilder<T>.() -> Unit) {
    add(RowBuilder<T>().apply(block).row)
}

/**
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.InlineKeyboardRowBuilder
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.ReplyKeyboardRowBuilder
 */
fun <T> MatrixBuilder<T>.row(vararg elements: T) {
    add(elements.toList())
}

/**
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.InlineKeyboardBuilder
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.ReplyKeyboardBuilder
 */
inline fun <T> matrix(block: MatrixBuilder<T>.() -> Unit): Matrix<T> {
    return MatrixBuilder<T>().also(block).matrix
}

/**
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.InlineKeyboardBuilder
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.ReplyKeyboardBuilder
 */
inline fun <T> flatMatrix(block: RowBuilder<T>.() -> Unit): Matrix<T> {
    return MatrixBuilder<T>().apply {
        row(block)
    }.matrix
}

/**
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.InlineKeyboardBuilder
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.ReplyKeyboardBuilder
 */
fun <T> flatMatrix(vararg elements: T): Matrix<T> {
    return MatrixBuilder<T>().apply {
        row { elements.forEach { +it } }
    }.matrix
}

/**
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.InlineKeyboardRowBuilder
 * @see dev.inmo.tgbotapi.extensions.utils.types.buttons.ReplyKeyboardRowBuilder
 */
operator fun <T> RowBuilder<T>.plus(t: T) = add(t)

open class RowBuilder<T> {
    private val mutRow: MutableList<T> = ArrayList()
    val row: List<T>
        get() = mutRow

    fun add(t: T) = mutRow.add(t)
    operator fun T.unaryPlus() = add(this)

    fun replace(i: Int, new: T) {
        mutRow[i] = new
    }
    fun replace(old: T, new: T): Boolean {
        val i = mutRow.indexOf(old).takeIf { it > -1 } ?: return false
        replace(i, new)
        return mutRow[i] == new
    }
    fun remove(i: Int): T {
        return mutRow.removeAt(i)
    }
}

open class MatrixBuilder<T> {
    private val mutMatrix: MutableList<List<T>> = ArrayList()
    val matrix: Matrix<T>
        get() = mutMatrix.toList()

    fun add(t: List<T>) = mutMatrix.add(t)
    operator fun plus(t: List<T>) = add(t)
    operator fun T.unaryPlus() = add(listOf(this))

    fun modifyRow(i: Int, block: RowBuilder<T>.() -> Unit) {
        val exists = matrix[i]
        val rowBuilder = RowBuilder<T>()
        exists.forEach { rowBuilder.add(it) }
        mutMatrix[i] = rowBuilder.apply(block).row
    }
    fun modifyRow(row: List<T>, block: RowBuilder<T>.() -> Unit): Boolean {
        val i = mutMatrix.indexOf(row).takeIf { it > -1 } ?: return false
        modifyRow(i, block)
        return true
    }
    fun remove(i: Int): List<T> {
        return mutMatrix.removeAt(i)
    }
}
