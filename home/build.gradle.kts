plugins {
    id(BuildPlugins.androidDynamicFeature)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinKapt)
    id(BuildPlugins.kotlinAndroidExtensions)
}

android {
    compileSdkVersion(AndroidVersions.compileSdk)

    buildFeatures.dataBinding = true

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
    implementation(project(Modules.cvApi))

    implementation(Libraries.Kotlin.core)

    implementation(Libraries.Compat.appCompat)
    implementation(Libraries.Compat.coreKtx)
    implementation(Libraries.ConstraintLayout.core)
    implementation(Libraries.MaterialDesign.core)

    implementation(Libraries.NavigationComponents.fragmentKtx)
    implementation(Libraries.NavigationComponents.uiKtx)
    implementation(Libraries.NavigationComponents.dynamicFeaturesFragment)

    implementation(Libraries.LifecycleComponents.extensions)
    implementation(Libraries.LifecycleComponents.runtime)
    implementation(Libraries.LifecycleComponents.viewModel)

    implementation(Libraries.Dagger.core)
    kapt(Libraries.Dagger.compiler)

    implementation(Libraries.Gson.core)
    implementation(Libraries.Gson.extras)

    implementation(Libraries.ThreeTenABP.core)

    implementation(Libraries.CircleImageView.core)
}