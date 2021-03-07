plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    compileSdkVersion(Config.compileSdk)
    buildToolsVersion(Config.buildTools)

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

}

dependencies {
    implementation(Libs.kotlinStdLib)

    testImplementation(Libs.mockk)
    testImplementation(Libs.junit)
}