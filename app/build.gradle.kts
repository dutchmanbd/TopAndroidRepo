plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-kapt")

    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
}

android {
    configCommon()

    defaultConfig {
        applicationId = "com.test.topandroidrepo"
        versionCode = 1
        versionName = "1.0.0_alpha01"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        ndk {
            abiFilters.addAll(arrayOf("armeabi-v7a", "arm64-v8a", "x86", "x86_64"))
        }
        resourceConfigurations.addAll(arrayOf("en"))

        val baseUrl = findProperty("BASE_URL") as String

        buildConfigField("String", "BASE_URL", baseUrl)

    }

    buildTypes {
        release {
            ndk {
                debugSymbolLevel = "FULL"
            }
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            applicationVariants.all {
                outputs.all {
                    if (name.contains("release"))
                        (this as com.android.build.gradle.internal.api.BaseVariantOutputImpl).outputFileName =
                            "${rootProject.name}_${defaultConfig.versionName}.apk"
                }
            }
        }
        debug {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            applicationVariants.all {
                outputs.all {
                    if (name.contains("debug"))
                        (this as com.android.build.gradle.internal.api.BaseVariantOutputImpl).outputFileName =
                            "${rootProject.name}_${defaultConfig.versionName}.apk"
                }
            }

        }
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

setupCommonDependencies()

dependencies {

    implementation(project(mapOf("path" to ":extensions")))
    implementation(project(mapOf("path" to ":utilities")))

    useNavigation()
    useRoom()
    useHilt()

    implementation(Google.Android.material)

    implementation(AndroidX.appCompat)
    implementation(AndroidX.core.ktx)
    implementation(AndroidX.constraintLayout)

    implementation(AndroidX.lifecycle.runtimeKtx)
    implementation(AndroidX.lifecycle.liveDataKtx)
    implementation(AndroidX.lifecycle.viewModelKtx)
    implementation(AndroidX.lifecycle.commonJava8)
    implementation(AndroidX.work.runtime)

    implementation(AndroidX.fragment.ktx)
    implementation(AndroidX.activity.ktx)

    implementation(Square.okHttp3.okHttp)
    implementation(Square.okHttp3.loggingInterceptor)
    implementation(Square.retrofit2.retrofit)
    implementation(Square.retrofit2.adapter.rxJava2)
    implementation(Square.retrofit2.converter.gson)
    implementation(Square.retrofit2.converter.scalars)
    implementation(Square.okio)

    implementation(DepUtils.coil)
    implementation(DepUtils.coilBase)
    implementation(DepUtils.shimmer)
    implementation(DepUtils.sdpAndroid)

}