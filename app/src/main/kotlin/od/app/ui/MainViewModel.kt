package od.app.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import od.app.data.Repository
import od.app.db.CharacterEntity
import kotlin.random.Random

enum class Mode(val base: Int) { WEAK(4), NORMAL(8), HARD(12) }

data class UiState(
    val character: CharacterEntity? = null,
    val fights: Int = 0,
    val wins: Int = 0,
    val running: Boolean = false,
    val mode: Mode = Mode.NORMAL,
    val last: String = ""
)

class MainViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = Repository(app)
    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    private var loopJob: Job? = null

    init {
        viewModelScope.launch {
            repo.first().collect { c ->
                _state.value = _state.value.copy(character = c)
            }
        }
    }

    fun createDefault() = viewModelScope.launch {
    val c = CharacterEntity(
        name = "Herói",
        race = "Humano",
        clazz = "Guerreiro",
        str = 10, dex = 10, con = 10, intel = 8, wis = 8, cha = 8,
        atk = 5, hp = 12
    )
    repo.upsert(c)
}

fun createCharacter(difficulty: String) = viewModelScope.launch {
    val stats = when (difficulty) {
        "Heroic" -> listOf(16, 14, 15, 12, 10, 13, 8, 20)
        "Adventurer" -> listOf(12, 12, 12, 10, 10, 10, 6, 15)
        "Survivor" -> listOf(8, 10, 14, 8, 12, 9, 4, 12)
        else -> listOf(10, 10, 10, 8, 8, 8, 5, 12)
    }
    
    val c = CharacterEntity(
        name = "Hero",
        race = "Human", 
        clazz = "Adventurer",
        str = stats[0], 
        dex = stats[1], 
        con = stats[2], 
        intel = stats[3], 
        wis = stats[4], 
        cha = stats[5],
        atk = stats[6], 
        hp = stats[7]
    )
    repo.upsert(c)
}

fun toggle(mode: Mode? = null) {
        val m = mode ?: _state.value.mode
        if (_state.value.running) {
            loopJob?.cancel()
            _state.value = _state.value.copy(running = false, mode = m)
        } else {
            _state.value = _state.value.copy(running = true, mode = m)
            loopJob = viewModelScope.launch { idleLoop() }
        }
    }

    private suspend fun idleLoop() {
        while (_state.value.running) {
            val c = _state.value.character ?: break
            val (updated, win, log) = fightOnce(c, _state.value.mode)
            if (win) repo.upsert(updated)
            _state.value = _state.value.copy(
                character = if (win) updated else c,
                fights = _state.value.fights + 1,
                wins = _state.value.wins + if (win) 1 else 0,
                last = log
            )
            delay(1000L)
        }
    }

    private fun fightOnce(p: CharacterEntity, mode: Mode): Triple<CharacterEntity, Boolean, String> {
        val pRoll = Random.nextInt(1, 7)
        val mRoll = Random.nextInt(1, 7)
        val playerPower = p.atk + p.str / 2 + pRoll
        val monsterPower = mode.base + mRoll
        val win = playerPower >= monsterPower
        val log = "P:{atk=%d,str=%d}+d6=%d vs M:{base=%d}+d6=%d => %s".format(
            p.atk, p.str, pRoll, mode.base, mRoll, if (win) "WIN" else "LOSE"
        )
        val updated = if (win) p.copy(atk = p.atk + 1, hp = p.hp + 1) else p
        return Triple(updated, win, log)
    }
}

