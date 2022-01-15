plugins {
    id("com.android.library")
}

android {
    buildToolsVersion = Config.buildTools
    compileSdk = Config.compileSdk

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

    compileOptions {
        sourceCompatibility(Config.javaVersion)
        targetCompatibility(Config.javaVersion)
    }
}

dependencies {
    implementation(Libs.kotlinStdLib)
    api(project(path = ":core"))
    api(Libs.flurryAnalytics)


    testImplementation(Libs.junit)
    testImplementation(Libs.mockk)
    testImplementation(Libs.robolectric)
    testImplementation(Libs.kluent)
    testImplementation(Libs.kluentAndroid)
}