package od.app.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    @Upsert
    suspend fun upsert(c: CharacterEntity): Long

    @Query("SELECT * FROM characters ORDER BY id LIMIT 1")
    fun first(): Flow<CharacterEntity?>

    @Query("SELECT * FROM characters ORDER BY id LIMIT 1")
    suspend fun firstOnce(): CharacterEntity?

    @Query("DELETE FROM characters")
    suspend fun nuke()
}
