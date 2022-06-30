import org.gradle.kotlin.dsl.DependencyHandlerScope


fun DependencyHandlerScope.implementation(dependencyNotation: Any) {
    add("implementation", dependencyNotation)
}

fun DependencyHandlerScope.kapt(dependencyNotation: Any) {
    add("kapt", dependencyNotation)
}

fun DependencyHandlerScope.useNavigation() {
    implementation(AndroidX.navigation.commonKtx)
    implementation(AndroidX.navigation.fragmentKtx)
    implementation(AndroidX.navigation.uiKtx)
    implementation(AndroidX.navigation.runtimeKtx)
}

fun DependencyHandlerScope.useHilt() {
    implementation(Google.dagger.hilt.android)
    kapt(Google.dagger.hilt.compiler)
    implementation(AndroidX.hilt.navigationFragment)
    implementation(AndroidX.hilt.work)
    kapt(AndroidX.hilt.compiler)
}

fun DependencyHandlerScope.useRoom() {
    implementation(AndroidX.room.runtime)
    kapt(AndroidX.room.compiler)
    implementation(AndroidX.room.ktx)
}

