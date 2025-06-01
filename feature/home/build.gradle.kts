plugins {
    id("com.android.library")
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.compose)
    id("kotlin-kapt")
}

android {
    namespace = "com.example.tmdbapp.home"
    compileSdk = 35

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
    implementation(project(":core:common:model"))
    implementation(project(":core:common:network"))
    implementation(project(":core:common:ui"))
    implementation(project(":core:common:nav"))

    implementation(libs.voyager.hilt)
    implementation(libs.voyager.navigator)
    implementation(libs.voyager.navigator.tab)
    implementation(libs.voyager.navigator.transitions)

    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    //hilt
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)
    implementation (libs.hilt.nav)

    implementation(libs.androidx.core.ktx)
    implementation(libs.android.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}