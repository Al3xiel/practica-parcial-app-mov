package com.example.practica_parcial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.practica_parcial.ui.theme.PracticaparcialTheme
import com.example.practica_parcial.viewmodel.PostViewModel
import com.example.practica_parcial.views.nav.Navigation

class MainActivity : ComponentActivity() {

    val viewModel by viewModels<PostViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticaparcialTheme {
                Navigation(viewModel,this, this)
            }
        }
    }
}