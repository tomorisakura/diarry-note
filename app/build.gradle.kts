plugins {
    id("com.android.application")
    id("kotlin-android")
    id("androidx.navigation.safeargs")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
}

android {
    namespace = "com.grevi.diarry"
    compileSdk = 34
    buildToolsVersion = "34.0.0"

    buildFeatures {
        viewBinding = true
    }

    defaultConfig {
        applicationId = "com.grevi.diarry"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(mapOf("path" to ":core-ui")))
    implementation(project(mapOf("path" to ":core-common")))
    implementation(project(mapOf("path" to ":core-navigation")))
    implementation(project(mapOf("path" to ":feature-splash")))
    core()
    layout()
    fragmentKtx()
    navigation()
    unitTest()
    hilt()
    coroutine()
}