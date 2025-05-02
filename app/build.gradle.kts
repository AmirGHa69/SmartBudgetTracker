// app level
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.example.smartbudgettracker"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.smartbudgettracker"
        minSdk = 21
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.13"
    }
}

kotlin {
    jvmToolchain(17)
}

kapt {
    arguments {
        arg("room.schemaLocation", "$projectDir/schemas")
        arg("room.incremental", "true")
        arg("room.expandProjection", "true")
        arg("room.skipVerifySchema", "true") // ✅ disables Room's use of native SQLite
        arg("room.verifySchemaLocation", "false") // 💥 disables native verification

    }
}


dependencies {
    // Compose BOM
    implementation(platform(libs.androidx.compose.bom))

    // Jetpack Compose Core UI
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.tooling.preview)
    implementation("androidx.compose.material:material") // not in toml
    implementation("androidx.compose.material3:material3:1.2.1")
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")



    // Navigation
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // Lifecycle
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")

    // Activity
    implementation(libs.androidx.activity.compose)

    // Room Database
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
}

