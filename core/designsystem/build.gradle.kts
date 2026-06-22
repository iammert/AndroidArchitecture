plugins {
    alias(libs.plugins.archmovies.android.library)
    alias(libs.plugins.archmovies.android.compose)
}

android {
    namespace = "iammert.com.androidarchitecture.core.designsystem"
}

dependencies {
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.material.icons.extended)
}
