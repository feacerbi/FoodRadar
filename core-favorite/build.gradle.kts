@file:Suppress("UnstableApiUsage")

plugins {
    id("java-library")
    alias(libs.plugins.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(libs.kotlin.stdLib)
    implementation(libs.coroutines.core)
}