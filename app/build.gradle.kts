import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.hilt)
    id("kotlin-kapt") // TODO: Replace kapt with KSP when Hilt supports it (https://kotlinlang.org/docs/ksp-overview.html#supported-libraries)
}

android {
    namespace = "com.example.nasaexplorer"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.nasaexplorer"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        // Load API Key value
        buildConfigField("String", "NASA_API_KEY", "\"${getApiKey("NASA_API_KEY")}\"")


        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
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
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

fun getApiKey(key: String): String {
    val properties = Properties()
    val localProperties = project.rootProject.file("apikeys.properties")
    if (localProperties.exists()) {
        localProperties.inputStream().use { input ->
            properties.load(input)
            return properties.getProperty(key)
        }
    } else {
        return ""
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.timber)
    implementation(libs.moshi)
    implementation(libs.moshiConverter)
    implementation(libs.coroutines)
    implementation(libs.retrofit)
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation)
    implementation(libs.coil)
    kapt(libs.hilt.compiler)
    kapt(libs.androidx.hilt.compiler)
    implementation(libs.androidx.navigation.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}