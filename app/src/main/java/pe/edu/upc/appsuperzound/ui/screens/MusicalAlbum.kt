package pe.edu.upc.appsuperzound.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import pe.edu.upc.appsuperzound.data.model.Album
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment

import pe.edu.upc.appsuperzound.data.remote.AlbumClient
import pe.edu.upc.appsuperzound.data.remote.AlbumResponse

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun Albums(viewModel: MusicalAlbumViewModel, modifier: Modifier = Modifier){
    val albums = remember {
        mutableStateListOf<Album>()
    }

    val albumService = AlbumClient.albumService()

    val fetchAlbum = albumService.fetchAll()

    fetchAlbum.enqueue(object : Callback<AlbumResponse>{
        override fun onResponse(call: Call<AlbumResponse>, response: Response<AlbumResponse>) {
            if (response.isSuccessful) {
                albums.addAll(response.body()!!.loved)
            }
        }

        override fun onFailure(call: Call<AlbumResponse>, t: Throwable) {
            TODO("Not yet implemented")
        }

    })

    LazyColumn(modifier = modifier) {
        items(albums) {
            MusicalCard1(viewModel, it)
        }
    }
}

@Composable
fun  MusicalCard1(viewModel: MusicalAlbumViewModel, album: Album, modifier: Modifier = Modifier){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            MusicalImage1(album)
            Spacer(modifier = modifier.width(4.dp))
            MusicalItem1(viewModel,album)
        }
    }
}



@Composable
fun MusicalItem1(viewModel: MusicalAlbumViewModel, album: Album, modifier: Modifier = Modifier){
    val isFavorite = remember {
        mutableStateOf(false)
    }
    Spacer(modifier = modifier.width(8.dp))


    Row {
        Column(modifier = modifier.weight(7f)) {
            Text(text = album.strAlbum)
            Text(text = album.strArtist)
            Text(text = album.intYearReleased)
        }
        IconButton(
            modifier = modifier.weight(1f),
            onClick = {
                if (isFavorite.value) {
                    viewModel.delete(album)
                } else {
                    viewModel.insert(album)
                }
                isFavorite.value = !isFavorite.value

            }) {
            Icon(
                Icons.Filled.Favorite,
                contentDescription = null,
                tint = if (isFavorite.value) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
fun MusicalImage1(album: Album, modifier: Modifier = Modifier){
    AsyncImage(
        model = album.strAlbumThumb,
        contentDescription = null,
        modifier = modifier
            .size(92.dp)
            .clip(shape = RoundedCornerShape(8.dp)),
        contentScale = ContentScale.Crop
    )
}


