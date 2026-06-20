plugins {
    alias(libs.plugins.archmovies.android.library)
    alias(libs.plugins.archmovies.hilt)
}

android {
    namespace = "iammert.com.androidarchitecture.core.data"
}

dependencies {
    api(projects.core.model)
    implementation(projects.core.database)
    implementation(projects.core.network)
    implementation(libs.kotlinx.coroutines.android)
}
