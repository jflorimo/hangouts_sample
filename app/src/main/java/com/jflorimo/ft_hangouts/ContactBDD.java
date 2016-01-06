package com.jflorimo.ft_hangouts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jflorimo on 27/11/15.
 */
public class ContactBDD {

	private static final int VERSION_BDD = 1;
	private static final String NOM_BDD = "contact.db";

	private static final String TABLE_CONTACTS = "table_contact";
	private static final String COL_ID = "ID";
	private static final int NUM_COL_ID = 0;
	private static final String COL_LOGIN = "LOGIN";
	private static final int NUM_COL_LOGIN = 1;
	private static final String COL_NUMBER = "NUMBER";
	private static final int NUM_COL_NUMBER = 2;
	private static final String COL_EMAIL = "EMAIL";
	private static final int NUM_COL_EMAIL = 3;
	private static final String COL_ADRESS = "ADRESS";
	private static final int NUM_COL_ADRESS = 4;

    private static final String TABLE_MESSAGES = "table_message";
    private static final String COL_ID_MESSAGE = "ID";
    private static final int NUM_COL_ID_MESSAGE = 0;
    private static final String COL_NUMBER_MESSAGE = "NUMBER";
    private static final int NUM_COL_NUMBER_MESSAGE = 1;
    private static final String COL_MESSAGE = "MESSAGE";
    private static final int NUM_COL_MESSAGE = 2;
    private static final String COL_SENDER = "SENDER";
    private static final int NUM_COL_SENDER = 3;

	private SQLiteDatabase bdd;

	private MaBaseSQLite maBaseSQLite;

	public ContactBDD(Context context){
		//On créer la BDD et sa table
		maBaseSQLite = new MaBaseSQLite(context, NOM_BDD, null, VERSION_BDD);
	}

	public void open(){
		//on ouvre la BDD en écriture
		bdd = maBaseSQLite.getWritableDatabase();
	}

	public void close(){
		//on ferme l'accès à la BDD
		bdd.close();
	}

	public SQLiteDatabase getBDD(){
		return bdd;
	}

	public long insertContact(Contact contact){

		ContentValues values = new ContentValues();

		values.put(COL_LOGIN, contact.getLogin());
		values.put(COL_NUMBER, contact.getNumber());
		values.put(COL_EMAIL, contact.getEmail());
		values.put(COL_ADRESS, contact.getAdress());

		return bdd.insert(TABLE_CONTACTS, null, values);
	}

    public long insertMessage(Message msg){

        ContentValues values = new ContentValues();

        values.put(COL_NUMBER_MESSAGE, msg.getNumber());
        values.put(COL_MESSAGE, msg.getMessage());
        values.put(COL_SENDER, msg.getSender());

        return bdd.insert(TABLE_MESSAGES, null, values);
    }

	public int updateContact(int id, Contact contact){
		ContentValues values = new ContentValues();
		values.put(COL_LOGIN, contact.getLogin());
		values.put(COL_NUMBER, contact.getNumber());
		values.put(COL_EMAIL, contact.getEmail());
		values.put(COL_ADRESS, contact.getAdress());
		return bdd.update(TABLE_CONTACTS, values, COL_ID + " = " + id, null);
	}

	public int removeContactById(int id){
		//Suppression d'un livre de la BDD grâce à l'ID
		return bdd.delete(TABLE_CONTACTS, COL_ID + " = " + id, null);
	}

    public Contact getContactById(int id){
        Cursor c =  bdd.rawQuery("select * from table_contact where id=" + id + "", null);
        return cursorToContact(c);
    }

	public Contact getContactByNumber(String number){
		//Récupère dans un Cursor les valeur correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
		Cursor c = bdd.query(TABLE_CONTACTS, null, COL_NUMBER + " LIKE \"" + number + "\"", null, null, null, null);
		return cursorToContact(c);
	}

	//Cette méthode permet de convertir un cursor en un livre
	private Contact cursorToContact(Cursor c){
		//si aucun élément n'a été retourné dans la requête, on renvoie null
		if (c.getCount() == 0)
			return null;

		//Sinon on se place sur le premier élément
		c.moveToFirst();
		//On créé un livre
		Contact contact = new Contact(Color.BLUE, c.getString(NUM_COL_LOGIN), c.getString(NUM_COL_NUMBER), c.getString(NUM_COL_EMAIL), c.getString(NUM_COL_ADRESS));
		//on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
		contact.setId(c.getInt(NUM_COL_ID));
		//On ferme le cursor
		c.close();

		//On retourne le livre
		return contact;
	}

	public List<Contact> getAllContacts() {
		List<Contact> contactList = new ArrayList<Contact>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;
		Cursor c = bdd.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {
				Contact contact = new Contact(Color.BLUE, c.getString(NUM_COL_LOGIN), c.getString(NUM_COL_NUMBER), c.getString(NUM_COL_EMAIL), c.getString(NUM_COL_ADRESS));
				//on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
				contact.setId(c.getInt(NUM_COL_ID));
				// Adding contact to list
				contactList.add(contact);
			} while (c.moveToNext());
		}

		// return contact list
		return contactList;
	}

    public List<Message> getAllMessagesFromNumber(String number) {
        List<Message> messageList = new ArrayList<Message>();
        String selectQuery = "SELECT  * FROM " + TABLE_MESSAGES + " WHERE number = '" + number + "'" ;
        Log.d("databaseDEBUG", ": " + selectQuery);
        Cursor c = bdd.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Message msg = new Message(c.getString(NUM_COL_NUMBER_MESSAGE), c.getInt(NUM_COL_SENDER), c.getString(NUM_COL_MESSAGE));
                msg.setId(c.getInt(NUM_COL_ID_MESSAGE));
                messageList.add(msg);
            } while (c.moveToNext());
        }
        return messageList;
    }
}