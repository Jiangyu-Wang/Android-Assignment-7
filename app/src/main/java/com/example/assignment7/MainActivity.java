package com.example.assignment7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button channel1Button = findViewById(R.id.channel_1);
        Button channel2Button = findViewById(R.id.channel_2);
        Button channel3Button = findViewById(R.id.channel_3);
        Button settingButton = findViewById(R.id.setting);

        channel1Button.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), NewsListActivity.class);
            intent.putExtra("channel_name", "breakingnews");
            startActivity(intent);
        });

        channel2Button.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), NewsListActivity.class);
            intent.putExtra("channel_name", "business");
            startActivity(intent);
        });

        channel3Button.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), NewsListActivity.class);
            intent.putExtra("channel_name", "local");
            startActivity(intent);
        });

        settingButton.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), AppSettingActivity.class);
            startActivity(intent);
        });

    }
}