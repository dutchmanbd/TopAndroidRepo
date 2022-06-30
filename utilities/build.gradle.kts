plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    configCommon()

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    testOptions {
        unitTests.isIncludeAndroidResources = true
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

}

dependencies {

    implementation(Google.Android.material)
    implementation(AndroidX.appCompat)
    implementation(AndroidX.core.ktx)
    implementation(AndroidX.activity.ktx)
    implementation(AndroidX.lifecycle.liveDataKtx)

    implementation(Square.okHttp3.okHttp)
    implementation(Square.retrofit2.retrofit)
    implementation(Square.retrofit2.converter.gson)
}