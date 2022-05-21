package dev.inmo.tgbotapi.utils

import dev.inmo.tgbotapi.types.buttons.Matrix

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
