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
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.10")

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