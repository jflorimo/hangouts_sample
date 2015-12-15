package com.jflorimo.ft_hangouts;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by jflorimo on 11/12/15.
 */
public class MessagesActivity extends Activity {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private TextView messageTextView;
    private Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        listView = (ListView)findViewById(R.id.listView);
        messageTextView = (TextView) findViewById(R.id.message);
        sendButton = (Button) findViewById(R.id.send);


        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1);
        listView.setAdapter(adapter);

        sendButton.setOnClickListener(sendButtonListener);

        adapter.add("egfejorhgz");
        adapter.add("dzefzeg");
        adapter.add("egfefzefzjorhgz");
        adapter.add("egfejgzefzeforhgz");
        adapter.add("egfejorhgz");
        adapter.add("dzefzeg");
        adapter.add("egfefzefzjorhgz");
        adapter.add("egfejgzefzeforhgz");
        adapter.add("egfejorhgz");
        adapter.add("dzefzeg");
        adapter.add("egfefzefzjorhgz");
        adapter.add("egfejgzefzeforhgz");
        adapter.add("egfejorhgz");
        adapter.add("dzefzeg");
        adapter.add("egfefzefzjorhgz");
        adapter.add("egfejgzefzeforhgz");
        adapter.add("egfejorhgz");
        adapter.add("dzefzeg");
        adapter.add("egfefzefzjorhgz");
        adapter.add("egfejgzefzeforhgz");
        adapter.add("egfejorhgz");
        adapter.add("dzefzeg");
        adapter.add("egfefzefzjorhgz");
        adapter.add("egfejgzefzeforhgz");
        adapter.add("egfejorhgz");
        adapter.add("dzefzeg");
        adapter.add("egfefzefzjorhgz");
        adapter.add("egfejgzefzeforhgz");
        adapter.add("egfejorhgz");
        adapter.add("dzefzeg");
        adapter.add("egfefzefzjorhgz");
        adapter.add("egfejgzefzeforhgz");
        adapter.add("egfejorhgz");
        adapter.add("dzefzeg");
        adapter.add("egfefzefzjorhgz");
        adapter.add("egfejgzefzeforhgz");
        adapter.add("egfejorhgz");
        adapter.add("dzefzeg");
        adapter.add("egfefzefzjorhgz");
        adapter.add("non");

        listView.setSelection(adapter.getCount() - 1);
    }

    View.OnClickListener sendButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            closeKeyboard();

            try {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage("+33608421973", null,messageTextView.getText().toString(), null, null);
                Toast.makeText(getApplicationContext(), "SMS Sent!",
                        Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(),
                        "SMS faild, please try again later!",
                        Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }


        }
    };

    private void closeKeyboard()
    {
        View view = MessagesActivity.this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
