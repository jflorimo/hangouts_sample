package com.jflorimo.ft_hangouts;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jflorimo on 11/12/15.
 */
public class MessagesActivity extends Activity {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private EditText messageEditText;
    private Button sendButton;
    private TextView loginText;
    private TextView numberText;

    private Intent intent;
    private ContactBDD bdd;
    private Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        intent = getIntent();
        bdd = new ContactBDD(this);
        bdd.open();

        listView = (ListView)findViewById(R.id.listView);
        messageEditText = (EditText) findViewById(R.id.message);
        sendButton = (Button) findViewById(R.id.send);
        loginText = (TextView) findViewById(R.id.loginTextView);
        numberText = (TextView) findViewById(R.id.numberTextView);


        contact = bdd.getContactById(intent.getIntExtra("CONTACT_ID", -1));

        loginText.setText(contact.getLogin());
        numberText.setText(contact.getNumber());


        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1);
        listView.setAdapter(adapter);

        sendButton.setOnClickListener(sendButtonListener);

        List<Message> messages = bdd.getAllMessagesFromNumber(contact.getNumber());
        for (Message msg: messages) {
			if (msg.getSender() == 1)
				adapter.add("Me: "+msg.getMessage());
			else
				adapter.add(contact.getLogin()+": "+msg.getMessage());
		}

        listView.setSelection(adapter.getCount() - 1);
    }

    View.OnClickListener sendButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
			closeKeyboard();

			SmsManager smsManager = SmsManager.getDefault();

			String phoneNumber = contact.getNumber();
			String smsBody = messageEditText.getText().toString();

			String SMS_SENT = "SMS_SENT";
			String SMS_DELIVERED = "SMS_DELIVERED";

			PendingIntent sentPendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent(SMS_SENT), 0);
			PendingIntent deliveredPendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent(SMS_DELIVERED), 0);

			ArrayList<String> smsBodyParts = smsManager.divideMessage(smsBody);
			ArrayList<PendingIntent> sentPendingIntents = new ArrayList<PendingIntent>();
			ArrayList<PendingIntent> deliveredPendingIntents = new ArrayList<PendingIntent>();

			for (int i = 0; i < smsBodyParts.size(); i++) {
				sentPendingIntents.add(sentPendingIntent);
				deliveredPendingIntents.add(deliveredPendingIntent);
			}

			// For when the SMS has been sent
			registerReceiver(new BroadcastReceiver() {
				@Override
				public void onReceive(Context context, Intent intent) {
					switch (getResultCode()) {
						case Activity.RESULT_OK:
							//Toast.makeText(context, "SMS sent successfully", Toast.LENGTH_SHORT).show();
							break;
						case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
							Toast.makeText(context, "Generic failure cause", Toast.LENGTH_SHORT).show();
							break;
						case SmsManager.RESULT_ERROR_NO_SERVICE:
							Toast.makeText(context, "Service is currently unavailable", Toast.LENGTH_SHORT).show();
							break;
						case SmsManager.RESULT_ERROR_NULL_PDU:
							Toast.makeText(context, "No pdu provided", Toast.LENGTH_SHORT).show();
							break;
						case SmsManager.RESULT_ERROR_RADIO_OFF:
							Toast.makeText(context, "Radio was explicitly turned off", Toast.LENGTH_SHORT).show();
							break;
					}
				}
			}, new IntentFilter(SMS_SENT));

			// For when the SMS has been delivered
			registerReceiver(new BroadcastReceiver() {
				@Override
				public void onReceive(Context context, Intent intent) {
					switch (getResultCode()) {
						case Activity.RESULT_OK:
                            saveSms();
							break;
						case Activity.RESULT_CANCELED:
							Toast.makeText(getBaseContext(), "SMS not delivered", Toast.LENGTH_SHORT).show();
							break;
					}
				}
			}, new IntentFilter(SMS_DELIVERED));

			// Send a text based SMS
			smsManager.sendMultipartTextMessage(phoneNumber, null, smsBodyParts, sentPendingIntents, deliveredPendingIntents);
        }
    };

    private void saveSms()
    {
        bdd.insertMessage(new Message(contact.getNumber(), 1, messageEditText.getText().toString()));
        Toast.makeText(getBaseContext(), "SMS delivered", Toast.LENGTH_SHORT).show();
		adapter.add("Me: "+messageEditText.getText().toString());
		messageEditText.setText("");
		listView.setSelection(adapter.getCount() - 1);
    }

    private void closeKeyboard()
    {
        View view = MessagesActivity.this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}

