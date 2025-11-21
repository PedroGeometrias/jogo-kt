package od.app.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import od.app.data.Repository
import od.app.ui.MainActivity

class BattleService : Service() {
    private val serviceScope = CoroutineScope(Dispatchers.IO + Job())
    private var battleJob: Job? = null
    
    companion object {
        const val CHANNEL_ID = "battle_service_channel"
        const val NOTIFICATION_ID = 1
        const val ACTION_START_BATTLE = "START_BATTLE"
        const val ACTION_STOP_BATTLE = "STOP_BATTLE"
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_START_BATTLE -> startBattle()
            ACTION_STOP_BATTLE -> stopBattle()
        }
        return START_STICKY
    }

    private fun startBattle() {
        if (battleJob?.isActive == true) return
        
        val notification = createNotification("Batalha em andamento...")
        startForeground(NOTIFICATION_ID, notification)
        
        battleJob = serviceScope.launch {
            var battleCount = 0
            while (true) {
                battleCount++
                val notification = createNotification("Batalha #$battleCount rodando em background")
                val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.notify(NOTIFICATION_ID, notification)
                delay(5000L) // Update every 5 seconds
            }
        }
    }

    private fun stopBattle() {
        battleJob?.cancel()
        stopForeground(true)
        stopSelf()
    }

    private fun createNotification(content: String): Notification {
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("OD Idle - Batalha")
            .setContentText(content)
            .setSmallIcon(android.R.drawable.ic_media_play)
            .setContentIntent(
                android.app.PendingIntent.getActivity(
                    this,
                    0,
                    Intent(this, MainActivity::class.java),
                    android.app.PendingIntent.FLAG_IMMUTABLE or android.app.PendingIntent.FLAG_UPDATE_CURRENT
                )
            )
            .build()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Serviço de Batalha",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "Mostra o progresso das batalhas em background"
            }
            
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun onDestroy() {
        battleJob?.cancel()
        super.onDestroy()
    }
}
