@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.library")
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    kotlin("kapt")
}

android {
    namespace = "com.felipeacerbi.foodradar.data_favorite"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
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
    kapt {
        correctErrorTypes = true
    }
}

hilt {
    enableAggregatingTask = true
}

dependencies {
    implementation(projects.coreFavorite)
    implementation(libs.kotlin.stdLib)
    implementation(libs.coroutines.core)
    implementation(libs.hilt.android)
    implementation(libs.room)
    implementation(libs.room.ktx)
    kapt(libs.hilt.compiler)
    ksp(libs.room.compiler)
    testImplementation(projects.coreTest)
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.coroutines.test)
}