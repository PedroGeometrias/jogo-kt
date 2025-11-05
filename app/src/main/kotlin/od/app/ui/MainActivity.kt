package od.app.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

class MainActivity : ComponentActivity() {
    private val vm: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(Modifier.fillMaxSize()) {
                    val state by vm.state.collectAsStateWithLifecycle()
                    
                    // Auto-create character if none exists
                    LaunchedEffect(state.character) {
                        if (state.character == null) {
                            vm.createDefault()
                        }
                    }
                    
                    Screen(state, onCreate = vm::createDefault, onToggle = vm::toggle)
                }
            }
        }
    }
}

@Composable
private fun Screen(
    state: UiState,
    onCreate: () -> Unit,
    onToggle: (Mode?) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text("OD Idle", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)

        val c = state.character
        if (c == null) {
            Text("No character found.")
            Button(onClick = onCreate) { Text("Create default") }
            return@Column
        }

        Text("Name: ${c.name}")
        Text("Race: ${c.race}  Class: ${c.clazz}")
        Text("ATK: ${c.atk}   HP: ${c.hp}")
        Text("STR: ${c.str} DEX: ${c.dex} CON: ${c.con}")
        Text("INT: ${c.intel} WIS: ${c.wis} CHA: ${c.cha}")

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            ModeChip("Weak", state.mode == Mode.WEAK) { onToggle(Mode.WEAK) }
            ModeChip("Normal", state.mode == Mode.NORMAL) { onToggle(Mode.NORMAL) }
            ModeChip("Hard", state.mode == Mode.HARD) { onToggle(Mode.HARD) }
        }

        Button(onClick = { onToggle(null) }) {
            Text(if (state.running) "Stop" else "Start")
        }

        Divider()
        Text("Fights: ${state.fights}  Wins: ${state.wins}")
        if (state.last.isNotBlank()) Text(state.last)
    }
}

@Composable
private fun ModeChip(text: String, selected: Boolean, onClick: () -> Unit) {
    AssistChip(
        onClick = onClick,
        label = { Text(text) },
        leadingIcon = { if (selected) Text("✓") }
    )
}


    
