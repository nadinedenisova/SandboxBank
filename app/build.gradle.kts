plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp") version "2.1.21-2.0.1"
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.example.sandboxbank"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.sandboxbank"
        minSdk = 21
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.6"
    }

}

dependencies {
    //Compose
    implementation(libs.compose.lifecycle)
    implementation(libs.compose)
    implementation(libs.activity.compose)
    implementation(libs.coil.compose)
    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.pager.indicators)
    implementation(libs.compose.navigation)
    debugImplementation(libs.compose.ui.tooling)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.converterGson)
    implementation(libs.gson)

    //Room
    implementation(libs.bundles.room)
    ksp(libs.room.compiler)

    //Coroutines
    implementation(libs.coroutines.android)

    //Dagger
    implementation(libs.dagger)
    ksp(libs.dagger.compiler)

    //Easy permission
    implementation(libs.easypermission)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}