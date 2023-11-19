package com.example.ejercicio23;

// PhotoListActivity.java
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class PhotoListActivity extends AppCompatActivity {
    private ListView listView;
    private DatabaseManager databaseManager;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_list);

        listView = findViewById(R.id.listView);
        databaseManager = new DatabaseManager(this);
        databaseManager.open();

        // Insert a sample photo (you should do this when taking a photo in your app)
        Photograph samplePhoto = new Photograph(0, null, "Sample Photo");
        databaseManager.insertPhoto(samplePhoto);

        // Retrieve and display all photos in the ListView
        List<Photograph> photos = databaseManager.getAllPhotos();
        ArrayAdapter<Photograph> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, photos);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseManager.close();
    }
}
