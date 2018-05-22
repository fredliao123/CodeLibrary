package com.example.fredliao.codelibrary.firebase.message

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v4.content.LocalBroadcastManager

private const val RECEIVED_PUSH_MESSAGE = "push_message_received"
private const val RECEIVED_PUSH_DATA_EXTRA = "push_message_received_data_extra"

/**
 * Send notification of received push message
 */
fun notifyMessage(context: Context, data: String) {
    val localBroadcastManager = LocalBroadcastManager.getInstance(context.applicationContext)
    val messageIntent = Intent(RECEIVED_PUSH_MESSAGE)
    messageIntent.putExtra(RECEIVED_PUSH_DATA_EXTRA, data)
    localBroadcastManager.sendBroadcast(messageIntent)
}

/**
 * Helper to allow Views to be notified of when push notification messages are received.
 * The passed in callback function will be called with the data type received in the push
 * notification.
 */
class PushNotificationHandler(val context: Context, val callback: (data: String?) -> Unit) {

    private val localBroadcastManager by lazy { LocalBroadcastManager.getInstance(context.applicationContext) }
    private val filter = IntentFilter(RECEIVED_PUSH_MESSAGE)

    private val receiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            callback(intent?.getStringExtra(RECEIVED_PUSH_DATA_EXTRA))
        }
    }

    /**
     * Register to start receiving notification of received push messages
     */
    fun register() {
        localBroadcastManager.registerReceiver(receiver, filter)
    }

    /**
     * Unregister to stop receiving notification of received push messages
     */
    fun unregister() {
        localBroadcastManager.unregisterReceiver(receiver)
    }
}
