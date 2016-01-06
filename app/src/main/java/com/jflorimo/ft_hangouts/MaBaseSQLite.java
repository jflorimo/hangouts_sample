package com.jflorimo.ft_hangouts;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jflorimo on 27/11/15.
 */
public class MaBaseSQLite extends SQLiteOpenHelper {

	private static final String TABLE_LIVRES = "table_contact";
	private static final String COL_ID = "ID";
	private static final String COL_ISBN = "ISBN";
	private static final String COL_TITRE = "Titre";

	private static final String DATABASE_CREATE_CONTACT = "CREATE TABLE IF NOT EXISTS table_contact (" +
			"`id` INTEGER PRIMARY KEY AUTOINCREMENT, " +
			"`login` varchar(120), " +
			"`number` varchar(55), " +
			"`email` varchar(120), " +
			"`adress` varchar(255)" +
			");";

    private static final String DATABASE_CREATE_MESSAGE = "CREATE TABLE IF NOT EXISTS table_message (" +
            "`id` INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "`number` varchar(55), " +
            "`message` varchar(120), " +
            "`sender` INTEGER" +
            ");";


	public MaBaseSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//on créé la table à partir de la requête écrite dans la variable CREATE_BDD
		db.execSQL(DATABASE_CREATE_CONTACT);
        db.execSQL(DATABASE_CREATE_MESSAGE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//On peut fait ce qu'on veut ici moi j'ai décidé de supprimer la table et de la recréer
		//comme ça lorsque je change la version les id repartent de 0
//		db.execSQL("DROP TABLE " + TABLE_LIVRES + ";");
		onCreate(db);
	}

}
