pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

rootProject.name = "Astral"

include(
    "core:api",
    "core:common",
    "core:network",
    "core:scheduler",
    "core:profiler",

    "platform:paper",
    "proxy:velocity",

    "ai:predictor",
    "ai:optimizer",
    "ai:mob-ai"
)