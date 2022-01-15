plugins {
    id("com.android.library")
}

android {
    compileSdk = Config.compileSdk
    buildToolsVersion = Config.buildTools

    defaultConfig {
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
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