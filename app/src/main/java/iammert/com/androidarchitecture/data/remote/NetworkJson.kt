package iammert.com.androidarchitecture.data.remote

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Converter
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object NetworkJson {

    private val json = Json { ignoreUnknownKeys = true }

    @JvmStatic
    fun converterFactory(): Converter.Factory = json.asConverterFactory("application/json".toMediaType())
}
