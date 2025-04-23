plugins {
    kotlin("jvm")
    alias(libs.plugins.ktlint)
}

project.description = "Linter rules for tgbotapi"
group = "dev.inmo.tgbotapi"

dependencies {
    ktlint(libs.ktlint.cli)

    implementation(libs.ktlint.cli.ruleset.core)
    implementation(libs.ktlint.ruleEngine.core)

    testImplementation(libs.ktlint.test)
}
