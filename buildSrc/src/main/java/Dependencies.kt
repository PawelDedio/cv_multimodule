object ApplicationConfig {
    const val id = "pl.dedio.cvmultimodule"
    const val projectName = "CV multimodule"
}

object BuildTypes {
    const val debug = "debug"
    const val release = "release"
}

object Modules {
    const val app = ":app"
    const val buildSrc = ":buildSrc"
}

object ReleaseVersions {
    const val versionCode = 1
    const val versionName = "1.0.0"
}

object AndroidVersions {
    const val minSdk = 21
    const val targetSdk = 29
    const val compileSdk = 29
    const val buildTools = "29.0.3"
}

object ClassPathDependencies {
    object Versions {
        const val androidGradle = "4.0.1"
    }

    const val r8 = Libraries.R8.core
    const val androidGradle = "com.android.tools.build:gradle:${Versions.androidGradle}"
    const val kotlinGradle =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Libraries.Kotlin.Versions.kotlin}"
    const val junit = "de.mannodermaus.gradle.plugins:android-junit5:${Libraries.JUnit.Versions.jUnitPlugin}"

    @Suppress("MaxLineLength")
    const val navigationSafeArgs =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Libraries.NavigationComponents.Versions.navigationComponents}"
}

object BuildPlugins {
    const val androidApplication = "com.android.application"
    const val androidDynamicFeature = "com.android.dynamic-feature"
    const val androidLibrary = "com.android.library"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
    const val kotlinKapt = "kotlin-kapt"
    const val junit5 = "de.mannodermaus.android-junit5"
    const val navigationSafeArgs = "androidx.navigation.safeargs.kotlin"
}

object Libraries {

    //https://github.com/square/okhttp/ An HTTP client for Android, Kotlin, and Java.
    object OkHttp {
        object Versions {
            const val okHttp = "4.8.0"
        }

        const val core = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
        const val logger = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
    }

    //https://github.com/square/retrofit Type-safe HTTP client for Android and Java
    object Retrofit {
        object Versions {
            const val retrofit = "2.9.0"
        }

        const val core = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val converter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    }

    //https://github.com/google/gson A Java serialization/deserialization
    // library to convert Java Objects into JSON and back
    object Gson {
        object Versions {
            const val gson = "2.8.6"
        }

        const val core = "com.google.code.gson:gson:${Versions.gson}"
    }

    //https://github.com/mockk/mockk mocking library for Kotlin
    object Mockk {
        object Versions {
            const val mockk = "1.10.0"
        }

        const val core = "io.mockk:mockk:${Versions.mockk}"
    }

    //https://github.com/square/leakcanary LeakCanary is a memory leak detection library for Android
    object LeakCanary {
        object Versions {
            const val leakCanary = "2.4"
        }

        const val core = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"
    }

    //https://blog.jetbrains.com/kotlin/category/releases/
    object Kotlin {
        object Versions {
            const val kotlin = "1.3.70"
        }

        const val core = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
        const val reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
    }

    //https://github.com/google/dagger A fast dependency injector for Android and Java
    object Dagger {
        object Versions {
            const val dagger = "2.28.3"
        }

        const val core = "com.google.dagger:dagger:${Versions.dagger}"
        const val compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    }

    //https://github.com/Kotlin/kotlinx.coroutines Library support for Kotlin coroutines
    object Coroutines {
        object Versions {
            const val coroutines = "1.3.2"
        }

        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val android =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    }

    //https://junit.org/junit5/docs/snapshot/release-notes/ Framework for Unit tests
    object JUnit {
        object Versions {
            //https://github.com/mannodermaus/android-junit5
            const val jUnitPlugin = "1.6.2.0"
            const val jUnit = "5.7.0-M1"
        }

        const val core = "org.junit.jupiter:junit-jupiter-api:${Versions.jUnit}"
        const val engine = "org.junit.jupiter:junit-jupiter-engine:${Versions.jUnit}"
    }

    //https://developer.android.com/jetpack/androidx/releases/appcompat
    object Compat {
        object Versions {
            const val compat = "1.1.0"
        }

        const val appCompat = "androidx.appcompat:appcompat:${Versions.compat}"
        const val coreKtx = "androidx.core:core-ktx:${Versions.compat}"
    }

    //https://developer.android.com/jetpack/androidx/releases/constraintlayout
    object ConstraintLayout {
        object Versions {
            const val constraintLayout = "1.1.3"
        }

        const val core = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    }

    //https://mvnrepository.com/artifact/com.google.android.material/material?repo=google
    object MaterialDesign {
        object Versions {
            const val materialDesign = "1.1.0"
        }

        const val core = "com.google.android.material:material:${Versions.materialDesign}"
    }

    //https://developer.android.com/guide/navigation/navigation-dynamic Navigation component for dynamic features
    object NavigationComponents {
        object Versions {
            const val navigationComponents = "2.3.0"
        }

        const val fragmentKtx =
            "androidx.navigation:navigation-fragment-ktx:${Versions.navigationComponents}"
        const val uiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigationComponents}"
        const val dynamicFeaturesFragment =
            "androidx.navigation:navigation-dynamic-features-fragment:${Versions.navigationComponents}"
    }

    //https://developer.android.com/jetpack/androidx/releases/lifecycle
    object LifecycleComponents {
        object Versions {
            const val lifecycle = "2.2.0"
        }

        const val extensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
        const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    }

    //https://github.com/arturbosch/detekt a static code analysis tool for the Kotlin
    object Detekt {
        object Versions {
            const val detekt = "1.9.0"
        }

        object Config {
            const val inputPath = "app/src/main/java"
            const val filtersPath = ".*/resources/.*,.*/build/.*"
            const val configPath = "detekt.yml"
        }

        const val core = "io.gitlab.arturbosch.detekt"
    }

    //https://github.com/JakeWharton/timber Android logger
    object Timber {
        object Versions {
            const val timber = "4.7.1"
        }

        const val core = "com.jakewharton.timber:timber:${Versions.timber}"
    }

    //https://github.com/bumptech/glide An image loading and caching library for Android
    object Glide {
        object Versions {
            const val glide = "4.11.0"
        }

        const val core = "com.github.bumptech.glide:glide:${Versions.glide}"
        const val compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    }

    //https://r8.googlesource.com/r8/
    object R8 {
        object Versions {
            const val r8 = "2.0.97"
        }

        const val mavenUrl = "http://storage.googleapis.com/r8-releases/raw"
        const val core = "com.android.tools:r8:${Versions.r8}"
    }
}