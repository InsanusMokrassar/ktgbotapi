package dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories

fun interface MarkerFactory<T, M> {
    suspend operator fun invoke(data: T): M
}
