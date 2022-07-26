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

java {
    withSourcesJar()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "dev.narcos.plugins"
            artifactId = "common"
            version = "local"

            from(components["java"])
        }
    }
}

dependencies {
    // Unethicalite
    compileOnly("net.unethicalite:runelite-api:${Versions.unethicaliteVersion}")
    compileOnly("net.unethicalite:runelite-client:${Versions.unethicaliteVersion}")

    // Kotlin
    compileOnly(group = "org.jetbrains.kotlin", name = "kotlin-stdlib")

    // Libs
    compileOnly("com.google.inject:guice:5.0.1")
    compileOnly("javax.annotation:javax.annotation-api:1.3.2")
    compileOnly("org.pf4j:pf4j:3.6.0")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "11"
    }

    register("buildRelease") {
        group = "folks"
        dependsOn(build)
        finalizedBy(named("publishToMavenLocal"))
    }
}

object Versions {
    const val unethicaliteVersion = "1.0.6"
}
