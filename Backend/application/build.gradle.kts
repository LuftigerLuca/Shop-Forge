plugins {
    alias(libs.plugins.kotlin.jvm)
    application
}

dependencies {
    implementation(project(":domain"))
    testImplementation(libs.kotlin.test)
}
