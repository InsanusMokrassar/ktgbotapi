package dev.inmo.tgbotapi.extensions.behaviour_builder

data class BehaviourContextData(
    val data: MutableMap<String, Any?> = mutableMapOf()
) {
    fun mergedWith(other: BehaviourContextData): BehaviourContextData = BehaviourContextData(
        data.toMutableMap().apply {
            putAll(other.data)
        }
    )

    operator fun plus(other: BehaviourContextData): BehaviourContextData = mergedWith(other)
}
