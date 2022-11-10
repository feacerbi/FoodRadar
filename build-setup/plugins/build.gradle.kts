plugins {
    `kotlin-dsl`
}

group = "com.felipeacerbi.buildsetup"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
}