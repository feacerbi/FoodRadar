@file:Suppress("UnstableApiUsage")

plugins {
    id("java-library")
    alias(libs.plugins.kotlin.jvm)
    kotlin("kapt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(projects.coreLocation)
    implementation(libs.kotlin.stdLib)
    implementation(libs.coroutines.core)
    implementation(libs.javax.inject)
    implementation(libs.hilt.core)
    kapt(libs.hilt.compiler)
    testImplementation(projects.coreTest)
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.coroutines.test)
}