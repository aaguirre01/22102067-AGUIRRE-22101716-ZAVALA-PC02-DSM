package dev.aaguirre.pc2_dsm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dev.aaguirre.pc2_dsm.ui.navigation.AppNavigation
import dev.aaguirre.pc2_dsm.ui.theme.PC2DSMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PC2DSMTheme {
                AppNavigation()
            }
        }
    }
}
