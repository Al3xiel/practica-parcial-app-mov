package com.example.practica_parcial.views

import android.content.Context
import android.content.SharedPreferences
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.practica_parcial.MainActivity
import com.example.practica_parcial.model.beans.Post
import com.example.practica_parcial.viewmodel.PostViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun Consulta(nav: NavHostController, viewModel: PostViewModel, context: Context, mainActivity: MainActivity) {

    var id by remember { mutableStateOf(viewModel.post.id)}
    var userId by remember { mutableStateOf(viewModel.post.userId)}
    var title by remember { mutableStateOf(viewModel.post.title)}
    var body by remember { mutableStateOf(viewModel.post.body)}

    val pref: SharedPreferences? = mainActivity.getSharedPreferences("preferences", Context.MODE_PRIVATE)

    val lastUserId = pref?.getString("lastUserId", null) ?: "Insert User"

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
            placeholder = { Text(lastUserId) },
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
                    val editor: SharedPreferences.Editor = pref!!.edit()
                    editor.putString("lastUserId", txtId)
                    editor.apply()
                    editor.commit()
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
//                if(existsInList(id, viewModel, context) || id == 0)
//                {
//                    nav.navigate("S2")
//                }else{
//                    val post= Post(
//                        id,
//                        userId,
//                        title,
//                        body
//                    )
//                    viewModel.insertPost(context,post)
//                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(text = "Registrar Post")
        }
    }
}

fun existsInList(id: Int, viewModel: PostViewModel, context: Context): Boolean {
    var listaP: MutableList<Post>
    listaP = viewModel.postList

    for (i in listaP) {
        if (i.id == id) {
            return true
        }
    }

    return false
}