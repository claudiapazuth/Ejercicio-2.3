package com.example.ejercicio23;

// DatabaseManager.java
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public DatabaseManager(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long insertPhoto(Photograph photo) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_IMAGE, photo.getImage());
        values.put(DatabaseHelper.COLUMN_DESCRIPTION, photo.getDescription());

        return database.insert(DatabaseHelper.TABLE_PHOTOS, null, values);
    }

    public List<Photograph> getAllPhotos() {
        List<Photograph> photos = new ArrayList<>();
        Cursor cursor = database.query(
                DatabaseHelper.TABLE_PHOTOS,
                null,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor != null) {
            int idIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_ID);
            int imageIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_IMAGE);
            int descriptionIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_DESCRIPTION);

            while (cursor.moveToNext()) {
                int id = cursor.getInt(idIndex);
                byte[] image = cursor.getBlob(imageIndex);
                String description = cursor.getString(descriptionIndex);

                photos.add(new Photograph(id, image, description));
            }

            cursor.close();
        }

        return photos;
    }
}
