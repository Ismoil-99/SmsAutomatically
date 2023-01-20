package com.android.smsautomatically

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Telephony
import android.telephony.SmsMessage
import android.widget.EditText


class MyBroadCastSms : BroadcastReceiver() {
    fun setEditText(editText: EditText?) {
        Companion.editText = editText
    }

    // OnReceive will keep trace when sms is been received in mobile
    override fun onReceive(context: Context, intent: Intent) {
        //message will be holding complete sms that is received
        val messages: Array<SmsMessage> = Telephony.Sms.Intents.getMessagesFromIntent(intent)
        for (sms in messages) {
            val msg: String = sms.messageBody
            // here we are splitting the sms using " : " symbol
            val otp = msg.split(": ").toTypedArray()[0]
            editText!!.setText(otp)
        }
    }

    companion object {
        private var editText: EditText? = null
    }
}