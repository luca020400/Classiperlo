object Kotlin {
    const val kotlinVersion = "1.3.71"
}

object BuildPlugins {
    private object Versions {
        const val buildToolsVersion = "4.0.0"
        const val safeArgsVersion = "2.2.0"
        const val gmsVersion = "4.3.3"
        const val firebaseVersion = "2.1.1"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.buildToolsVersion}"
    const val kotlinGradlePlugin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.kotlinVersion}"
    const val safeArgs =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.safeArgsVersion}"
    const val gms = "com.google.gms:google-services:${Versions.gmsVersion}"
    const val firebase =
        "com.google.firebase:firebase-crashlytics-gradle:${Versions.firebaseVersion}"
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
    const val kotlinSafeArgs = "androidx.navigation.safeargs.kotlin"
    const val gmsPlugin = "com.google.gms.google-services"
    const val firebasePlugin = "com.google.firebase.crashlytics"
}

object AndroidSdk {
    const val min = 16
    const val compile = 29
    const val target = compile
}

object Libraries {
    private object Versions {
        const val appcompat = "1.1.0"
        const val constraintLayout = "2.0.0-beta6"
        const val ktx = "1.3.0"
        const val lifecycle = "2.2.0"
        const val material = "1.1.0"
        const val navigation = "2.2.2"
        const val preference = "1.1.1"
        const val jsoup = "1.13.1"
        const val firebaseAnalytics = "17.4.2"
        const val firebaseCrashlytics = "17.0.0"
        const val firebaseMessaging = "20.2.0"
        const val okHttp = "4.7.2"
        const val multidex = "2.0.1"
    }

    const val multidex = "androidx.multidex:multidex:${Versions.multidex}"
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
    const val preferenceKtx = "androidx.preference:preference-ktx:${Versions.preference}"
    const val jsoup = "org.jsoup:jsoup:${Versions.jsoup}"
    const val firebaseAnalytics =
        "com.google.firebase:firebase-analytics:${Versions.firebaseAnalytics}"
    const val firebaseCrashlytics =
        "com.google.firebase:firebase-crashlytics:${Versions.firebaseCrashlytics}"
    const val firebaseMessaging =
        "com.google.firebase:firebase-messaging:${Versions.firebaseMessaging}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
}
