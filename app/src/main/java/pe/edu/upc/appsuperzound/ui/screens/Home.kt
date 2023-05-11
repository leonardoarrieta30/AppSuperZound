package pe.edu.upc.appsuperzound.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import pe.edu.upc.appsuperzound.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onFindAlbums: () -> Unit, onFavouriteAlbum: () -> Unit, function: () -> Unit) {
    Scaffold(
        bottomBar = {  }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.icon),
                contentDescription = "Branding Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.5f)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onFindAlbums,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Albums")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onFavouriteAlbum,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Favourite Album")
            }
        }
    }
}



