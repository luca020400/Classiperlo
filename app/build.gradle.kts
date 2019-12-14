plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
    id(BuildPlugins.kotlinSafeArgs)
    id(BuildPlugins.gmsPlugin)
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
        versionCode = 1
        versionName = "1.0"
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
    // Kotlin
    implementation(Libraries.kotlinStdLib)

    // AndroidX
    implementation(Libraries.appCompat)
    implementation(Libraries.constraintLayout)
    implementation(Libraries.ktxCore)
    implementation(Libraries.lifecycle)
    implementation(Libraries.liveData)

    // Material
    implementation(Libraries.material)

    // Navigation
    implementation(Libraries.navigationFragmentKtx)
    implementation(Libraries.navigationUiKtx)

    // jsoup
    implementation(Libraries.jsoup)

    // GMS
    implementation(Libraries.firebaseAnalytics)
}
