package com.example.assignment7;

import android.util.Xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class NewsXmlParser extends DefaultHandler {

    NewsItem newsItem;
    ArrayList<NewsItem> newsList = new ArrayList<>();
    StringBuilder buffer = new StringBuilder();

    public ArrayList<NewsItem> parse(InputStream inputStream) throws IOException, SAXException {
        Xml.parse(inputStream, Xml.Encoding.UTF_8, this);
        return newsList;
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);

        if (localName.equals("channel")) {
            newsList = new ArrayList<>();
        } else if (localName.equals("item")) {
            newsItem = new NewsItem();
        }
        buffer.setLength(0);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);

        if (localName.equals("item")) {
            newsList.add(newsItem);
        } else if (localName.equals("title") && newsItem != null) {
            newsItem.setTitle(buffer.toString());
        } else if (localName.equals("link") && newsItem != null) {
            newsItem.setLink(buffer.toString());
        } else if (localName.equals("description") && newsItem != null) {
            newsItem.setDescription(buffer.toString());
        } else if (localName.equals("pubDate") && newsItem != null) {
            newsItem.setPubDate(buffer.toString());
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        buffer.append(ch, start, length);
    }
}
