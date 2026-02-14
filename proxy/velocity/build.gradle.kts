plugins {
    kotlin("jvm")
}

group = "xyz.aeroitems"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("com.velocitypowered:velocity-api:3.3.0-SNAPSHOT")
    annotationProcessor("com.velocitypowered:velocity-api:3.3.0-SNAPSHOT")

    implementation(project(":core:common"))
    implementation(project(":core:network"))
    implementation(project(":core:api"))

    implementation("io.lettuce:lettuce-core:6.3.2.RELEASE")
    implementation("org.slf4j:slf4j-simple:2.0.9")
}
