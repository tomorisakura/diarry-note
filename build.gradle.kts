plugins {
    id("com.google.devtools.ksp") version "2.0.21-1.0.27" apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(ProjectDependencies.gradle)
        classpath(ProjectDependencies.kotlin_gradle)
        classpath(ProjectDependencies.navigation_safe_args)
        classpath(ProjectDependencies.hilt)
        classpath(ProjectDependencies.kotlinGradlePlugin)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://jitpack.io") }
    }
}

tasks.register("clean") {
    delete(rootProject.buildDir)
}