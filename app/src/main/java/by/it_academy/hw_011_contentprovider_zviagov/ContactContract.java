package by.it_academy.hw_011_contentprovider_zviagov;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by dissa on 28.09.2016.
 */

public class ContactContract implements BaseColumns {
    public static final String TABLE_NAME = "Contacts";

    public static final Uri CONTENT_URI = new Uri.Builder()
            .scheme("content")
            .authority(MyContentProvider.AUTHORITY)
            .appendPath(TABLE_NAME)
            .build();

    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";

    public static final String CREATE_TABLE_SCRIPT = "CREATE TABLE " + TABLE_NAME + " " +
            "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            FIRST_NAME + " TEXT, " + LAST_NAME + " TEXT); ";

    public static final String DROP_TABLE_SCRIPT =
            "DROP TABLE IF EXIST " + TABLE_NAME + ";";


    public static final String FILL_TABLE_SCRIPT1 =
            "INSERT INTO " + TABLE_NAME + " (" + FIRST_NAME + ", " + LAST_NAME + ") VALUES ('Сергей', 'Попков');";
    public static final String FILL_TABLE_SCRIPT2 =
            "INSERT INTO " + TABLE_NAME + " (" + FIRST_NAME + ", " + LAST_NAME + ") VALUES ('Максим', 'Горбаль');";
    public static final String FILL_TABLE_SCRIPT3 =
            "INSERT INTO " + TABLE_NAME + " (" + FIRST_NAME + ", " + LAST_NAME + ") VALUES ('Виталий', 'Кононович');";

}
