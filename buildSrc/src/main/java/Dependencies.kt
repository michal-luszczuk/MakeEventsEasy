import org.gradle.api.JavaVersion

object Config {
    const val minSdk = 19
    const val compileSdk = 29
    const val targetSdk = 29
    const val buildTools = "29.0.2"
    val javaVersion = JavaVersion.VERSION_1_8
}


object Versions {
    const val junit = "4.13"
    const val mockk = "1.9.3"
    const val kotlinVersion = "1.3.61"
    const val firebaseAnalytics = "17.2.2"
    const val robolectric = "4.3.1"
    const val kluent = "1.60"
    const val flurryAnalytics = "12.1.0"
    const val mixpanelAnalytics = "5.7.0"
    const val dokka = "0.10.1"
}

object Libs {
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinVersion}"
    const val junit = "junit:junit:${Versions.junit}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"

    const val kluent = "org.amshove.kluent:kluent:${Versions.kluent}"
    const val kluentAndroid = "org.amshove.kluent:kluent-android:${Versions.kluent}"

    const val firebaseAnalytics = "com.google.firebase:firebase-analytics:${Versions.firebaseAnalytics}"
    const val flurryAnalytics = "com.flurry.android:analytics:${Versions.flurryAnalytics}"
    const val mixpanelAnalytics = "com.mixpanel.android:mixpanel-android:${Versions.mixpanelAnalytics}"
}