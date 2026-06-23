plugins {
    alias(libs.plugins.kotlin.jvm) apply false
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
}
