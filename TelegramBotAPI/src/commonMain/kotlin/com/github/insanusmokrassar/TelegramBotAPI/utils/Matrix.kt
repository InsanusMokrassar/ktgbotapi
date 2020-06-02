package com.github.insanusmokrassar.TelegramBotAPI.utils

import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.Matrix

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

class RowBuilder<T> {
    private val mutRow: MutableList<T> = ArrayList()
    val row: List<T>
        get() = mutRow

    fun add(t: T) = mutRow.add(t)
}

class MatrixBuilder<T> {
    private val mutMatrix: MutableList<List<T>> = ArrayList()
    val matrix: Matrix<T>
        get() = mutMatrix

    fun add(t: List<T>) = mutMatrix.add(t)
    operator fun plus(t: List<T>) = add(t)
}
