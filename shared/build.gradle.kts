import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version "1.5.0"
    id("com.android.library")
    id("com.squareup.sqldelight")
}

sqldelight {
    database("StonksDatabase") {
        packageName = "com.stonks.db"
    }
}

kotlin {
    android()

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
            ::iosArm64
        else
            ::iosX64

    iosTarget("ios") {
        binaries {
            framework {
                baseName = "shared"
            }
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                //Network
                implementation("io.ktor:ktor-client-core:${properties["version.ktor"]}")
                implementation ("io.ktor:ktor-client-serialization:${properties["version.ktor"]}")
                implementation("io.ktor:ktor-client-logging:${properties["version.ktor"]}")
                //Coroutines
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${properties["version.kotlinx.coroutines"]}")
                //JSON
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${properties["version.kotlinx.serialization"]}")
                //SQL Delight
                implementation("com.squareup.sqldelight:runtime:1.5.1")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                //Network
                implementation("io.ktor:ktor-client-okhttp:${properties["version.ktor"]}")
                implementation("com.squareup.sqldelight:android-driver:1.5.1")

            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
                implementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
                implementation("org.mockito:mockito-inline:3.11.2")
            }
        }
        val iosMain by getting {
            dependencies {
                //Network
                implementation("io.ktor:ktor-client-ios:${properties["version.ktor"]}")
                implementation("com.squareup.sqldelight:native-driver:1.5.1")
            }
        }
        val iosTest by getting
    }
}

android {
    compileSdkVersion(30)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(30)
    }
}

val packForXcode by tasks.creating(Sync::class) {
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val framework = kotlin.targets.getByName<KotlinNativeTarget>("ios").binaries.getFramework(mode)
    val targetDir = File(buildDir, "xcode-frameworks")

    group = "build"
    dependsOn(framework.linkTask)
    inputs.property("mode", mode)

    from({ framework.outputDirectory })
    into(targetDir)
}

tasks.getByName("build").dependsOn(packForXcode)
