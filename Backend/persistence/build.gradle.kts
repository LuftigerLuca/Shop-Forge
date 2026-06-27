plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.jpa)
}

dependencies {
    implementation(platform(libs.bom.spring.boot))
    implementation(project(":domain"))
    implementation(libs.spring.boot.jpa)

    runtimeOnly(libs.h2)

    testImplementation(libs.kotest.runner)
    testImplementation(libs.kotest.assertions)
}
