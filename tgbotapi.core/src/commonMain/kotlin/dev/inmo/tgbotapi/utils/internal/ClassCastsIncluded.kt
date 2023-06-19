package dev.inmo.tgbotapi.utils.internal

import dev.inmo.tgbotapi.utils.RiskFeature

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
@RiskFeature("It is internal API in tgbotapi.core and should not be used outside")
annotation class ClassCastsIncluded(val typesRegex: String = "", val excludeRegex: String = "")

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
@RiskFeature("It is internal API in tgbotapi.core and should not be used outside")
annotation class ClassCastsExcluded
