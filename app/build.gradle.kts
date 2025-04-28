plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.compose)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.tmdbapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.tmdbapp"
        minSdk = 30
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":core:common:network"))
    implementation(project(":core:common:nav"))
    implementation(project(":core:common:model"))
    implementation(project(":core:common:ui"))
    implementation(project(":feature:home"))
    implementation(project(":feature:profile"))

    //voyager
    implementation(libs.voyager.navigator.tab)

    implementation("tech.thdev:flow-call-adapter-factory:1.0.1")

    //hilt
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.material3)
}

kapt {
    correctErrorTypes = true
}