//import com.android.tools.r8.internal.kt

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    alias(libs.plugins.hilt.android)
   // id("com.google.dagger.hilt.android") version "2.51.1" apply false
/*
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")

 */
    kotlin("kapt")
}

android {
    namespace = "com.example.marvelheros"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.marvelheros"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//добавил ключи от marvel API
        buildConfigField("String", "MARVEL_PUBLIC_KEY", "\"${properties["MARVEL_PUBLIC_KEY"]}\"")
        buildConfigField("String", "MARVEL_PRIVATE_KEY", "\"${properties["MARVEL_PRIVATE_KEY"]}\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11 // по документации Hilt использует функции Java 8
        targetCompatibility = JavaVersion.VERSION_11 //
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
// default
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
// coil
    implementation(libs.coil.compose)
    implementation (libs.coil3.coil.compose)
    implementation(libs.coil.network.okhttp)
// ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")
//Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.1")
// retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-moshi:2.9.0")
//  Для проверки интернет-соединения
    implementation ("androidx.legacy:legacy-support-v4:1.0.0")

// Moshi (для преобразования JSON)
    implementation ("com.squareup.moshi:moshi-kotlin:1.14.0")
//Hilt
  //  implementation("com.google.dagger:hilt-android:2.51.1")
  //  kapt("com.google.dagger:hilt-android-compiler:2.51.1")


    kapt(libs.hilt.android)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

}

kapt {
  correctErrorTypes = true
}
