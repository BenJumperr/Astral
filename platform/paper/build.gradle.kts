plugins {
    kotlin("jvm")
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("io.papermc.paperweight.userdev") version "1.7.1"
}

group = "xyz.aeroitems"
version = rootProject.version

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

kotlin {
    jvmToolchain(17)
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    implementation(project(":core:api"))
    implementation(project(":core:common"))

    paperweight.paperDevBundle("1.20.1-R0.1-SNAPSHOT")

    implementation(kotlin("stdlib"))
}

tasks {

    assemble {
        dependsOn(reobfJar)
    }

    shadowJar {
        archiveClassifier.set("")
        minimize()
    }

    build {
        dependsOn(shadowJar)
    }
}
