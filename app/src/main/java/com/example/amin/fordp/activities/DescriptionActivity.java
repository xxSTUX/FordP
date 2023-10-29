package com.example.amin.fordp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.amin.fordp.R;

public class DescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_layout_main);

        String title = getIntent().getStringExtra("title");
        int imageResource = getIntent().getIntExtra("imageResource", 0);
        String description = getIntent().getStringExtra("description");

        TextView titleTextView = findViewById(R.id.titleTextView);
        ImageView imageView = findViewById(R.id.imageView);
        TextView descriptionTextView = findViewById(R.id.descriptionTextView);

        titleTextView.setText(title);
        imageView.setImageResource(imageResource);
        descriptionTextView.setText(description);

        Button volverButton = findViewById(R.id.volver);
        volverButton.setOnClickListener(v -> {
            Intent intent = new Intent(DescriptionActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}

