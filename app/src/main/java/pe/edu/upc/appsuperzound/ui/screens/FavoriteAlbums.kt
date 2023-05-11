package pe.edu.upc.appsuperzound.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.map
import coil.compose.AsyncImage
import pe.edu.upc.appsuperzound.data.model.Album

@Composable
fun AlbumsFavorite(viewModel: MusicalAlbumViewModel){
    Column {
        AlbumFavorite(viewModel = viewModel)
    }
}

@Composable
fun AlbumFavorite(viewModel: MusicalAlbumViewModel,){
    val albums by remember(viewModel.albums) {
        viewModel.albums.map { albums ->
            albums.filter { it.favorite }
        }.distinctUntilChanged()
    }.observeAsState(emptyList())

    LazyColumn {
        items(albums) { album ->
            FavoriteCard(
                album,
                insertProduct = {
                    viewModel.insert(album)
                },
                deleteProduct = {
                    viewModel.delete(album)
                }
            )
        }
    }
}

@Composable
fun FavoriteCard(
    album: Album,
    modifier: Modifier = Modifier,
    insertProduct: () -> Unit,
    deleteProduct: () -> Unit
){
    val isFavorite = remember {
        mutableStateOf(false)
    }

    isFavorite.value=album.favorite

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
    ){
        Row {
            AlbumFavoriteImage(album)
            AlbumFavoriteItem(album, insertProduct, deleteProduct, modifier, isFavorite)
        }
    }
}

@Composable
fun AlbumFavoriteItem(
    album: Album,
    insertProduct: () -> Unit,
    deleteProduct: () -> Unit,
    modifier: Modifier = Modifier,
    isFavorite: MutableState<Boolean>
){

    Spacer(modifier = modifier.width(8.dp))
    Row {
        Column(modifier = modifier.weight(7f)) {
            Text(text = album.strAlbum, fontWeight = FontWeight.Bold)
        }
        IconButton(
            modifier = modifier.weight(1f),
            onClick = {
                if (isFavorite.value){
                    deleteProduct()
                }else{
                    insertProduct()
                }
                isFavorite.value=!isFavorite.value

            }) {
            Icon(
                Icons.Filled.Favorite,
                contentDescription = null,
                tint = if(isFavorite.value) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
fun AlbumFavoriteImage(album: Album, modifier: Modifier = Modifier){
    AsyncImage(
        model = album.strAlbumThumb,
        contentDescription = null,
        modifier = modifier
            .size(92.dp)
            .clip(shape = RoundedCornerShape(8.dp)),
        contentScale = ContentScale.Crop
    )
}
