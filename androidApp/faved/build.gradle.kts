plugins {
    id("com.android.library")
    kotlin("android")
}

dependencies {
    implementation(project(":shared"))
    implementation(project(":androidApp:design"))
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.compose.compiler:compiler:1.1.0-alpha04")
    implementation("androidx.compose.ui:ui:1.0.2")
    implementation("androidx.compose.ui:ui-text:1.0.2")
    implementation("androidx.compose.foundation:foundation:1.0.2")
    implementation("androidx.compose.material:material:1.0.2")
    implementation("androidx.activity:activity-compose:1.3.1")
    implementation("androidx.compose.ui:ui-tooling:1.0.1") {
        version {
            strictly("1.0.0-beta09")
        }
    }
}

android {
    compileSdkVersion(31)
    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(31)
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
        kotlinCompilerExtensionVersion = "1.1.0-alpha05"
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}
