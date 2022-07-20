import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `maven-publish`
    kotlin("jvm") version "1.7.10"
}

group = "dev.narcos.plugins"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
    maven {
        url = uri("https://repo.unethicalite.net/releases/")
        mavenContent {
            releasesOnly()
        }
    }
    maven {
        url = uri("https://repo.unethicalite.net/snapshots/")
        mavenContent {
            snapshotsOnly()
        }
    }
}

dependencies {
    // Unethicalite
    implementation("net.unethicalite:runelite-api:1.0.1")
    implementation("net.unethicalite:runelite-client:1.0.1")

    // Kotlin
    implementation(group = "org.jetbrains.kotlin", name = "kotlin-stdlib")

    // Libs
    implementation("com.google.inject:guice:5.0.1")
    implementation("javax.annotation:javax.annotation-api:1.3.2")
    implementation("org.pf4j:pf4j:3.6.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
