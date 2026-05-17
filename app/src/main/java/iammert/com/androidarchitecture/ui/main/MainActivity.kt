package iammert.com.androidarchitecture.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import iammert.com.androidarchitecture.ui.navigation.ArchMoviesNavHost
import iammert.com.androidarchitecture.ui.theme.ArchMoviesTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArchMoviesTheme {
                ArchMoviesNavHost()
            }
        }
    }
}
