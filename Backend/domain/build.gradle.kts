plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    testImplementation(libs.kotest.runner)
    testImplementation(libs.kotest.assertions)
}
