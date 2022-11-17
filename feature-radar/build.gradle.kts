@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.library")
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    kotlin("kapt")
}

android {
    namespace = "com.felipeacerbi.foodradar.feature_radar"
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get().toString()
    }
}

hilt {
    enableAggregatingTask = true
}

dependencies {
    implementation(projects.coreDesign)
    implementation(projects.coreFavorite)
    implementation(projects.coreLocation)
    implementation(projects.coreRestaurant)
    implementation(libs.kotlin.stdLib)
    implementation(libs.coroutines.core)
    implementation(libs.hilt.android)
    implementation(libs.lifecycle.runtimeCompose)
    implementation(libs.lifecycle.viewModel)
    implementation(libs.lifecycle.viewModelCompose)
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.toolingPreview)
    implementation(libs.compose.material)
    kapt(libs.hilt.compiler)
    debugImplementation(libs.compose.tooling)
    debugImplementation(libs.compose.manifest)
    testImplementation(projects.coreTest)
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.coroutines.test)
}