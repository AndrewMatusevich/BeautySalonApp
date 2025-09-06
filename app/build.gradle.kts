plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.gms)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.beautysalonapp"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.example.beautysalonapp"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()

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
        sourceCompatibility = JavaVersion.VERSION_19
        targetCompatibility = JavaVersion.VERSION_19
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_19.toString()
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    // --- Modules ---
    implementation(project(":data_api"))
    implementation(project(":data_impl"))
    implementation(project(":domain_models"))
    implementation(project(":domain_api"))
    implementation(project(":domain_impl"))
    implementation(project(":core:ui"))
    implementation(project(":core:navigation"))
    implementation(project(":core:firebase"))

    // --- Feature Modules ---
    implementation(project(":features:auth_api"))
    implementation(project(":features:auth_impl"))
    implementation(project(":features:booking_api"))
    implementation(project(":features:booking_impl"))
    implementation(project(":features:calendar_api"))
    implementation(project(":features:calendar_impl"))
    implementation(project(":features:client_api"))
    implementation(project(":features:client_impl"))
    implementation(project(":features:master_api"))
    implementation(project(":features:master_impl"))
    implementation(project(":features:admin_api"))
    implementation(project(":features:admin_impl"))
    implementation(project(":features:profile_api"))
    implementation(project(":features:profile_impl"))


    // --- Firebase ---
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth.ktx)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.storage)
    implementation(libs.firebase.functions)
    implementation(libs.firebase.messaging)
    implementation(libs.firebase.analytics)

    // --- AndroidX Core & Lifecycle ---
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // --- Jetpack Compose ---
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // --- Navigation ---
    implementation(libs.navigation.compose)
    implementation(libs.androidx.navigation.runtime.ktx)

    // --- Dagger ---
    implementation(libs.dagger)
    ksp(libs.dagger.compiler)

    // --- Unit Testing ---
    testImplementation(libs.junit)

    // --- Android Instrumented Testing ---
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    // --- Debugging ---
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}