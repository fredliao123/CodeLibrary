package com.example.fredliao.codelibrary.firebase.message

import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService
import timber.log.Timber

/**
 * Service to deal with Firebase Cloud Messaging push notifications need for creation,
 * rotation, and updating of registration tokens.
 */
class FredFirebaseInstanceIDService : FirebaseInstanceIdService() {

    override fun onTokenRefresh() {
        // Get updated InstanceID token.
        val refreshedToken = FirebaseInstanceId.getInstance().token
        Timber.d("Refreshed FCM: %s", refreshedToken)

        if (refreshedToken != null) {
        }
    }
}