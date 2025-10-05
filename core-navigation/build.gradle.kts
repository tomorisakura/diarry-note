plugins {
    id("com.android.library")
    id("kotlin-android")
    id("androidx.navigation.safeargs")
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
}
android {
    namespace = "com.ekik.core_navigation"
    compileSdk = 36

    defaultConfig {
        minSdk = 27
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(project(mapOf("path" to ":feature-splash")))
    implementation(project(mapOf("path" to ":feature-intro")))
    implementation(project(mapOf("path" to ":feature-home")))
    implementation(project(mapOf("path" to ":feature-task")))
    core()
    layout()
    fragmentKtx()
    navigation()
    hilt()
}