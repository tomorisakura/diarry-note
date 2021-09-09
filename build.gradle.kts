import java.net.URI

// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(ProjectDependencies.gradle)
        classpath(ProjectDependencies.kotlin_gradle)
        classpath(ProjectDependencies.navigation_safe_args)
        classpath(ProjectDependencies.hilt)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven {
            url = URI("https://jitpack.io")
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}