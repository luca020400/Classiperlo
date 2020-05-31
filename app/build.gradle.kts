plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
    id(BuildPlugins.kotlinSafeArgs)
    id(BuildPlugins.gmsPlugin)
    id(BuildPlugins.firebasePlugin)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

android {
    compileSdkVersion(AndroidSdk.compile)

    defaultConfig {
        applicationId = "com.luca020400.classiperlo"
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)
        versionCode = 14
        versionName = "2.0"
        multiDexEnabled = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    // Multidex
    implementation(Libraries.multidex)

    // Kotlin
    implementation(Libraries.kotlinStdLib)

    // AndroidX
    implementation(Libraries.appCompat)
    implementation(Libraries.constraintLayout)
    implementation(Libraries.ktxCore)
    implementation(Libraries.lifecycle)
    implementation(Libraries.liveData)
    implementation(Libraries.preferenceKtx)

    // Material
    implementation(Libraries.material)

    // Navigation
    implementation(Libraries.navigationFragmentKtx)
    implementation(Libraries.navigationUiKtx)

    // jsoup
    implementation(Libraries.jsoup)

    // GMS
    implementation(Libraries.firebaseAnalytics)
    implementation(Libraries.firebaseCrashlytics)
    implementation(Libraries.firebaseMessaging)

    // OkHttp
    implementation(Libraries.okHttp)

    implementation("com.heinrichreimersoftware:material-intro:2.0.0")
}
