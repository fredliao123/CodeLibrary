package com.example.fredliao.codelibrary.firebase.message

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import timber.log.Timber

/**
 * Service to deal with Firebase Cloud Messaging push notifications while app is in
 * the foreground, etc.
 */
class FredFireBaseMessageService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage?) {
        Timber.d("Received FCM MESG DATA: %s", message?.data)

        message?.data?.let {
            // This is where we deal with different message
        }
    }
}
