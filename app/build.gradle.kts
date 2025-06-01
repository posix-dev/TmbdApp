import java.util.Properties

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

        // Read from local.properties
        val localProperties = Properties().apply {
            val localPropertiesFile = rootProject.file("local.properties")
            if (localPropertiesFile.exists()) {
                load(localPropertiesFile.inputStream())
            }
        }

        // Set the BuildConfig field
        buildConfigField(
            "String",
            "API_KEY",
            "\"${localProperties.getProperty("API_KEY", "")}\""
        )
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            buildConfigField("String", "API_URL","\"https://api.themoviedb.org/3/\"")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "API_URL","\"https://api.themoviedb.org/3/\"")
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
        buildConfig = true
    }
}

dependencies {
    implementation(project(":core:common:network"))
    implementation(project(":core:common:nav"))
    implementation(project(":core:common:model"))
    implementation(project(":core:common:ui"))
    implementation(project(":feature:home"))
    implementation(project(":feature:profile"))
    implementation(project(":feature:detail_movie"))
    implementation(project(":feature:detail_actor"))
    implementation(project(":feature:all_movies"))

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