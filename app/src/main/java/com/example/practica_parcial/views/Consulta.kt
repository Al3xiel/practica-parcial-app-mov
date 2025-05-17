package com.example.practica_parcial.views

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.practica_parcial.model.beans.Post
import com.example.practica_parcial.viewmodel.PostViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun Consulta(nav: NavHostController, viewModel: PostViewModel, context: Context) {

    var id by remember { mutableStateOf(viewModel.post.id)}
    var userId by remember { mutableStateOf(viewModel.post.userId)}
    var title by remember { mutableStateOf(viewModel.post.title)}
    var body by remember { mutableStateOf(viewModel.post.body)}

    var txtId by remember { mutableStateOf("")}

    Column(modifier= Modifier
        .fillMaxSize()
        .padding(20.dp)
        .padding(vertical = 30.dp)) {

        Text(
            text = "CONSULTA POST",
            fontSize = 32.sp,
            color = Color.Blue,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        OutlinedTextField(
            value = txtId,
            modifier = Modifier.padding(7.dp)
                .padding(vertical = 15.dp)
                .fillMaxWidth(),
            label = { Text(text = "Insert ID") },
            placeholder = { Text("Insert User") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    tint = Color.Gray,
                    contentDescription = ""
                )
            },
            singleLine = true,
            keyboardOptions =  KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = {
                txtId = it
            }
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            onClick = {
                viewModel.viewModelScope.launch(Dispatchers.IO) {
                    viewModel.getPost(txtId.toInt())
                    id=viewModel.post.id
                    userId=viewModel.post.userId
                    title=viewModel.post.title
                    body=viewModel.post.body
                }
            },
        ) {
            Text("Consulta de Post")
        }

        Text(text = "Id: ${id}")
        Text(text = "UserId:${userId}")
        Text(text = "Title: ${title}")
        Text(text = "Body: ${body}")

        Button (
            onClick = {
                val post= Post(
                    id,
                    userId,
                    title,
                    body
                )

                nav.navigate("P2")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(text = "Registrar Post")
        }
    }
}
