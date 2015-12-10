package com.jflorimo.ft_hangouts;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jflorimo on 05/10/15.
 */
public class ContactAdapter extends ArrayAdapter<Contact> {

	private List<Contact> contacts;

	public ContactAdapter(Context context, List<Contact> contacts)
	{
		super(context, 0, contacts);
		this.contacts = contacts;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		if(convertView == null){
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.contact_list_item, parent, false);
		}

		ContactViewHolder viewHolder = (ContactViewHolder) convertView.getTag();
		if(viewHolder == null){
			viewHolder = new ContactViewHolder();
			viewHolder.login = (TextView) convertView.findViewById(R.id.login);
			viewHolder.number = (TextView) convertView.findViewById(R.id.number);
			viewHolder.picture = (ImageView) convertView.findViewById(R.id.picture);
			viewHolder.editButton = (Button) convertView.findViewById(R.id.editButton);
			convertView.setTag(viewHolder);
		}

		//getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
		Contact contact = getItem(position);
		viewHolder.login.setText(contact.getLogin());
		viewHolder.number.setText(contact.getNumber());
		viewHolder.picture.setImageDrawable(new ColorDrawable(contact.getColor()));
		viewHolder.editButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				View parentRow = (View) v.getParent();
				ListView listView = (ListView) parentRow.getParent();
				int position = listView.getPositionForView(parentRow);
				Contact contact = getItem(position);

				Intent intent = new Intent(getContext(), AddContactActivity.class);
				intent.putExtra("CONTACT_ID", contact.getId());
				intent.putExtra("CONTACT_LOGIN", contact.getLogin());
				intent.putExtra("CONTACT_NUMBER", contact.getNumber());
				intent.putExtra("CONTACT_EMAIL", contact.getEmail());
				intent.putExtra("CONTACT_ADRESS", contact.getAdress());
				getContext().startActivity(intent);
			}
		});

		return convertView;
	}

	private class ContactViewHolder{
		public ImageView picture;
		public TextView login;
		public TextView number;
		public Button editButton;

	}
}
