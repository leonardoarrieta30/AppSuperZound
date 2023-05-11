package pe.edu.upc.appsuperzound.data.model

import com.google.gson.annotations.SerializedName

data class Album(

    val id: String,
    @SerializedName("strAlbum")
    val strAlbum: String,
    @SerializedName("strArtist")
    val strArtist: String,
    @SerializedName("strAlbumThumb")
    val strAlbumThumb: String,
    @SerializedName("intYearReleased")
    val intYearReleased: String,

    var favorite: Boolean
)
