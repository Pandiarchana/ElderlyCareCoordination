plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.elderlycarecoordination"
    compileSdk = 34  // ✅ Make sure compileSdk is defined

    defaultConfig {
        applicationId = "com.example.elderlycarecoordination"
        minSdk = 21  // ✅ Define minSdk properly
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false  // ✅ Correct syntax in Kotlin DSL
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }
}
dependencies {
    implementation("androidx.core:core-ktx:1.12.0")

    // ✅ Ensure Material3 Dependency is Correct
    implementation("androidx.compose.material3:material3:1.2.1")
    implementation("androidx.compose.ui:ui:1.5.4")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.4")

    // ✅ Navigation for Compose
    implementation("androidx.navigation:navigation-compose:2.7.6")

    // ✅ Lifecycle for Compose
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

    // ✅ Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.5.4")

    // ✅ Debugging
    debugImplementation("androidx.compose.ui:ui-tooling:1.5.4")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.5.4")
}
