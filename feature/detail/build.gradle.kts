plugins {
    alias(libs.plugins.archmovies.android.feature)
}

android {
    namespace = "iammert.com.androidarchitecture.feature.detail"

    testOptions {
        unitTests.isIncludeAndroidResources = true
    }
}

dependencies {
    testImplementation(libs.robolectric)
    testImplementation(libs.androidx.test.ext.junit)
    testImplementation(libs.androidx.navigation.testing)
}
