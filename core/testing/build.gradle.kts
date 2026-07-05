plugins {
    alias(libs.plugins.archmovies.android.library)
}

android {
    namespace = "iammert.com.androidarchitecture.core.testing"
}

dependencies {
    api(projects.core.data)
    api(libs.junit)
    api(libs.kotlinx.coroutines.test)
    api(libs.turbine)
    api(libs.androidx.paging.testing)
}
