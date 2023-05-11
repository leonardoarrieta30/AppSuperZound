package pe.edu.upc.appsuperzound.ui.screens

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import pe.edu.upc.appsuperzound.data.local.AppDatabase
import pe.edu.upc.appsuperzound.data.model.Album
import pe.edu.upc.appsuperzound.data.remote.AlbumClient
import pe.edu.upc.appsuperzound.repository.AlbumRepository

class MusicalAlbumViewModel(application: Application) : AndroidViewModel(application){
    private val albumService = AlbumClient.albumService()
    private val albumDao = AppDatabase.getInstance(application).albumDao()
    private val albumRepository = AlbumRepository(albumService, albumDao)

    private var _albums = albumRepository.albums
    val albums get() = _albums

    private var _name = MutableLiveData<String>()
    val name get() = _name

    fun update(name : String){
        _name.value = name
    }

    fun fetchByName() {
        albumRepository.fetchByName(name.value!!)
        _albums.value = albumRepository.albums.value
    }

    fun fetchAll(){
        albumRepository.fetchAll()
        _albums.value = albumRepository.albums.value
    }

    //favorites
    fun insert(album: Album){
        albumRepository.insert(album)
    }

    fun delete(album: Album){
        albumRepository.delete(album)
    }
}