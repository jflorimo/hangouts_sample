package com.jflorimo.ft_hangouts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by jflorimo on 16/12/15.
 */
public class SmsReceiver extends BroadcastReceiver {
	private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
	private static final String TAG = "##SMSBroadcastReceiver";
    private ContactBDD bdd;
	public SmsReceiver() {

	}

	@Override
	public void onReceive(Context context, Intent intent) {
        bdd = new ContactBDD(context);
        bdd.open();
//		Log.d(TAG, "Intent recieved: " + intent.getAction());

		if (intent.getAction().equals(SMS_RECEIVED)) {
			Bundle bundle = intent.getExtras();
			if (bundle != null) {
				Object[] pdus = (Object[])bundle.get("pdus");
				final SmsMessage[] messages = new SmsMessage[pdus.length];
				for (int i = 0; i < pdus.length; i++) {
					messages[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
				}
				if (messages.length > -1) {
					Log.d(TAG, "from:"+ messages[0].getOriginatingAddress());
					Log.d(TAG, "Message recieved: " + messages[0].getMessageBody());
					Contact tmp = bdd.getContactByNumber(messages[0].getOriginatingAddress());
					if (tmp != null)
						bdd.insertMessage(new Message(messages[0].getOriginatingAddress(), 0, messages[0].getMessageBody()));
					else
					{
						bdd.insertContact(new Contact(
								Color.RED,
								messages[0].getOriginatingAddress(),
								messages[0].getOriginatingAddress(),
								"",
								""
						));
						bdd.insertMessage(new Message(messages[0].getOriginatingAddress(), 0, messages[0].getMessageBody()));
					}
				}
			}
		}
        bdd.close();
	}
}