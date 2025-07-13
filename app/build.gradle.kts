plugins {
    // Android application plugin
    alias(libs.plugins.android.application)

    // Kotlin Android support
    alias(libs.plugins.kotlin.android)

    // Kotlin Annotation Processor (for LiveData & ViewModel)
    id("kotlin-kapt")
}

android {
    namespace = "com.example.quizora"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.quizora"
        minSdk = 27
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        // Test runner for instrumented tests
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            // Disables code shrinking for release build
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        // Enables DataBinding
        dataBinding = true
    }
}

dependencies {
    // AndroidX core and UI libraries
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // Unit and UI testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Retrofit and Gson for networking and JSON parsing
    implementation("com.squareup.retrofit2:retrofit:3.0.0")
    implementation("com.squareup.retrofit2:converter-gson:3.0.0")

    // Kotlin Coroutines for async tasks
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Lifecycle components (ViewModel & LiveData)
    val lifecycle_version = "2.9.1"
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")

    // Required for annotation processing (LiveData/ViewModel)
    kapt("androidx.lifecycle:lifecycle-compiler:$lifecycle_version")
}