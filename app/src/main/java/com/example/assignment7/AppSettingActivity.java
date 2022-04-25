package com.example.assignment7;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AppSettingActivity extends AppCompatActivity {

    SharedPreferences sharedPref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        sharedPref = getSharedPreferences("sizePref", Context.MODE_PRIVATE);

        Button save = findViewById(R.id.savePreference);
        TextView titleFontSizeText = findViewById(R.id.titleFontSize);
        TextView descriptionFontSizeText = findViewById(R.id.descriptionFontSize);
        TextView dateFontSizeText = findViewById(R.id.dateFontSize);
        TextView listPaddingSizeText = findViewById(R.id.listPaddingSize);

        int titleFontSize = sharedPref.getInt("titleFontSize", 25);
        int descriptionFontSize = sharedPref.getInt("descriptionFontSize", 20);
        int dateFontSize = sharedPref.getInt("dateFontSize", 15);
        int listPaddingSize = sharedPref.getInt("listPaddingSize", 10);

        titleFontSizeText.setText(Integer.toString(titleFontSize));
        descriptionFontSizeText.setText(Integer.toString(descriptionFontSize));
        dateFontSizeText.setText(Integer.toString(dateFontSize));
        listPaddingSizeText.setText(Integer.toString(listPaddingSize));

        save.setOnClickListener(view -> {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt("titleFontSize", Integer.parseInt(titleFontSizeText.getText().toString()));
            editor.putInt("descriptionFontSize", Integer.parseInt(descriptionFontSizeText.getText().toString()));
            editor.putInt("dateFontSize", Integer.parseInt(dateFontSizeText.getText().toString()));
            editor.putInt("listPaddingSize", Integer.parseInt(listPaddingSizeText.getText().toString()));
            editor.apply();
            Toast.makeText(getApplicationContext(), "Saved successfully!", Toast.LENGTH_LONG).show();
        });
    }
}
