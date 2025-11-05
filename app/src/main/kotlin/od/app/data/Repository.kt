package od.app.data

import android.content.Context
import kotlinx.coroutines.flow.Flow
import od.app.db.AppDb
import od.app.db.CharacterEntity

class Repository(context: Context) {
    private val dao = AppDb.get(context).characterDao()

    fun first(): Flow<CharacterEntity?> = dao.first()
    suspend fun firstOnce(): CharacterEntity? = dao.firstOnce()
    suspend fun upsert(c: CharacterEntity): Long = dao.upsert(c)
    suspend fun nuke() = dao.nuke()
}
