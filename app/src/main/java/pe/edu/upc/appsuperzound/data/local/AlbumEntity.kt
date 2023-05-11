package pe.edu.upc.appsuperzound.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Album")
class AlbumEntity (
    @PrimaryKey
    val id: String
)