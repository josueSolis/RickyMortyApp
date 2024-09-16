plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.apollo)
    alias(libs.plugins.mockmp)
}

kotlin {
    androidTarget {
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
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            //put your multiplatform dependencies here
            implementation(libs.apollographql.apollo)
            implementation(libs.koin)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.apollo.mock)
        }
        androidMain.dependencies {
            implementation(libs.paging)
            implementation(libs.paging.compose)
        }
    }
    apollo {
        service("service") {
            packageName.set("com.omni.pro.graphql")
        }
    }
    mockmp {
        usesHelper = true
        installWorkaround()
    }
}

android {
    namespace = "com.omni.pro.characters"
    compileSdk = 34
    defaultConfig {
        minSdk = 25
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
