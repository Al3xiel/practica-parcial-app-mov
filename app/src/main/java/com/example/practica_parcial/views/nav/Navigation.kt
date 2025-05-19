package com.example.practica_parcial.views.nav

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.practica_parcial.MainActivity
import com.example.practica_parcial.viewmodel.PostViewModel
import com.example.practica_parcial.views.Consulta
import com.example.practica_parcial.views.Listado

@Composable
fun Navigation(viewModel: PostViewModel, context: Context, mainActivity: MainActivity){
    var rememberScreen = rememberNavController()
    NavHost(navController = rememberScreen,
        startDestination = "S1"){
        composable("S1") { Consulta(rememberScreen, viewModel, context, mainActivity) }
        composable("S2") { Listado(rememberScreen, viewModel, context) }
    }
}