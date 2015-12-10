package com.jflorimo.ft_hangouts;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by jflorimo on 26/11/15.
 */
public class AddContactActivity extends Activity{

	private Button addContactButton;
	private Button deleteButton;
	private EditText login;
	private EditText number;
	private EditText email;
	private EditText adress;
	private Intent intent;

	private ContactBDD bdd;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_contact_activity);
		intent = getIntent();
		bdd = new ContactBDD(this);
		bdd.open();

		login = (EditText)findViewById(R.id.loginEditText);
		number = (EditText)findViewById(R.id.numberEditText);
		email = (EditText)findViewById(R.id.emailEditText);
		adress = (EditText)findViewById(R.id.adressEditText);
		addContactButton = (Button)findViewById(R.id.submitButton);
		deleteButton = (Button)findViewById(R.id.delteButton);

		fillData();
	}

	View.OnClickListener addContactListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			if (login.getText().toString().trim().length() > 0 && number.getText().toString().trim().length() > 0)
			{
				bdd.insertContact(new Contact(
						Color.GREEN,
						login.getText().toString(),
						number.getText().toString(),
						email.getText().toString(),
						adress.getText().toString()
				));
				finish();
			}
			else
			{
				Toast empty = Toast.makeText(AddContactActivity.this, "empty fields!", Toast.LENGTH_LONG);
				empty.show();
			}
		}
	};

	View.OnClickListener updateContactListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			if (intent.getIntExtra("CONTACT_ID", -1) != -1) {
				Contact tmp = new Contact(
						Color.RED,
						login.getText().toString(),
						number.getText().toString(),
						email.getText().toString(),
						adress.getText().toString()
				);
				bdd.updateContact(intent.getIntExtra("CONTACT_ID", -1), tmp);
				Toast updated = Toast.makeText(AddContactActivity.this, "updated", Toast.LENGTH_LONG);
				updated.show();
				finish();
			}
			else
			{
				Toast error = Toast.makeText(AddContactActivity.this, "error", Toast.LENGTH_LONG);
				error.show();
				finish();
			}
		}
	};

	View.OnClickListener deleteContactListener = new View.OnClickListener(){

		@Override
		public void onClick(View v) {
			if (intent.getIntExtra("CONTACT_ID", -1) != -1) {
				bdd.removeContactById(intent.getIntExtra("CONTACT_ID", -1));
				Toast updated = Toast.makeText(AddContactActivity.this, "deleted", Toast.LENGTH_LONG);
				updated.show();
				finish();
			}
			else
			{
				Toast error = Toast.makeText(AddContactActivity.this, "error", Toast.LENGTH_LONG);
				error.show();
				finish();
			}
		}
	};

	private void fillData()
	{
		if (intent.getIntExtra("CONTACT_ID", -1) != -1)
		{
			addContactButton.setText(R.string.save);
			login.setText(intent.getStringExtra("CONTACT_LOGIN"));
			number.setText(intent.getStringExtra("CONTACT_NUMBER"));
			email.setText(intent.getStringExtra("CONTACT_EMAIL"));
			adress.setText(intent.getStringExtra("CONTACT_ADRESS"));
			addContactButton.setOnClickListener(updateContactListener);
			deleteButton.setOnClickListener(deleteContactListener);
		}
		else
		{
			deleteButton.setVisibility(View.GONE);
			deleteButton.setEnabled(false);
			addContactButton.setOnClickListener(addContactListener);
		}
	}
}
