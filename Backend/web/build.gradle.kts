plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(project(":application"))
    implementation(project(":domain"))

    implementation(platform(libs.bom.spring.boot))
    implementation(libs.spring.boot.web)
    implementation(libs.spring.boot.security)
    implementation(libs.jackson.kotlin)
    implementation(libs.jwt.api)
    implementation(libs.kotlin.reflect)

    runtimeOnly(libs.jwt.impl)
    runtimeOnly(libs.jwt.jackson)

    testImplementation(libs.kotest.runner)
    testImplementation(libs.kotest.assertions)
}
