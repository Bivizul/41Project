package aaa.bivizul.a41project.domain.util

import android.content.Context
import com.appsflyer.AppsFlyerLib
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.onesignal.OSNotificationReceivedEvent
import com.onesignal.OneSignal
import com.onesignal.OneSignal.OSRemoteNotificationReceivedHandler

class Funsporemnot : OSRemoteNotificationReceivedHandler {

    override fun remoteNotificationReceived(p0: Context?, p1: OSNotificationReceivedEvent?) {
        val funsponot = p1!!.notification.additionalData.get("funspo").toString()
        if (funsponot.isNotEmpty()) {
            p0.let {
                Firebase.analytics.logEvent(funsponot, null)
                AppsFlyerLib.getInstance().logEvent(p0, funsponot, null)
                OneSignal.sendTag("conversion", funsponot)
                OneSignal.sendTag("conversion_time", "${System.currentTimeMillis() / 1000L}")
                p1.complete(null)
            }
        }
    }
}