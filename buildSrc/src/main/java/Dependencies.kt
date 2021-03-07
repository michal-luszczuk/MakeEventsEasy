import org.gradle.api.JavaVersion

object Config {
    const val minSdk = 19
    const val compileSdk = 30
    const val targetSdk = 30
    const val buildTools = "29.0.2"
    val javaVersion = JavaVersion.VERSION_1_8
}


object Versions {
    const val junit = "4.13.2"
    const val mockk = "1.9.3"
    const val kotlinVersion = "1.4.31"
    const val firebaseAnalytics = "18.0.2"
    const val robolectric = "4.5.1"
    const val kluent = "1.65"
    const val flurryAnalytics = "12.11.0"
    const val mixpanelAnalytics = "5.8.6"
    const val dokka = "0.10.1"
    const val dagger = "2.33"
}

object Libs {
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinVersion}"
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