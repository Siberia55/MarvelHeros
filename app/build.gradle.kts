import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.android.build.gradle.internal.utils.KSP_PLUGIN_ID
import com.google.wireless.android.sdk.stats.AnnotationProcessorInfo.InclusionType.KSP
import org.jetbrains.kotlin.konan.properties.Properties

import com.android.tools.r8.internal.kt
//import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    alias(libs.plugins.hilt.android)

    kotlin("kapt")
    id("com.google.devtools.ksp")
}

val localProperties = Properties().apply {
    load(rootProject.file("local.properties").inputStream())
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

        buildConfigField("String", "MARVEL_PUBLIC_KEY", "\"${localProperties.getProperty("MARVEL_PUBLIC_KEY")}\"")
        buildConfigField("String", "MARVEL_PRIVATE_KEY", "\"${localProperties.getProperty("MARVEL_PRIVATE_KEY")}\"")
    }

    buildFeatures {buildConfig = true}

    buildTypes {
        release {
            isMinifyEnabled = true
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
        freeCompilerArgs += "-Xlint:deprecation"
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
// Edge-to-Edge
    implementation("androidx.activity:activity-ktx:1.10.1")
// implementation ("androidx.compose.material:material-icons-extended:2.51.1")
    implementation ("androidx.compose.material:material-icons-extended:1.7.8")
// ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")
// Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.1")
// retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-moshi:2.9.0")
//  Для проверки интернет-соединения
    implementation ("androidx.legacy:legacy-support-v4:1.0.0")

// Moshi (для преобразования JSON)
    implementation ("com.squareup.moshi:moshi:1.14.0")
    implementation ("com.squareup.moshi:moshi-kotlin:1.14.0")
    implementation ("com.squareup.retrofit2:converter-moshi:2.9.0")
// Hilt
   implementation("com.google.dagger:hilt-android:2.51.1")
   kapt("com.google.dagger:hilt-android-compiler:2.51.1")
// для логирования
    implementation ("com.squareup.okhttp3:logging-interceptor:4.10.0")
// Room
    implementation ("androidx.room:room-runtime:2.7.0")
    implementation ("androidx.room:room-ktx:2.7.0")
    kapt ("androidx.room:room-compiler:2.7.0")
//--------- переход на ksp
// ksp("androidx.room:room-compiler:2.5.0")

    kapt(libs.hilt.android)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)
}

kapt {
  correctErrorTypes = true
}
