import java.util.Properties

plugins {
    alias(libs.plugins.archmovies.android.library)
    alias(libs.plugins.archmovies.hilt)
    alias(libs.plugins.kotlin.serialization)
}

val localProperties = Properties().apply {
    rootProject.file("local.properties").takeIf { it.exists() }?.inputStream()?.use(::load)
}

android {
    namespace = "iammert.com.androidarchitecture.core.network"

    defaultConfig {
        buildConfigField("String", "TMDB_API_KEY", "\"${localProperties.getProperty("tmdb.apiKey", "")}\"")
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    api(libs.retrofit)
    implementation(libs.retrofit.converter.kotlinx.serialization)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp)
}
