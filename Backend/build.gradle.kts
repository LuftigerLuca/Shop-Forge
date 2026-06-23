plugins {
    alias(libs.plugins.kotlin.jvm) apply false
}

val ktlintConfig by configurations.creating {
    attributes {
        attribute(Bundling.BUNDLING_ATTRIBUTE, getObjects().named(Bundling::class, Bundling.SHADOWED))
    }
}

dependencies {
    ktlintConfig(libs.ktlint)
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
}

val ktlintCheck by tasks.registering(JavaExec::class) {
    group = "verification"
    description = "Check Kotlin code style"
    classpath = ktlintConfig
    mainClass.set("com.pinterest.ktlint.Main")
    args("**/src/**/*.kt", "!**/build/**")
}

val ktlintFormat by tasks.registering(JavaExec::class) {
    group = "formatting"
    description = "Auto-format Kotlin code"
    classpath = ktlintConfig
    mainClass.set("com.pinterest.ktlint.Main")
    args("-F", "**/src/**/*.kt", "!**/build/**")
}
