plugins {
    alias(libs.plugins.archmovies.android.feature)
}

android {
    namespace = "iammert.com.androidarchitecture.feature.movies"
}

dependencies {
    implementation(libs.androidx.paging.compose)
}
