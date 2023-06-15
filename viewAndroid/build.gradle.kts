plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "dev.subfly.rickmmorty.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "dev.subfly.rickmmorty.android"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        signingConfig = signingConfigs.getByName("debug")
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":core"))
    implementation(libs.bundles.compose)
    implementation(libs.bundles.accompanist)
    implementation(libs.bundles.koin.android)
    implementation(libs.coil)
    implementation(libs.splash)
}