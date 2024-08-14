import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.parcelize)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.codelytical.muvyz"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.codelytical.muvyz"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        ksp {
            arg("room.schemaLocation", "$rootDir/app/schemas/")
        }
    }

    val localProperties = gradleLocalProperties(rootDir, providers)
    val apiKey: String = localProperties.getProperty("API_KEY") ?: ""
    val baseUrl: String = localProperties.getProperty("BASE_URL") ?: ""
    val imageUrl: String = localProperties.getProperty("IMAGE_BASE_UR") ?: ""

    buildTypes {
        all {
            buildConfigField("int", "PATCH_VERSION_CODE", "1")
            buildConfigField("String", "IMAGE_BASE_UR", "\"$imageUrl\"")
        }

        getByName("debug") {
            versionNameSuffix = " - debug-1"
            applicationIdSuffix = ".debug"
            buildConfigField("String", "API_KEY", "\"$apiKey\"")
            buildConfigField("String", "BASE_URL", "\"$baseUrl\"")
        }

        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "API_KEY", "\"$apiKey\"")
            buildConfigField("String", "BASE_URL", "\"$baseUrl\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    sourceSets {
        getByName("androidTest").assets.srcDirs(files("$rootDir/app/schemas")) // Room
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

composeCompiler {
    enableStrongSkippingMode = true
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.ui)
    implementation(libs.material3)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    implementation(libs.appcompat)

    // Room
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    // Compose dependencies
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.accompanist.flowlayout)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // Coroutine Lifecycle Scopes
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    //Dagger - Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    ksp(libs.androidx.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    // Timber
    implementation(libs.jakewharton.timber)

    // RamCosta Navigation
    implementation(libs.compose.destination.core)
    ksp(libs.compose.destination.ksp)

    // Navigation animation
    implementation(libs.accompanist.navigation.animation)

    // Accompanist System UI controller
    implementation(libs.accompanist.systemuicontroller)

    // Insets
    implementation(libs.accompanist.insets)

    // Coil
    implementation(libs.coil.compose)

    // Timber
    implementation(libs.jakewharton.timber)

    // Paging Compose
    implementation(libs.androidx.paging.compose)

    // Swipe to refresh
    implementation(libs.accompanist.swiperefresh)

    // DataStore
    implementation(libs.datastore.preferences)
}