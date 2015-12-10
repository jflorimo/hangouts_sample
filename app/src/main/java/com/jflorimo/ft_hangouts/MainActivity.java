package com.jflorimo.ft_hangouts;

import android.app.ActionBar;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ListView contactList;
	private Button addContactButton;
    private ContactBDD bdd;
	List<Contact> contacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bdd = new ContactBDD(this);
        bdd.open();

        contactList = (ListView)findViewById(R.id.contactList);
		addContactButton = (Button)findViewById(R.id.addButton);

		addContactButton.setOnClickListener(addContactListener);
    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first
        displayContacts();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            android.support.v7.app.ActionBar actionBar = getSupportActionBar();
            Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            actionBar.setBackgroundDrawable(new ColorDrawable(color));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

	View.OnClickListener addContactListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent addContactActivity = new Intent(MainActivity.this, AddContactActivity.class);
			startActivity(addContactActivity);
		}
	};

	private void displayContacts(){

        contacts.clear();
        for (Contact elem : bdd.getAllContacts())
        {
            contacts.add(elem);
        }

		ContactAdapter adapter = new ContactAdapter(MainActivity.this, contacts);
		contactList.setAdapter(adapter);
	}
}
