package com.example.aaron.lab16aaronohare;

import android.app.ListActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.aaron.lab16aaronohare.model.News;
import com.example.aaron.lab16aaronohare.parsers.JSONParser;
import com.example.aaron.lab16aaronohare.parsers.XMLParser;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity {

    private TextView output;
    private List<MyTask> myTasks;
    //URL to folder that contains all images - image name is added to this url
    //from the xml data to find the image
    private static final String PHOTOS_BASE_URL ="http://10.0.2.2/OOPDCA2/images/";

    //Array list that stores all of the news objects
    ArrayList<News> newsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyTask task = new MyTask();
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Uncomment/Comment these lines to test XML & JSON//////////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //JSON
        task.execute("http://10.0.2.2/OOPDCA2/myFeed.json");
        //XML
        //task.execute("http://10.0.2.2/OOPDCA2/myFeed.rss");

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }


    protected void updateDisplay() {
        NewsAdapter adapter = new NewsAdapter(this, R.layout.news_item, newsList);
        setListAdapter(adapter);
    }

    private class MyTask extends AsyncTask<String, String, ArrayList<News>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<News> doInBackground(String... strings) {
            //get xml data through http manager
            String content = HttpManager.getData(strings[0]);

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            ///Uncomment/Comment these lines to test XML & JSON//////////////////////////////////////////////////////////////////////////////////////////////
            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //parse the data that was retrieved

            //XML
            //newsList = XMLParser.parseFeed(content);

            //JSON
            newsList = JSONParser.parseFeed(content);
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            //for each news item in the news list
            for (News news : newsList){
                try{
                    //construct the image url
                    String imageUrl = PHOTOS_BASE_URL + news.getEnlosureURL();
                    //Request the image
                    InputStream in = (InputStream) new URL(imageUrl).getContent();
                    //store the image in a bitmap
                    Bitmap bitmap = BitmapFactory.decodeStream(in);
                    news.setBitmap(bitmap);
                    //close the input stream
                    in.close();
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
            //return the list of news oibjects
            return newsList;

         }


        @Override
        protected void onPostExecute(ArrayList <News> news) {
            super.onPostExecute(news);
            updateDisplay();
        }
    }
}
