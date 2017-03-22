package com.example.aaron.lab16aaronohare.parsers;

import com.example.aaron.lab16aaronohare.model.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by aaron on 21/03/2017.
 */

//JSON Parser

public class JSONParser {

    public static ArrayList<News> parseFeed(String content) {


        try {
            //Converts json content into untyped array of objects
            JSONArray ja = new JSONArray(content);

            ArrayList newsList = new ArrayList<>();

            for (int i = 0; i < ja.length(); i++) {
                //get reference to current json object
                JSONObject obj = ja.getJSONObject(i);
                News news = new News();
                //get title/image tag contents in json and set them
                news.setTitle(obj.getString("title"));
                news.setEnlosureURL(obj.getString("image"));

                //add new object to flower list
                newsList.add(news);


            }
            return newsList;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;

        }
    }
}
