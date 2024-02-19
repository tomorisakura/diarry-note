import org.gradle.api.artifacts.dsl.DependencyHandler

private fun DependencyHandler.implementation(depName: String) {
    add("implementation", depName)
}

private fun DependencyHandler.kapt(depName: String) {
    add("kapt", depName)
}

private fun DependencyHandler.test(depName: String) {
    add("testImplementation", depName)
}

private fun DependencyHandler.androidTest(depName: String) {
    add("androidTestImplementation", depName)
}

fun DependencyHandler.hilt() {
    implementation(AppDependencies.dagger_hilt)
    kapt(AppDependencies.dagger_hilt_android_compiler)
}

fun DependencyHandler.room() {
    implementation(AppDependencies.room_runtime)
    kapt(AppDependencies.room_compiler)
    implementation(AppDependencies.room_ktx)
}

fun DependencyHandler.layout() {
    implementation(AppDependencies.constraint_layout)
    implementation(AppDependencies.material_guideline)
    implementation(AppDependencies.awesome_dialog)
}

fun DependencyHandler.core() {
    implementation(AppDependencies.kotlin_version)
    implementation(AppDependencies.appcompat)
    implementation(AppDependencies.appcompatResource)
    implementation(AppDependencies.kotlin_core_ktx)
    implementation(AppDependencies.legacy_support)
    implementation(AppDependencies.permission_x)
}

fun DependencyHandler.navigation() {
    implementation(AppDependencies.navigation_fragment_ktx)
    implementation(AppDependencies.navigation_ui_ktx)
}

fun DependencyHandler.fragmentKtx() {
    implementation(AppDependencies.fragment_ktx)
}

fun DependencyHandler.coroutine() {
    implementation(AppDependencies.coroutine)
}

fun DependencyHandler.viewpager() {
    implementation(AppDependencies.viewpager2)
    implementation(AppDependencies.viewpager_indicator)
}

fun DependencyHandler.unitTest() {
    test(AppDependencies.junit)
    androidTest(AppDependencies.junit_extension)
    androidTest(AppDependencies.espresso)
}