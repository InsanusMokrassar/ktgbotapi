package dev.inmo.tgbotapi.utils.internal

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
internal annotation class ClassCastsIncluded(val typesRegex: String = ".*", val excludeRegex: String = "")
