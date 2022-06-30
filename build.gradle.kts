buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()

        maven("https://jitpack.io")
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.2.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")

        classpath(Google.dagger.hilt.android.gradlePlugin)
        classpath(AndroidX.navigation.safeArgsGradlePlugin)

    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

task("clean") {
    delete(rootProject.buildDir)
}