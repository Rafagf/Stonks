plugins {
    id("com.android.application")
    kotlin("android")
}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${properties["version.kotlinx.coroutines"]}")
    implementation("androidx.compose.compiler:compiler:1.0.0-beta08")
    implementation("androidx.compose.ui:ui:1.0.1")
    implementation("androidx.compose.ui:ui-text:1.0.1")
    implementation("androidx.compose.foundation:foundation:1.0.1")
    implementation("androidx.compose.runtime:runtime-livedata:1.0.1")
    implementation("androidx.compose.material:material:1.0.1")
    implementation("androidx.activity:activity-compose:1.3.1")
    implementation("androidx.compose.ui:ui-tooling:1.0.1") {
        version {
            strictly("1.0.0-beta09")
        }
    }
}

android {
    compileSdkVersion(30)
    defaultConfig {
        applicationId = "com.rafag.stonks.android"
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.0-beta08"
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}
