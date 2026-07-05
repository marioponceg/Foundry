package io.github.marioponceg.foundry.catalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    // Placeholder entry point; the catalog structure arrives with the
                    // catalog-base design unit (PR #3 of the v0.1 roadmap).
                    Text(text = "Foundry Catalog")
                }
            }
        }
    }
}
