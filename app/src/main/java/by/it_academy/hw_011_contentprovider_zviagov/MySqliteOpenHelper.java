package by.it_academy.hw_011_contentprovider_zviagov;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by dissa on 28.09.2016.
 */

public class MySqliteOpenHelper extends SQLiteOpenHelper {
    public static final String MYDB_SQLITE = "mydb.sqlite";
    public static final int VERSION = 2;

    public MySqliteOpenHelper(Context context) {
        super(context, MYDB_SQLITE, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ContactContract.CREATE_TABLE_SCRIPT);
        db.execSQL(ContactContract.FILL_TABLE_SCRIPT1);
        db.execSQL(ContactContract.FILL_TABLE_SCRIPT2);
        db.execSQL(ContactContract.FILL_TABLE_SCRIPT3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ContactContract.DROP_TABLE_SCRIPT);
        db.execSQL(ContactContract.CREATE_TABLE_SCRIPT);
    }

}
