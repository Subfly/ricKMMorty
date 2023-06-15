import app.cash.sqldelight.gradle.kotlin.linkSqlite
import com.android.build.gradle.internal.ide.kmp.KotlinAndroidSourceSetMarker.Companion.android

plugins {
    kotlin("multiplatform")
    alias(libs.plugins.android.library)
    alias(libs.plugins.graphQL)
    alias(libs.plugins.sqlDelight)
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "core"
        }
    }

    sourceSets {

        val androidMain by getting {
            dependencies {
                implementation(libs.koin.android)
                implementation(libs.sqlDelight.android.driver)
                implementation(libs.compose.material)
            }
        }
        val androidUnitTest by getting

        val commonMain by getting {
            dependencies {
                implementation(libs.graphQL)
                implementation(libs.sqlDelight.runtime)
                implementation(libs.sqlDelight.coroutines)
                implementation(libs.bundles.koin.shared)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by getting {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation(libs.sqlDelight.native.driver)
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by getting {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }

    }
}

android {
    namespace = "dev.subfly.rickmmorty"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
        signingConfig = signingConfigs.getByName("debug")
    }
}

apollo {
    service("rickAndMortyApi") {
        packageName.set("dev.subfly.rickmmorty")
        introspection {
            endpointUrl.set("https://rickandmortyapi.com/graphql")
            schemaFile.set(file("src/commonMain/graphql/schema.graphqls"))
            codegenModels.set("operationBased")
            generateKotlinModels.set(true)
        }
    }
}

sqldelight {
    databases {
        create("ricKMMortyDB") {
            packageName.set("dev.subfly.rickmmorty")
        }
    }
    linkSqlite()
}
