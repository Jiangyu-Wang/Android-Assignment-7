package com.example.assignment7;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsListActivity extends AppCompatActivity {

    ArrayList<NewsItem> newsList = new ArrayList<>();
    NewsItemAdapter newsItemAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = findViewById(R.id.listView);
        Intent intent = getIntent();
        String channelName = intent.getStringExtra("channel_name");
        String baseUrl = "https://www.winnipegfreepress.com/rss/?path=%2F";

        OkHttpClient client = new OkHttpClient();
        String url = baseUrl + channelName;
        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    NewsXmlParser newsXmlParser = new NewsXmlParser();
                    try {
                        newsList = newsXmlParser.parse(response.body().byteStream());
                        newsItemAdapter = new NewsItemAdapter(getApplicationContext(), R.layout.list_item, newsList);
                        runOnUiThread(() -> listView.setAdapter(newsItemAdapter));
                    } catch (SAXException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(view.getContext(), NewsDetailActivity.class);
                intent.putExtra("link_url", newsList.get(i).link);
                startActivity(intent);
            }
        });

    }

}
