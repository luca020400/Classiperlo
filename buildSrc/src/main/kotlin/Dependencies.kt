object Kotlin {
    const val kotlinVersion = "1.3.61"
}

object BuildPlugins {
    private object Versions {
        const val buildToolsVersion = "3.5.1"
        const val safeArgsVersion = "2.1.0"
        const val gmsVersion = "4.3.2"
        const val fabricVersion = "1.31.2"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.buildToolsVersion}"
    const val kotlinGradlePlugin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.kotlinVersion}"
    const val safeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.safeArgsVersion}"
    const val gms = "com.google.gms:google-services:${Versions.gmsVersion}"
    const val fabric = "io.fabric.tools:gradle:${Versions.fabricVersion}"
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
    const val kotlinSafeArgs = "androidx.navigation.safeargs.kotlin"
    const val gmsPlugin = "com.google.gms.google-services"
    const val fabricPlugin = "io.fabric"
}

object AndroidSdk {
    const val min = 16
    const val compile = 29
    const val target = compile
}

object Libraries {
    private object Versions {
        const val appcompat = "1.1.0"
        const val constraintLayout = "2.0.0-beta3"
        const val ktx = "1.1.0"
        const val lifecycle = "2.2.0-rc02"
        const val material = "1.0.0"
        const val navigation = "2.2.0-rc02"
        const val jsoup = "1.12.1"
        const val firebaseAnalyticsVersion = "17.2.0"
        const val firebaseMessagingVersion = "20.1.0"
        const val fabricCrashlyticsVersion = "2.10.1"
    }

    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Kotlin.kotlinVersion}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val ktxCore = "androidx.core:core-ktx:${Versions.ktx}"
    const val lifecycle = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val navigationFragmentKtx =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val jsoup = "org.jsoup:jsoup:${Versions.jsoup}"
    const val firebaseAnalytics = "com.google.firebase:firebase-analytics:${Versions.firebaseAnalyticsVersion}"
    const val firebaseMessaging = "com.google.firebase:firebase-messaging:${Versions.firebaseMessagingVersion}"
    const val fabricCrashlytics = "com.crashlytics.sdk.android:crashlytics:${Versions.fabricCrashlyticsVersion}"
}
