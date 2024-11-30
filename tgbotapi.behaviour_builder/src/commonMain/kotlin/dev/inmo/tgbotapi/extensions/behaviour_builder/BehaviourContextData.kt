package dev.inmo.tgbotapi.extensions.behaviour_builder

data class BehaviourContextData(
    private val data: MutableMap<String, Any?> = mutableMapOf()
) : MutableMap<String, Any?> by data {
    fun mergedWith(other: BehaviourContextData): BehaviourContextData = BehaviourContextData(
        data.toMutableMap().apply {
            putAll(other.data)
        }
    )

    fun include(other: BehaviourContextData) {
        data.putAll(other.data)
    }

    operator fun plus(other: BehaviourContextData): BehaviourContextData = mergedWith(other)
}
