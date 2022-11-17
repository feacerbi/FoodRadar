@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.application")
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    kotlin("kapt")
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
        val debug by getting {
            buildConfigField("String", "BASE_ENDPOINT_URL", "\"https://example.com/\"")
        }
        val release by getting {
            initWith(debug)
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
    implementation(projects.featureRadar)
    implementation(projects.coreNetwork)
    runtimeOnly(projects.dataFavorite)
    runtimeOnly(projects.dataLocation)
    runtimeOnly(projects.dataRestaurant)
    implementation(libs.androidx.coreKtx)
    implementation(libs.lifecycle.runtime)
    implementation(libs.androidx.activityCompose)
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.toolingPreview)
    implementation(libs.compose.material)
    implementation(libs.hilt.android)
    implementation(libs.lifecycle.runtimeCompose)
    kapt(libs.hilt.compiler)
    debugImplementation(libs.compose.tooling)
    debugImplementation(libs.compose.manifest)
}