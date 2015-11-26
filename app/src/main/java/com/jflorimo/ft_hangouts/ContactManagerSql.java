package com.jflorimo.ft_hangouts;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by jflorimo on 26/11/15.
 */
public class ContactManagerSql extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "ft_hangouts.db";
	private static final int DATABASE_VERSION = 1;

	// Commande sql pour la création de la base de données
	private static final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS contactManager (" +
				"`id` int, " +
				"`login` varchar(120), " +
				"`number` varchar(55), " +
				"`email` varchar(120), " +
				"`adress` varchar(255)" +
			");";


	public ContactManagerSql(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(ContactManagerSql.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		onCreate(db);
	}
}
