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

            ///Vriables that you need to keep track paarse position
            boolean inTag = false;
            //cuurent xml tag
            String currentTagName = "";
            // the news object you are currently constructing from the XML
            News news = null;
            // Full list
            ArrayList<News> newsList = new ArrayList<>();


            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            // content is the complete XML content that was passed in from the calling program
            parser.setInput(new StringReader(content));

            int eventType = parser.getEventType();

            // XMLPullParser generates events. Once for each start tag, end tag and for text events
            while (eventType != XmlPullParser.END_DOCUMENT) {

                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        currentTagName = parser.getName();

                        // if starting a new product create a new Flower object to start building it up.
                        if (currentTagName.equals("item")) {
                            inTag = true;
                            news = new News();
                            newsList.add(news);
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        // if leaving current product
                        if (parser.getName().equals("item")) {
                            inTag = false;
                        }
                        currentTagName = "";
                        break;

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