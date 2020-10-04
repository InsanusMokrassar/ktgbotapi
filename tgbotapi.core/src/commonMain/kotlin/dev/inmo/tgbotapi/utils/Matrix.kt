package dev.inmo.tgbotapi.utils

import dev.inmo.tgbotapi.types.buttons.Matrix

fun <T> row(block: RowBuilder<T>.() -> Unit): List<T> {
    return RowBuilder<T>().also(block).row
}

fun <T> MatrixBuilder<T>.row(block: RowBuilder<T>.() -> Unit) {
    add(RowBuilder<T>().also(block).row)
}

fun <T> MatrixBuilder<T>.row(vararg elements: T) {
    add(elements.toList())
}

fun <T> matrix(block: MatrixBuilder<T>.() -> Unit): Matrix<T> {
    return MatrixBuilder<T>().also(block).matrix
}

fun <T> flatMatrix(block: RowBuilder<T>.() -> Unit): Matrix<T> {
    return MatrixBuilder<T>().apply {
        row(block)
    }.matrix
}

fun <T> flatMatrix(vararg elements: T): Matrix<T> {
    return MatrixBuilder<T>().apply {
        row { elements.forEach { +it } }
    }.matrix
}

operator fun <T> RowBuilder<T>.plus(t: T) = add(t)

class RowBuilder<T> {
    private val mutRow: MutableList<T> = ArrayList()
    val row: List<T>
        get() = mutRow

    fun add(t: T) = mutRow.add(t)
    operator fun T.unaryPlus() = add(this)
}

class MatrixBuilder<T> {
    private val mutMatrix: MutableList<List<T>> = ArrayList()
    val matrix: Matrix<T>
        get() = mutMatrix

    fun add(t: List<T>) = mutMatrix.add(t)
    operator fun plus(t: List<T>) = add(t)
}
