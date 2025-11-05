package od.app.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val race: String,
    val clazz: String,
    val str: Int,
    val dex: Int,
    val con: Int,
    val intel: Int,
    val wis: Int,
    val cha: Int,
    val atk: Int,
    val hp: Int
)
