package com.example.assignment7;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class NewsItemAdapter extends ArrayAdapter<NewsItem> {

    private final int layoutId;
    Context thisContext;

    public NewsItemAdapter(@NonNull Context context, int resource, @NonNull List<NewsItem> newsList) {
        super(context, resource, newsList);
        this.layoutId = resource;
        this.thisContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        NewsItem newsItem = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(layoutId, parent, false);
        TextView titleTextView = view.findViewById(R.id.newsItemTitle);
        TextView descTextView = view.findViewById(R.id.newsItemDescription);
        TextView pubDateTextView = view.findViewById(R.id.newsItemPubDate);
        titleTextView.setText(newsItem.getTitle());
        descTextView.setText(newsItem.getDescription());
        pubDateTextView.setText(newsItem.getPubDate());

        SharedPreferences sharedPref = thisContext.getSharedPreferences("sizePref", Context.MODE_PRIVATE);
        int titleFontSize = sharedPref.getInt("titleFontSize", 25);
        int descriptionFontSize = sharedPref.getInt("descriptionFontSize", 20);
        int dateFontSize = sharedPref.getInt("dateFontSize", 15);
        int listPaddingSize = sharedPref.getInt("listPaddingSize", 10);

        titleTextView.setTextSize(titleFontSize);
        descTextView.setTextSize(descriptionFontSize);
        pubDateTextView.setTextSize(dateFontSize);
        titleTextView.setPadding(listPaddingSize, listPaddingSize, listPaddingSize, listPaddingSize);

        return view;
    }
}
