package com.example.aaron.lab16aaronohare.parsers;

import com.example.aaron.lab16aaronohare.model.News;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aaron on 22/02/2017.
 */

public class XMLParser {

    public static ArrayList<News> parseFeed(String content) {

        try {

            boolean inTag = false;
            //current xml tag
            String currentTagName = "";
            // the news object you are currently constructing from the XML
            News news = null;
            // Full list
            ArrayList<News> newsList = new ArrayList<>();


            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            // content =  xml content that was passed in
            parser.setInput(new StringReader(content));

            int eventType = parser.getEventType();

            // XmlPullParser generates events for each start tag, end tag and text
            while (eventType != XmlPullParser.END_DOCUMENT) {

                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        currentTagName = parser.getName();

                        // if starting a new item, create a new news object
                        if (currentTagName.equals("item")) {
                            inTag = true;
                            news = new News();
                            newsList.add(news);
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        // if leaving current item
                        if (parser.getName().equals("item")) {
                            inTag = false;
                        }
                        currentTagName = "";
                        break;
                    //prsing each tag
                    case XmlPullParser.TEXT:
                        if (inTag && news != null) {
                            switch (currentTagName) {
                                case "title":
                                    news.setTitle(parser.getText());
                                    break;
                                case "link":
                                    news.setLink(parser.getText());
                                    break;
                                case "description":
                                    news.setDescreption(parser.getText());
                                    break;
                                case "image" :
                                    news.setEnlosureURL(parser.getText());
                                default:
                                    break;
                            }
                        }
                        break;
                }

                eventType = parser.next();

            }

            // return the complete list that was generated above
            return newsList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

}