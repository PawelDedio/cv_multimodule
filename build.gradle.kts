import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektPlugin
import io.gitlab.arturbosch.detekt.detekt

// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
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
        maven(url = Libraries.Gson.mavenUrl)
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
