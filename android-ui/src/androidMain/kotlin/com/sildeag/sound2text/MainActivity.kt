package com.sildeag.sound2text

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloAndroidApp()
        }
    }
}

@Composable
fun HelloAndroidApp() {
    Surface(color = MaterialTheme.colorScheme.background) {
        Text(text = "Android, says hello!")
    }
}

@Preview(showBackground = true)
@Composable
fun HelloAndroidPreview() {
    HelloAndroidApp()
}
