package dev.inmo.tgbotapi.types.stories

import dev.inmo.micro_utils.common.Percentage
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.utils.Percentage0_100Serializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StoryAreaPosition(
    @Serializable(Percentage0_100Serializer::class)
    @SerialName(xPercentageField)
    val x: Percentage,
    @Serializable(Percentage0_100Serializer::class)
    @SerialName(yPercentageField)
    val y: Percentage,
    @Serializable(Percentage0_100Serializer::class)
    @SerialName(widthPercentageField)
    val width: Percentage,
    @Serializable(Percentage0_100Serializer::class)
    @SerialName(heightPercentageField)
    val height: Percentage,
    @SerialName(rotationAngleField)
    val rotationAngle: Double,
    @Serializable(Percentage0_100Serializer::class)
    @SerialName(cornerRadiusPercentageField)
    val cornerRadius: Percentage,
)
