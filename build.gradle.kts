import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektPlugin
import io.gitlab.arturbosch.detekt.detekt
import java.io.FileInputStream
import java.util.regex.Pattern
import java.util.Properties
import java.net.URI

// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    val kotlin_version by extra("1.3.72")
    repositories {
        google()
        jcenter()
        maven(url = Libraries.R8.mavenUrl)
    }
    dependencies {
        classpath(ClassPathDependencies.r8)
        classpath(ClassPathDependencies.androidGradle)
        classpath(ClassPathDependencies.kotlinGradle)
        classpath(ClassPathDependencies.junit)
        classpath(ClassPathDependencies.navigationSafeArgs)
        "classpath"("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
    }
}

plugins {
    id(Libraries.Detekt.core).version(Libraries.Detekt.Versions.detekt) apply false
}

allprojects {
    repositories {
        google()
        jcenter()
        maven(url = "https://jitpack.io")
    }
}

subprojects {
    apply<DetektPlugin>()

    detekt {
        toolVersion = Libraries.Detekt.Versions.detekt
        input = files("$rootDir/${Libraries.Detekt.Config.inputPath}")
        config.setFrom(files("$rootDir/${Libraries.Detekt.Config.configPath}"))
        buildUponDefaultConfig = true
    }
}

tasks.withType<Detekt> {
    exclude(Libraries.Detekt.Config.filtersPath)
    jvmTarget = "1.8"
}

tasks.register("clean").configure {
    delete("build")
}
