rootProject.name = "DnDShop-backend"

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

include(":domain")
include(":application")
