package pe.edu.upc.appsuperzound.data.remote

import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Path

interface AlbumService {
    @GET("")
    fun fetchByName(@Path("strArtist") strArtist: String): Call<AlbumResponse>

    @GET("mostloved.php?format=album")
    fun fetchAll():Call<AlbumResponse>
}