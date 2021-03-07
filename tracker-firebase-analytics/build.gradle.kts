plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    buildToolsVersion(Config.buildTools)
    compileSdkVersion(Config.compileSdk)

    defaultConfig {
        minSdkVersion(Config.minSdk)
        targetSdkVersion(Config.targetSdk)
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"))
        }
    }

    compileOptions {
        sourceCompatibility(Config.javaVersion)
        targetCompatibility(Config.javaVersion)
    }
}

dependencies {
    implementation(Libs.kotlinStdLib)
    api(project(path = ":core"))
    api(Libs.firebaseAnalytics)

    testImplementation(Libs.junit)
    testImplementation(Libs.mockk)
    testImplementation(Libs.robolectric)
    testImplementation(Libs.kluent)
    testImplementation(Libs.kluentAndroid)
}