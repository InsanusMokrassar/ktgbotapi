package dev.inmo.tgbotapi.utils

@RequiresOptIn(
    "It is possible, that behaviour of this thing will be changed later or this feature will be removed",
    RequiresOptIn.Level.WARNING
)
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.CONSTRUCTOR,
    AnnotationTarget.FIELD,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.TYPEALIAS
)
annotation class PreviewFeature

const val lowLevelRiskFeatureMessage = "This method is low-level and not recommended to direct use"
@RequiresOptIn(
    "This feature can work unstable and may have some restrictions in Telegram System",
    RequiresOptIn.Level.WARNING
)
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.CONSTRUCTOR,
    AnnotationTarget.FIELD,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.TYPEALIAS
)
annotation class RiskFeature(val message: String = lowLevelRiskFeatureMessage)
