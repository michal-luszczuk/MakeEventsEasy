import org.gradle.api.JavaVersion

object Config {
    const val minSdk = 19
    const val compileSdk = 31
    const val targetSdk = 31
    const val buildTools = "31.0.0"
    val javaVersion = JavaVersion.VERSION_1_8
}


object Versions {
    const val junit = "4.13.2"
    const val mockk = "1.12.2"
    const val kotlinVersion = "1.6.10"
    const val firebaseAnalytics = "20.0.2"
    const val robolectric = "4.7.3"
    const val kluent = "1.68"
    const val flurryAnalytics = "13.1.0"
    const val mixpanelAnalytics = "6.0.0"
    const val dokka = "1.6.10"
    const val dagger = "2.40.5"
}

object Libs {
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"
    const val junit = "junit:junit:${Versions.junit}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"

    const val kluent = "org.amshove.kluent:kluent:${Versions.kluent}"
    const val kluentAndroid = "org.amshove.kluent:kluent-android:${Versions.kluent}"

    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"

    const val firebaseAnalytics = "com.google.firebase:firebase-analytics:${Versions.firebaseAnalytics}"
    const val flurryAnalytics = "com.flurry.android:analytics:${Versions.flurryAnalytics}"
    const val mixpanelAnalytics = "com.mixpanel.android:mixpanel-android:${Versions.mixpanelAnalytics}"
}