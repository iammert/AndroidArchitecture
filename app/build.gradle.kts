plugins {
    alias(libs.plugins.archmovies.android.application)
    alias(libs.plugins.archmovies.android.compose)
    alias(libs.plugins.archmovies.hilt)
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
    implementation(projects.core.designsystem)
    implementation(projects.feature.movies)
    implementation(projects.feature.detail)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    testImplementation(libs.junit)
}
