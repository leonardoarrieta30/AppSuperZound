package pe.edu.upc.appsuperzound.data.remote

import com.google.gson.annotations.SerializedName
import pe.edu.upc.appsuperzound.data.model.Album

data class AlbumResponse (
//    @SerializedName("loved")
    val loved: List<Album>
)