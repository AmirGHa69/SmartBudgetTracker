pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven(url = "https://jitpack.io") // ✅ Added for MPAndroidChart
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io") // ✅ Added for MPAndroidChart
    }
}

rootProject.name = "SmartBudgetTracker"
include(":app")
