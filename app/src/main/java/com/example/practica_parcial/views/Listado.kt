package com.example.practica_parcial.views

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.practica_parcial.model.beans.Post
import com.example.practica_parcial.viewmodel.PostViewModel

@Composable
fun Listado(rememberScreen: NavHostController, viewModel: PostViewModel, context: Context) {
    //la siguiemte linea pide importar set, get y mutable
    //ademas suprimir Line
    //var listaP: MutableList<Post> by mutableStateOf(arrayListOf())
    var listaP: MutableList<Post>

    viewModel.getPostList(context)
    listaP = viewModel.postList

    Column (
        modifier= Modifier
            .fillMaxSize()
            .padding(20.dp)
            .padding(vertical = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text ="LISTA DE POST",
            fontSize = 25.sp,
            color = Color.Blue,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center)

        LazyColumn (modifier = Modifier.fillMaxWidth()) {
            //pidio imporyar extension de lazyColumn items
            items(listaP) {

                Card(
                    onClick = { /*activateEditForm(user)*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .heightIn(min = 100.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(5.dp)
                    ) {
                        Icon(
                            Icons.Default.AccountCircle,
                            contentDescription = null,
                            Modifier.size(50.dp)
                        )
                        Column(
                            modifier = Modifier
                                .padding(horizontal = 5.dp)
                                .width(210.dp)
                        ) {
                            Text(text = "Id: ${it.id}")
                            Text(text = "UserId: ${it.userId}")
                            Text(text = "Title: ${it.title}")
                            Text(text = "Body: ${it.body}")
                        }
                        IconButton(
                            onClick = {
                            },
                        ) {
                            Icon(Icons.Default.Delete,
                                contentDescription = null)
                        }
                        IconButton(
                            onClick = {
                                Log.i("posicion --->",it.toString())
                            },
                        ) {
                            Icon(Icons.Default.Edit, contentDescription = null)
                        }
                    }
                }

            }
        }
    }
}