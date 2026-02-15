plugins {
    kotlin("jvm")
    `java-library`
}

group = "xyz.aeroitems"
version = rootProject.version

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {

    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    implementation("io.lettuce:lettuce-core:6.3.2.RELEASE")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.0")
    implementation("it.unimi.dsi:fastutil:8.5.12")
    implementation("io.micrometer:micrometer-core:1.12.5")

    api("org.slf4j:slf4j-api:2.0.12")

    implementation("org.joml:joml:1.10.5")
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "17"
            freeCompilerArgs += listOf(
                "-Xjvm-default=all",
                "-Xcontext-receivers"
            )
        }
    }
}