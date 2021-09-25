pluginManagement {
    repositories {
        google()
        jcenter()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "stonks"
include(":androidApp")
include(":shared")
include(":androidApp:design")
include(":androidApp:navigation")
