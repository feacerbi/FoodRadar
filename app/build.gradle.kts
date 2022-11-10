@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.felipeacerbi.foodradar"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.felipeacerbi.foodradar"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
}

dependencies {

    implementation(libs.androidx.coreKtx)
    implementation(libs.lifecycle.runtime)
    implementation(libs.androidx.activityCompose)
    implementation(libs.compose.ui)
    implementation(libs.compose.toolingPreview)
    implementation(libs.compose.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junitExt)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.compose.junit)
    debugImplementation(libs.compose.tooling)
    debugImplementation(libs.compose.manifest)
}