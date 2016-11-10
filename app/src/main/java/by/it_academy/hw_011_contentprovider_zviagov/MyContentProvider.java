package by.it_academy.hw_011_contentprovider_zviagov;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by dissa on 28.09.2016.
 */

public class MyContentProvider extends ContentProvider {

    public static final String AUTHORITY = BuildConfig.APPLICATION_ID + ".sqlprovider";

    private MySqliteOpenHelper mSqliteOpenHelper;

    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    private static final int CONTACT_ITEMS = 0;
    private static final int CONTACT_ITEM = 1;

    static {
        URI_MATCHER.addURI(AUTHORITY, ContactContract.TABLE_NAME, CONTACT_ITEMS);
        URI_MATCHER.addURI(AUTHORITY, ContactContract.TABLE_NAME + "/#", CONTACT_ITEM);
    }

    @Override
    public boolean onCreate() {
        mSqliteOpenHelper = new MySqliteOpenHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        int uriType = URI_MATCHER.match(uri);
        final String table;
        switch (uriType) {
            case CONTACT_ITEM:
                selection =
                        DatabaseUtils.concatenateWhere(
                                ContactContract._ID + "=" + ContentUris.parseId(uri),
                                selection
                        );
            case CONTACT_ITEMS:
                table = ContactContract.TABLE_NAME;
                break;
            default:
                throw new IllegalArgumentException("Unknown uri!");
        }

        final SQLiteDatabase db = mSqliteOpenHelper.getReadableDatabase();
        Cursor cursor = db.query(table, projection, selection, selectionArgs, null, null, ContactContract.FIRST_NAME);
        if (cursor != null) {
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int uriType = URI_MATCHER.match(uri);
        final String table;
        switch (uriType) {
            case CONTACT_ITEMS:
                table = ContactContract.TABLE_NAME;
                break;
            default:
                throw new IllegalArgumentException("Unknown uri!");
        }

        final SQLiteDatabase db = mSqliteOpenHelper.getWritableDatabase();
        long newId = db.insert(table, null, values);
        getContext().getContentResolver().notifyChange(uri, null, false);
        return ContentUris.withAppendedId(uri, newId);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int uriType = URI_MATCHER.match(uri);
        final String table;
        switch (uriType) {
            case CONTACT_ITEM:
                selection =
                        DatabaseUtils.concatenateWhere(
                                ContactContract._ID + "=" + ContentUris.parseId(uri),
                                selection
                        );
            case CONTACT_ITEMS:
                table = ContactContract.TABLE_NAME;
                break;
            default:
                throw new IllegalArgumentException("Unknown uri!");
        }

        final SQLiteDatabase db = mSqliteOpenHelper.getWritableDatabase();
        int deleteRows = db.delete(table, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null, false);
        return deleteRows;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int uriType = URI_MATCHER.match(uri);
        final String table;
        switch (uriType) {
            case CONTACT_ITEM:
                selection =
                        DatabaseUtils.concatenateWhere(
                                ContactContract._ID + "=" + ContentUris.parseId(uri),
                                selection
                        );
            case CONTACT_ITEMS:
                table = ContactContract.TABLE_NAME;
                break;
            default:
                throw new IllegalArgumentException("Unknown uri!");
        }

        final SQLiteDatabase db = mSqliteOpenHelper.getWritableDatabase();
        int updateRows = db.update(table, values, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return updateRows;
    }
}
