package com.jflorimo.ft_hangouts;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jflorimo on 05/10/15.
 */
public class ContactAdapter extends ArrayAdapter<Contact> {

	public ContactAdapter(Context context, List<Contact> contacts)
	{
		super(context, 0, contacts);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		if(convertView == null){
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.contact_list_item,parent, false);
		}

		ContactViewHolder viewHolder = (ContactViewHolder) convertView.getTag();
		if(viewHolder == null){
			viewHolder = new ContactViewHolder();
			viewHolder.login = (TextView) convertView.findViewById(R.id.login);
			viewHolder.text = (TextView) convertView.findViewById(R.id.text);
			viewHolder.picture = (ImageView) convertView.findViewById(R.id.picture);
			convertView.setTag(viewHolder);
		}

		//getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
		Contact contact = getItem(position);
		viewHolder.login.setText(contact.getLogin());
		viewHolder.text.setText(contact.getText());
		viewHolder.picture.setImageDrawable(new ColorDrawable(contact.getColor()));

		return convertView;
	}

	private class ContactViewHolder{
		public ImageView picture;
		public TextView login;
		public TextView text;

	}
}
