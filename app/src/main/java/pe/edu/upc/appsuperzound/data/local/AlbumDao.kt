package pe.edu.upc.appsuperzound.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AlbumDao {

    @Query("select * from Album where id=:id")
    fun fetchById(id: String): List<AlbumEntity>

    @Insert
    fun insert(albumEntity: AlbumEntity)

    @Delete
    fun delete(albumEntity: AlbumEntity)
}