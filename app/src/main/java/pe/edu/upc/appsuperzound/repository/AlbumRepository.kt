package pe.edu.upc.appsuperzound.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import pe.edu.upc.appsuperzound.data.local.AlbumDao
import pe.edu.upc.appsuperzound.data.local.AlbumEntity
import pe.edu.upc.appsuperzound.data.model.Album
import pe.edu.upc.appsuperzound.data.remote.AlbumResponse
import pe.edu.upc.appsuperzound.data.remote.AlbumService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumRepository(
    private val albumService: AlbumService,
    private val albumDao: AlbumDao
) {
    private val _albums = MutableLiveData<List<Album>>(listOf())
    val albums get() = _albums

    fun fetchByName(name: String){
        val fetchByName = albumService.fetchByName(name)
        fetchByName.enqueue(object : Callback<AlbumResponse>{
            override fun onResponse(
                call: Call<AlbumResponse>,
                response: Response<AlbumResponse>
            ) {
                if (response.isSuccessful) {
                    _albums.value = response.body()!!.loved
                    for (album in _albums.value!!){
                        album.favorite = albumDao.fetchById(album.id).isNotEmpty()
                    }
                }
            }

            override fun onFailure(call: Call<AlbumResponse>, t: Throwable) {
                Log.d("AlbumRepository", t.message.toString())
            }

        })

    }

    fun fetchAll(){
        val fetchByAll = albumService.fetchAll()
        fetchByAll.enqueue(object : Callback<AlbumResponse>{
            override fun onResponse(
                call: Call<AlbumResponse>,
                response: Response<AlbumResponse>
            ) {
                if (response.isSuccessful) {
                    _albums.value = response.body()!!.loved
                    for (album in _albums.value!!){
                        album.favorite = albumDao.fetchById(album.id).isNotEmpty()
                    }
                }
            }

            override fun onFailure(call: Call<AlbumResponse>, t: Throwable) {
                Log.d("AlbumRepository", t.message.toString())
            }

        })
    }

    fun insert(album: Album){
        val albumEntity = AlbumEntity(album.id)
        albumDao.insert(albumEntity)
        album.favorite=true
    }

    fun delete(album: Album){
        val albumEntity = AlbumEntity(album.id)
        albumDao.delete(albumEntity)
        album.favorite=false
    }

}