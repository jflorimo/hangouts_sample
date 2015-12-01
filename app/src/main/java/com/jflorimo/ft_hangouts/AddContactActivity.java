package com.jflorimo.ft_hangouts;

import android.app.Activity;
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
	private EditText login;
	private EditText number;
	private EditText email;
	private EditText adress;

	private ContactBDD bdd;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_contact_activity);

		bdd = new ContactBDD(this);
		bdd.open();

		login = (EditText)findViewById(R.id.loginEditText);
		number = (EditText)findViewById(R.id.numberEditText);
		email = (EditText)findViewById(R.id.emailEditText);
		adress = (EditText)findViewById(R.id.adressEditText);
		addContactButton = (Button)findViewById(R.id.submitButton);
		addContactButton.setOnClickListener(addContactListener);
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
}
