plugins {
    alias(libs.plugins.archmovies.android.application)
    alias(libs.plugins.archmovies.android.compose)
    alias(libs.plugins.archmovies.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "iammert.com.androidarchitecture"

    defaultConfig {
        applicationId = "iammert.com.androidarchitecture"
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(projects.core.data)

    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    implementation(libs.androidx.hilt.lifecycle.viewmodel.compose)


    testImplementation(libs.junit)
}
