plugins {
    id(BuildPlugins.androidDynamicFeature)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinKapt)
    id(BuildPlugins.kotlinAndroidExtensions)
}

android {
    compileSdkVersion(AndroidVersions.compileSdk)

    defaultConfig {
        minSdkVersion(AndroidVersions.minSdk)
        targetSdkVersion(AndroidVersions.targetSdk)
    }
    buildTypes {
        getByName(BuildTypes.release) {
            isMinifyEnabled = false
            isShrinkResources = false
        }
    }

    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8

        tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
            kotlinOptions.jvmTarget = "1.8"
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(Modules.app))

    implementation(Libraries.Kotlin.core)

    implementation(Libraries.Dagger.core)
    kapt(Libraries.Dagger.compiler)

    implementation(Libraries.Gson.core)
    implementation(Libraries.Gson.extras)

    implementation(Libraries.ThreeTenABP.core)
}