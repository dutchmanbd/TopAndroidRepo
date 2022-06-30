import org.gradle.api.Project
import org.gradle.kotlin.dsl.*

fun Project.setupCommonDependencies() {
    dependencies {
        val implementation by configurations
        val testImplementation by configurations
        val androidTestImplementation by configurations

        testImplementation(AndroidX.test.ext.junitKtx)
        androidTestImplementation(AndroidX.test.ext.junitKtx)
        androidTestImplementation(AndroidX.test.runner)
        androidTestImplementation(AndroidX.test.espresso.core)
        androidTestImplementation(AndroidX.test.rules)

        implementation(
            fileTree(
                "dir" to "libs",
                "include" to listOf("*.jar")
            )
        )

    }
}