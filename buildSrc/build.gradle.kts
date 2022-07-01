plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {

    implementation("de.fayard.refreshVersions:refreshVersions:0.40.2")
    implementation("com.android.tools.build:gradle:7.2.1")
    implementation(kotlin("gradle-plugin", "1.5.31"))

    implementation(gradleApi())
    implementation(localGroovy())
}
