@file:Suppress("DEPRECATION")

package aaa.bivizul.a41project.domain.util

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.telephony.TelephonyManager
import com.onesignal.OneSignal
import java.text.SimpleDateFormat
import java.util.*

fun getFunspomm(): String {
    val manfacfunspo = android.os.Build.MANUFACTURER
    val modelfunspo = android.os.Build.MODEL
    return "$manfacfunspo $modelfunspo"
}

fun getFunsposim(funspocon: Context): String {
    val telmanfunspo = funspocon.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
    return telmanfunspo.simCountryIso
}

fun getFunspoid(funspocon: Context): String {
    val sharedPreferences = funspocon.getSharedPreferences("apppreffunspo", Context.MODE_PRIVATE)
    var funspoid = sharedPreferences.getString("funspo_key", "nofunspo") ?: "nofunspo"
    if (funspoid == "nofunspo") {
        val dateNow = Date()
        val simpleDateFormat = SimpleDateFormat("yyMMddhhmmssMs")
        val datetime = simpleDateFormat.format(dateNow)
        val randomNum = (10000 until 100000).random()
        funspoid = datetime + randomNum
        sharedPreferences.edit().putString("funspo_key", funspoid).apply()
    }
    return funspoid
}

fun getFunspol(): String {
    return Locale.getDefault().language
}

fun getFunspot(): String {
    val funspotz: String = SimpleDateFormat("z", Locale.getDefault()).format(
        Calendar.getInstance(
            TimeZone.getTimeZone("GMT"),
            Locale.getDefault()
        ).time
    ).replace("GMT", "")
    val funspozone = if (funspotz.contains(":")) funspotz else "default"
    return funspozone
}

fun getFunspodlg(funspocon: Context, funspoact: Activity) {
    AlertDialog.Builder(funspocon).apply {
        setTitle("Error connect!")
        setMessage("Please try again later, push exit")
        setPositiveButton("Exit") { _, _ ->
            funspoact.finish()
            System.exit(0)
        }
        setCancelable(true)
    }.create().show()
}

@SuppressLint("MissingPermission")
fun checkFunsponet(funspocon: Context): Boolean {
    val conmanfunspo =
        funspocon.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val netinffunspo = conmanfunspo.activeNetworkInfo
    return netinffunspo != null && netinffunspo.isConnected
}

fun sigFunspooff() {
    OneSignal.disablePush(true)
}

//fun getFunspoact(funspoact: Activity, funspourl: String) {
//    val activity = funspoact as Activity
//    val funspoc = Class.forName(SPOHOWACTIVITY)
//    val funspoi = Intent(activity, funspoc)
//    val put = funspoi.putExtra(Funspocon.SPOHOWKOR, funspourl)
//    activity.startActivity(put)
//}