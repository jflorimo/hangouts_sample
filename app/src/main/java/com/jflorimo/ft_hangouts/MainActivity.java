package com.jflorimo.ft_hangouts;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView contactList;

	List<Contact> contacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactList = (ListView)findViewById(R.id.contactList);
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
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

	private void displayContacts(){
		contacts.add(new Contact(Color.BLACK, "Florent", "Mon premier tweet !"));
		contacts.add(new Contact(Color.BLUE, "Kevin", "C'est ici que ça se passe !"));
		contacts.add(new Contact(Color.GREEN, "Logan", "Que c'est beau..."));
		contacts.add(new Contact(Color.RED, "Mathieu", "Il est quelle heure ??"));
		contacts.add(new Contact(Color.GRAY, "Willy", "On y est presque"));
		contacts.add(new Contact(Color.BLACK, "Florent", "Mon premier tweet !"));
		contacts.add(new Contact(Color.BLUE, "Kevin", "C'est ici que ça se passe !"));
		contacts.add(new Contact(Color.GREEN, "Logan", "Que c'est beau..."));
		contacts.add(new Contact(Color.RED, "Mathieu", "Il est quelle heure ??"));
		contacts.add(new Contact(Color.GRAY, "Willy", "On y est presque"));
		contacts.add(new Contact(Color.BLACK, "Florent", "Mon premier tweet !"));
		contacts.add(new Contact(Color.BLUE, "Kevin", "C'est ici que ça se passe !"));
		contacts.add(new Contact(Color.GREEN, "Logan", "Que c'est beau..."));
		contacts.add(new Contact(Color.RED, "Mathieu", "Il est quelle heure ??"));
		contacts.add(new Contact(Color.GRAY, "Willy", "On y est presque"));

		ContactAdapter adapter = new ContactAdapter(MainActivity.this, contacts);
		contactList.setAdapter(adapter);
	}
}
