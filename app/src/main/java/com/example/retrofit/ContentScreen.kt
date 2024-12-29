package com.example.retrofit

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun ContentScreen(viewModel: MainViewModel) {
    val image = viewModel.image.collectAsState().value

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (viewModel.isLoading.value) {
            CircularProgressIndicator()
        } else {
            image?.let {
                Image(
                    painter = rememberAsyncImagePainter(it),
                    contentDescription = "Fetched Image",
                    modifier = Modifier.size(300.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }

        Spacer(modifier = Modifier.padding(25.dp))

        Button(onClick = { viewModel.fetchImage("DOG") }) {
            Text("New Dog")

        }

        Spacer(modifier = Modifier.padding(25.dp))

        Button(onClick = { viewModel.fetchImage("CAT") }) {
            Text("New Cat")

        }
    }
}

