buildscript {
    repositories {
        google()
        jcenter()

    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.1.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}")
        classpath("org.jetbrains.dokka:dokka-gradle-plugin:${Versions.dokka}")
        classpath("com.vanniktech:gradle-maven-publish-plugin:0.14.2")
    }
}

plugins {
    id("com.github.ben-manes.versions") version "0.38.0"
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.named<com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask>("dependencyUpdates")
    .configure {
        rejectVersionIf {
            isNonStable(candidate.version) && !isNonStable(currentVersion)
        }
    }

fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}


subprojects {

    pluginManager.withPlugin("com.android.library") {
        apply(plugin = "org.jetbrains.dokka")

        tasks.withType<org.jetbrains.dokka.gradle.DokkaTask>().configureEach {
            outputFormat = "html"
            outputDirectory = "$buildDir/javadoc"
        }
        val RELEASE_REPOSITORY_URL by extra("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
        val SNAPSHOT_REPOSITORY_URL by extra("https://s01.oss.sonatype.org/content/repositories/snapshots/")

        apply(plugin = "com.vanniktech.maven.publish")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
