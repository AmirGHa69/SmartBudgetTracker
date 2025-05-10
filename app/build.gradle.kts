plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.smartbudgettracker"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.smartbudgettracker"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.13"
    }

    kotlinOptions {
        jvmTarget = "17"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    // Core libraries (✅ Downgraded)
    implementation("androidx.core:core:1.12.0")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.2")

    // Jetpack Compose BOM
    implementation(platform("androidx.compose:compose-bom:2024.09.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    // Room
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")

    // MPAndroidChart
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")

    // Tests
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.0")
    debugImplementation("androidx.compose.ui:ui-test-junit4:1.5.13")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Jetpack Compose
    implementation("androidx.compose.ui:ui:1.5.13")
    implementation("androidx.compose.material:material:1.5.13")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.13")
    implementation("androidx.activity:activity-compose:1.7.2")

// Navigation Compose
    implementation("androidx.navigation:navigation-compose:2.7.7")

// ViewModel Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

// Optional for preview/debug
    debugImplementation("androidx.compose.ui:ui-tooling:1.5.13")

}
