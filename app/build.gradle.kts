import java.util.Properties

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

        buildConfigField(
            "String",
            "MARVEL_PUBLIC_KEY",
            "\"${localProperties.getProperty("MARVEL_PUBLIC_KEY")}\""
        )
        buildConfigField(
            "String",
            "MARVEL_PRIVATE_KEY",
            "\"${localProperties.getProperty("MARVEL_PRIVATE_KEY")}\""
        )
    }

    buildFeatures { buildConfig = true }

    buildTypes {
        release {
            isShrinkResources = true
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    applicationVariants.all {
        outputs.all {
            (this as com.android.build.gradle.internal.api.BaseVariantOutputImpl).outputFileName =
                if (buildType.name == "release") "Marvel Heroes.apk"
                else "my-app-debug.apk"
        }
    }

    compileOptions {
        sourceCompatibility =
            JavaVersion.VERSION_11 // по документации Hilt использует функции Java 8
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
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
// my connect
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.moshi)
    implementation(libs.moshi.kotlin)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.moshi.kotlin.codegen)
    ksp(libs.room.compiler)
// coil
    implementation(libs.coil.compose)
    implementation(libs.coil3.coil.compose)
    implementation(libs.coil.network.okhttp)

    kapt(libs.hilt.android)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)
}

kapt {
    correctErrorTypes = true
}
