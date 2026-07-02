plugins {
    alias(libs.plugins.archmovies.android.library)
    alias(libs.plugins.archmovies.hilt)
}

android {
    namespace = "iammert.com.androidarchitecture.core.database"
}

dependencies {
    implementation(projects.core.model)

    api(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    api(libs.androidx.room.paging)
    ksp(libs.androidx.room.compiler)
}
