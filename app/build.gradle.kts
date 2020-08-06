import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.android.build.gradle.internal.dsl.DefaultConfig

plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinKapt)
    id(BuildPlugins.kotlinAndroidExtensions)
    id(BuildPlugins.junit5)
    id(BuildPlugins.navigationSafeArgs)
}

android {
    compileSdkVersion(AndroidVersions.compileSdk)
    buildToolsVersion(AndroidVersions.buildTools)

    buildFeatures.dataBinding = true

    defaultConfig {
        applicationId = ApplicationConfig.id
        minSdkVersion(AndroidVersions.minSdk)
        targetSdkVersion(AndroidVersions.targetSdk)
        versionCode = ReleaseVersions.versionCode
        versionName = ReleaseVersions.versionName

        vectorDrawables.useSupportLibrary = true

        configureFlavorsConfig(ExtVariables.allConfig)
    }

    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8

        tasks.withType<KotlinCompile> {
            kotlinOptions.jvmTarget = "1.8"
        }
    }

    dynamicFeatures = mutableSetOf(
        Modules.cvApi, ":cvModels"
    )

    testOptions {
        unitTests.apply {
            isReturnDefaultValues = true
        }
    }

    signingConfigs {
        getByName(BuildTypes.debug) {
            storeFile = file("default_key.jks")
            storePassword = "testkeypassword"
            keyAlias = "testkeynotforrelease"
            keyPassword = "spyrosofttestkey"
        }
        create(BuildTypes.release) {
            storeFile = file("default_key.jks")
            storePassword = "testkeypassword"
            keyAlias = "testkeynotforrelease"
            keyPassword = "spyrosofttestkey"
        }
    }

    buildTypes {
        getByName(BuildTypes.debug)

        getByName(BuildTypes.release) {
            isMinifyEnabled = true
            isShrinkResources = false

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(Libraries.Kotlin.core)

    implementation(Libraries.Dagger.core)
    kapt(Libraries.Dagger.compiler)

    implementation(Libraries.Coroutines.core)
    implementation(Libraries.Coroutines.android)

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

    implementation(Libraries.OkHttp.core)
    implementation(Libraries.OkHttp.logger)
    implementation(Libraries.Retrofit.core)
    implementation(Libraries.Retrofit.converter)

    implementation(Libraries.Gson.core)

    implementation(Libraries.Timber.core)

    implementation(Libraries.Glide.core)
    kapt(Libraries.Glide.compiler)

    api(Libraries.Glide.core)

    debugImplementation(Libraries.LeakCanary.core)

    testImplementation(Libraries.JUnit.core)
    testImplementation(Libraries.JUnit.engine)
    testImplementation(Libraries.Kotlin.reflect)
    testImplementation(Libraries.Coroutines.test)
    testImplementation(Libraries.Mockk.core)
}

fun DefaultConfig.configureFlavorsConfig(variableName: String) {
    // Add config properties as build configs
    val configMap = rootProject.ext.get(variableName) as java.util.Hashtable<String, String>
    for ((key: String, value: String) in configMap) {
        if (value.startsWith("\"")) {
            buildConfigField("String", key, value)
        } else {
            buildConfigField("Integer", key, value)
        }
    }
}