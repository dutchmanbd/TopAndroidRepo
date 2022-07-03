import org.gradle.api.Project
import org.gradle.kotlin.dsl.*

fun Project.setupCommonDependencies() {
    dependencies {
        val implementation by configurations
        val testImplementation by configurations
        val androidTestImplementation by configurations
        val debugImplementation by configurations
        val kaptAndroidTest by configurations

        // Local Unit Test
        implementation(AndroidX.test.coreKtx)
        testImplementation(AndroidX.test.ext.junit)
        testImplementation(AndroidX.archCore.testing)
        testImplementation(KotlinX.coroutines.test)
        testImplementation(DepUtils.junit)
        testImplementation(DepUtils.robolectric)
        testImplementation(DepUtils.mockito)
        testImplementation(DepUtils.mockitoInline)
        testImplementation(DepUtils.truth)
        testImplementation(Square.okHttp3.mockWebServer)


        // Instrumented Unit Test
        androidTestImplementation(AndroidX.test.ext.junit)
        androidTestImplementation(AndroidX.test.runner)
        androidTestImplementation(AndroidX.test.espresso.core)
        androidTestImplementation(AndroidX.test.rules)
        androidTestImplementation(AndroidX.archCore.testing)

        androidTestImplementation(KotlinX.coroutines.test)

        androidTestImplementation(DepUtils.junit)
        androidTestImplementation(DepUtils.robolectric)
        androidTestImplementation(DepUtils.mockito)
        androidTestImplementation(DepUtils.mockitoInline)
        androidTestImplementation(DepUtils.truth)

        androidTestImplementation(Google.dagger.hilt.android.testing)
        kaptAndroidTest(Google.dagger.hilt.compiler)


        debugImplementation(AndroidX.fragment.testing)

        implementation(
            fileTree(
                "dir" to "libs",
                "include" to listOf("*.jar")
            )
        )

    }
}