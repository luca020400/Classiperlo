object Kotlin {
    const val kotlinVersion = "1.3.50"
}

object BuildPlugins {
    private object Versions {
        const val buildToolsVersion = "3.5.1"
        const val r8Version = "1.6.41"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.buildToolsVersion}"
    const val kotlinGradlePlugin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.kotlinVersion}"
    const val r8GradlePlugin = "com.android.tools:r8:${Versions.r8Version}"
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
}

object AndroidSdk {
    const val min = 15
    const val compile = 29
    const val target = compile
}

object Libraries {
    private object Versions {
        const val appcompat = "1.1.0"
        const val constraintLayout = "2.0.0-beta2"
        const val ktx = "1.1.0"
        const val lifecycle = "2.1.0"
        const val vectorDrawable = "1.1.0"
        const val material = "1.0.0"
        const val navigation = "2.1.0"
    }

    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Kotlin.kotlinVersion}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val ktxCore = "androidx.core:core-ktx:${Versions.ktx}"
    const val lifecycle = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val vectorDrawable = "androidx.vectordrawable:vectordrawable:${Versions.vectorDrawable}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val navigationFragmentKtx =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
}
